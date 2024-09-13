package com.xxyy.quandaiban1.Api;

import com.xxyy.quandaiban1.application.dtos.UserLoginInput;
import com.xxyy.quandaiban1.application.dtos.UserLoginOutput;
import com.xxyy.quandaiban1.application.service.UserService;
import com.xxyy.quandaiban1.infrastructure.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xy
 * @date 2023-09-25 15:33
 */
@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserApi {

    @Autowired
    UserService service;

    @PostMapping(value = "/login")
    public Result<UserLoginOutput> login(@RequestBody @Valid UserLoginInput input) {
        return Result.data(service.login(input));
    }

    @PutMapping
    public Result<Boolean> joinToGroup(@RequestParam(name = "invited") String invited) {
        return Result.data(service.joinToGroupBy(invited));
    }

}

