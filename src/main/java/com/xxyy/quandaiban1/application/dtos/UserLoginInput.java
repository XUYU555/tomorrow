package com.xxyy.quandaiban1.application.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author xy
 * @date 2023-09-13 13:51
 */

@Data
public class UserLoginInput {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
