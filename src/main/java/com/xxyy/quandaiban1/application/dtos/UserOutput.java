package com.xxyy.quandaiban1.application.dtos;

import com.xxyy.quandaiban1.domain.model.User;
import lombok.Data;

/**
 * @author xy
 * @date 2023-10-16 21:54
 */
@Data
public class UserOutput {

    private Long id;

    private String username;

    public static UserOutput of(User user) {
        UserOutput userOutput = new UserOutput();
        userOutput.id=user.getId();
        userOutput.username=user.getUsername();
        return userOutput;
    }
}
