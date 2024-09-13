package com.xxyy.quandaiban1.application.dtos;

import com.xxyy.quandaiban1.domain.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xy
 * @date 2023-09-13 13:48
 */
@Data
@NoArgsConstructor
public class UserLoginOutput {

    private Long id;
    private String username;
    private String token;

    public static UserLoginOutput of(User user, String token){
        UserLoginOutput userLoginOutput = new UserLoginOutput();
        userLoginOutput.id=user.getId();
        userLoginOutput.username=user.getUsername();
        userLoginOutput.token=token;
        return userLoginOutput;
    }
}
