package com.xxyy.quandaiban1.application.service.Impl;

import com.xxyy.quandaiban1.application.dtos.LoginUser;
import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.xxyy.quandaiban1.domain.specs.UserSpecs;



/**
 * @author xy
 * @date 2023-09-13 21:50
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findOne(UserSpecs.username(username)).orElse(null);
        if(user==null){
            throw new UsernameNotFoundException("没有该用户");
        }
        return new LoginUser(user);
    }
}
