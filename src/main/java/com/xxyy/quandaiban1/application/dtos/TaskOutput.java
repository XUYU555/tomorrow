package com.xxyy.quandaiban1.application.dtos;

import com.xxyy.quandaiban1.domain.model.Task;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xy
 * @date 2023-10-09 22:03
 */
@Data
public class TaskOutput {

    private Long id;

    private String content;

    private LocalDateTime deadline;

    private String source;

    private boolean completed;

    private String username;

    private boolean followed;

    private  boolean finished;

    public static TaskOutput of(Task task){
        TaskOutput taskOutput = new TaskOutput();
        taskOutput.id=task.getId();
        taskOutput.content=task.getNotice().getContent();
        taskOutput.deadline=task.getNotice().getDeadline();
        taskOutput.source=task.getNotice().getSource();
        taskOutput.completed=task.getNotice().isCompleted();
        taskOutput.username=task.getUser().getUsername();
        taskOutput.followed=task.isFollowed();
        taskOutput.finished=task.isFinished();
        return taskOutput;
    }
}
