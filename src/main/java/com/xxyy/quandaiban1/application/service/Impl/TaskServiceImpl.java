package com.xxyy.quandaiban1.application.service.Impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.xxyy.quandaiban1.application.dtos.NoticeOutput;
import com.xxyy.quandaiban1.application.dtos.TaskOutput;
import com.xxyy.quandaiban1.application.service.TaskService;
import com.xxyy.quandaiban1.domain.model.Notice;
import com.xxyy.quandaiban1.domain.model.QTask;
import com.xxyy.quandaiban1.domain.model.Task;
import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.domain.repository.TaskRepository;
import com.xxyy.quandaiban1.domain.specs.TaskSPecs;
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

/**
 * @author xy
 * @date 2023-09-27 20:36
 */

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private final TaskRepository taskRepository;


    @Override
    public PageOutput<TaskOutput> pageTaskBy(Pageable pageable) {
        User user = SecurityUtil.getUser();
        BooleanBuilder builder = TaskSPecs.findTask(user);
        Page<TaskOutput> tasks = taskRepository.findAll(builder, pageable)
                .map(TaskOutput::of);
        return PageOutput.of(tasks);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public TaskOutput finishedBy(Long taskId, boolean finished) {
        Task task = lookTask(taskId);// 数据校验
        task.setFinished(finished);
        return TaskOutput.of(task);
    }



    @Override
    public Map<LocalDate, List<NoticeOutput>> pageOrderTaskBy() {
        User user = SecurityUtil.getUser();
        List<Notice> notices = new ArrayList<>();
        OrderSpecifier<LocalDateTime> order = new OrderSpecifier<>(Order.ASC, QTask.task.notice.deadline);
        taskRepository.findAll(TaskSPecs.findTask(user), order).forEach(task -> notices.add(task.getNotice()));
        Map<LocalDate, List<Notice>> group = notices.stream()
                .collect(Collectors.groupingBy(it -> it.getDeadline().toLocalDate(),
                        LinkedHashMap::new, // 设置为LinkedHashMap使插入保持有序
                        Collectors.toList()
                ));
      return NoticeOutput.of(group);
    }

    @Override
    public TaskOutput neglectTaskBy(Long taskId) {
        Task task = lookTask(taskId);
        task.setFinished(false);
        task.followed(false);
        return TaskOutput.of(task);
    }


    public Task lookTask(Long taskId) {
        User user = SecurityUtil.getUser();
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QTask.task.followed.eq(true));
        builder.and(QTask.task.user.eq(user));
        builder.and(QTask.task.id.eq(taskId));
        Task task = taskRepository.findOne(builder).orElse(null);
        if(task == null) {
            throw new AppException("代办不存在");
        }
        return task;
    }
}
