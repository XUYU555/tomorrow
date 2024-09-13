package com.xxyy.quandaiban1.sys.controller;


import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.infrastructure.common.Result;
import com.xxyy.quandaiban1.sys.application.dtos.SysUser;
import com.xxyy.quandaiban1.sys.application.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author xy
 * @date 2023-09-15 20:12
 */
@RestController
@RequestMapping(value = "/sys/user")
public class SysUserController {


    @Autowired
    SysUserService service;


    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Result<User> create(@RequestBody SysUser input)
    {
        return Result.data(service.createBy(input));
    }

    @DeleteMapping(value = "/{userId}")
    @PreAuthorize("hasRole('admin')")
    public Result<Boolean> deleteUser(@PathVariable Long userId)
    {
        return Result.data(service.deleteUserBy(userId));
    }


}
