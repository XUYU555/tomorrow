package com.xxyy.quandaiban1.application.dtos;

import com.xxyy.quandaiban1.domain.model.Notice;
import com.xxyy.quandaiban1.domain.model.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * @author xy
 * @date 2023-10-10 22:01
 */
@Data
public class NoticeInput {

    private Long id;
    @NotEmpty
    private String content;
    @NotEmpty
    private LocalDateTime deadline;
    @NotEmpty
    private String source;
    @NotEmpty
    private HashSet<Long> userId;

    public Notice mapToEntity(User user) {
        return new Notice(this.content,this.deadline,user,this.source);
    }
}
