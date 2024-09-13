package com.xxyy.quandaiban1.domain.model;

import com.xxyy.quandaiban1.infrastructure.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author xy
 * @date 2023-09-25 14:55
 */


@Getter
@Entity(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Column(unique = true,updatable = false)
    private String username;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 用户角色
     */
    @Column
    private String role;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void changeROLE(String role)
    {
        role="ROLE_"+role;
        this.role=role;
    }

}
