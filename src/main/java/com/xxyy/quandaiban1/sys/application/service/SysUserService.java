package com.xxyy.quandaiban1.sys.application.service;

import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.domain.repository.TaskRepository;
import com.xxyy.quandaiban1.domain.repository.UserRepository;
import com.xxyy.quandaiban1.domain.specs.UserSpecs;
import com.xxyy.quandaiban1.infrastructure.AppException;
import com.xxyy.quandaiban1.infrastructure.PasswordUtil;
import com.xxyy.quandaiban1.sys.application.dtos.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xy
 * @date 2023-09-15 20:14
 */

@Service
public class SysUserService {

    @Autowired
    UserRepository repository;
    @Autowired
    TaskRepository taskRepository;


    @Transactional(rollbackFor = Exception.class)
    public User createBy(SysUser input) {
        User user = repository.findOne(UserSpecs.username(input.getUsername())).orElse(null);
        if (user != null) {
            throw new AppException("用户已存在");
        }
        User NewUser = new User(input.username, PasswordUtil.encode(input.getPassword()));
        NewUser.changeROLE("user");
        return repository.save(NewUser);
    }

    public boolean deleteUserBy(Long userId) {
        repository.deleteById(userId);
        return true;
    }



}