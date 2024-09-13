package com.xxyy.quandaiban1.sys.application.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author xy
 * @date 2023-09-15 20:13
 */
@Data
public class SysUser {

    @NotEmpty
    public String username;

    @NotEmpty
    public String password;


}
