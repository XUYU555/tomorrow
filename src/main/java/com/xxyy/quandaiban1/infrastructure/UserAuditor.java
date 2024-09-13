package com.xxyy.quandaiban1.infrastructure;

import com.xxyy.quandaiban1.application.dtos.LoginUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author xy
 * @date 2023-09-15 12:20
 * jpa审计：自动获取用户
 */
@Configuration
public class UserAuditor implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            return Optional.ofNullable(loginUser.getUsername());
        } catch (Exception e) {
            throw new AppException("获得用户信息错误");
        }
    }
}
