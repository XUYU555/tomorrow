package com.xxyy.quandaiban1.domain.model;

import com.xxyy.quandaiban1.application.dtos.NoticeInput;
import com.xxyy.quandaiban1.domain.repository.TaskRepository;
import com.xxyy.quandaiban1.domain.specs.TaskSPecs;
import com.xxyy.quandaiban1.infrastructure.AppException;
import com.xxyy.quandaiban1.infrastructure.common.BaseEntity;
import com.xxyy.quandaiban1.infrastructure.common.Ioc;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author xy
 * @date 2023-09-25 15:01
 */
@Getter
@Entity(name = "notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

    public Notice(String content, LocalDateTime deadline, User user, String source) {
        this.content = content;
        this.deadline = deadline;
        this.creator = user;
        this.source = source;
    }

    /**
     * 内容
     */
    @Column(length = 30)
    private String content;

    /**
     * 截至时间
     */
    @Column
    private LocalDateTime deadline;

    /**
     * 发布人
     */
    @OneToOne
    private User creator;


    /**
     * 来源
     */
    @Column
    private String source;

    /**
     * 完成状态
     */
    @Setter
    @Column
    private boolean completed = false;


    public Task add(Notice notice, User user, boolean followed) {
        Task task = Ioc.getBean(TaskRepository.class).findOne(TaskSPecs.already(user, notice)).orElse(null);
        if(task != null) {
            throw new AppException("代办已存在");
        }
        return new Task(notice,user,followed);
    }


    public Notice update(NoticeInput noticeInput) {
            this.deadline=noticeInput.getDeadline();
            this.source=noticeInput.getSource();
            this.content=noticeInput.getContent();
            return this;
    }


    public List<String> peopleList(boolean followed, boolean finished) {
        List<String> people = Ioc.getBean(TaskRepository.class)
                .findAll(TaskSPecs.peopleList(this, followed, finished))
                .stream().map(Task::getUser).map(User::getUsername).collect(Collectors.toList());
        return people;
    }


    public List<String> total() {
        List<String> total = new ArrayList<>();
        Ioc.getBean(TaskRepository.class).findAll(QTask.task.notice.eq(this))
                .forEach(task -> total.add(task.getUser().getUsername()));
        return total;
    }
}
