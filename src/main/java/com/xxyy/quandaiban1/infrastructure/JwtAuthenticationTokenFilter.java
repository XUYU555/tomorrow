package com.xxyy.quandaiban1.infrastructure;

import com.xxyy.quandaiban1.application.dtos.LoginUser;
import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xy
 * @date 2023-09-14 16:40
 */

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if(!Strings.hasText(token)){
            log.info("无token");
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        String userId;
        try {
            Claims claims = jwtUtils.parseToken(token);
            userId=claims.getSubject();
        } catch (Exception e) {
            throw new AppException("token非法");
        }
        long id = Long.parseLong(userId);
        User user = repository.findById(id).orElse(null);
        LoginUser loginUser = new LoginUser(user);
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities()); //添加授权
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
