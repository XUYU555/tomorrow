package com.xxyy.quandaiban1.application.service.Impl;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.xxyy.quandaiban1.application.dtos.NoticeInput;
import com.xxyy.quandaiban1.application.dtos.NoticeOutput;
import com.xxyy.quandaiban1.application.dtos.TaskOutput;
import com.xxyy.quandaiban1.application.service.NoticeService;
import com.xxyy.quandaiban1.domain.model.Notice;
import com.xxyy.quandaiban1.domain.model.Task;
import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.domain.model.qmodel.QNotice;
import com.xxyy.quandaiban1.domain.model.qmodel.QTask;
import com.xxyy.quandaiban1.domain.model.qmodel.QUser;
import com.xxyy.quandaiban1.domain.repository.NoticeRepository;
import com.xxyy.quandaiban1.domain.repository.TaskRepository;
import com.xxyy.quandaiban1.domain.repository.UserRepository;
import com.xxyy.quandaiban1.domain.specs.NoticeSpecs;
import com.xxyy.quandaiban1.infrastructure.AppException;
import com.xxyy.quandaiban1.infrastructure.SecurityUtil;
import com.xxyy.quandaiban1.infrastructure.common.PageOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * @author xy
 * @date 2023-09-27 20:42
 */

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    public NoticeServiceImpl(UserRepository userRepository, NoticeRepository noticeRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.noticeRepository = noticeRepository;
        this.taskRepository = taskRepository;
    }

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private final TaskRepository taskRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public NoticeOutput createBy(NoticeInput noticeInput) {
        User user = SecurityUtil.getUser();
        Notice notice = noticeInput.mapToEntity(user);
        noticeRepository.save(notice);
        List<Task> tasks = StreamSupport.stream(userRepository.findAll(QUser.user.id.in(noticeInput.getUserId())).spliterator(), false)
                .map(curUser -> notice.add(notice, curUser, false)).collect(Collectors.toList());
        taskRepository.saveAll(tasks);
        return NoticeOutput.of(notice);
    }


    @Override
    public PageOutput<NoticeOutput> pageNoticeBy(Pageable pageable, int type) {
        User user = SecurityUtil.getUser();
        if( type == 1) {
            Page<NoticeOutput> notices = noticeRepository.findAll(QNotice.notice.creator.eq(user), pageable).map(NoticeOutput::ofMyNotice);
            return PageOutput.of(notices);
        }
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QTask.task.user.eq(user));
        builder.and(QTask.task.readStatus.eq(false));
        builder.and(QTask.task.followed.eq(false));
        Page<NoticeOutput> noticeOutputs = taskRepository.findAll(builder, pageable).map(Task::getNotice).map(NoticeOutput::of);
        return PageOutput.of(noticeOutputs);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public TaskOutput followBy(Long noticeId, boolean followed) {
        User user = SecurityUtil.getUser();
        Notice notice = SecurityUtil.getNotice(noticeId);
        Task task = taskRepository.findOne(QTask.task.user.eq(user).and(QTask.task.notice.eq(notice))
                        .and(QTask.task.finished.eq(false))).orElseThrow(() -> new AppException("代办不存在"));
        return TaskOutput.of(task.followed(followed));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public NoticeOutput updateBy(NoticeInput noticeInput) {
        User user = SecurityUtil.getUser();
        Notice notice = SecurityUtil.getNotice(noticeInput.getId());
        if(notice.getCreator() != user) {
            throw new AppException("通知不存在");
        }
        return NoticeOutput.of(notice.update(noticeInput));
    }

    @Override
    public Map<LocalDate, List<NoticeOutput>> pageOrderNoticeBy() {
        User user = SecurityUtil.getUser();
        OrderSpecifier<LocalDateTime> order = new OrderSpecifier<>(Order.DESC, QTask.task.notice.createAt);
        List<Notice> notices = new ArrayList<>();
        taskRepository.findAll(NoticeSpecs.newNotice(user), order).forEach(task -> notices.add(task.getNotice()));
        Map<LocalDate, List<Notice>> group = notices.stream()
                .collect(Collectors.groupingBy(it -> it.getCreateAt().toLocalDate(),
                        LinkedHashMap::new, // 设置为LinkedHashMap使插入保持有序
                        Collectors.toList()
                ));
        return NoticeOutput.of(group);
    }


}
