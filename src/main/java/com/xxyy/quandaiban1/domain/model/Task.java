package com.xxyy.quandaiban1.domain.model;


import com.xxyy.quandaiban1.infrastructure.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author xy
 * @date 2023-09-25 15:04
 */
@Getter
@Entity(name = "task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task extends BaseEntity {

    public Task(Notice notice, User user, Boolean followed) {
        this.notice = notice;
        this.user = user;
        this.followed = followed;
    }

    /**
     * 通知
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    /**
     * 关注状态
     */
    @Column
    private boolean followed;

    /**
     * 已读状态
     */
    @Setter
    @Column
    private boolean readStatus = false;


    /**
     * 完成状态
     */
    @Setter
    @Column
    private boolean finished = false;


    public Task followed(boolean status) {
        this.followed = status;
        this.readStatus = true;
        return this;
    }
}
