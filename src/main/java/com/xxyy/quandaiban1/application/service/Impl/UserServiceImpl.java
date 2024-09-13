package com.xxyy.quandaiban1.application.service.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xxyy.quandaiban1.application.dtos.LoginUser;
import com.xxyy.quandaiban1.application.dtos.UserLoginInput;
import com.xxyy.quandaiban1.application.dtos.UserLoginOutput;
import com.xxyy.quandaiban1.application.service.UserService;
import com.xxyy.quandaiban1.domain.model.Group;
import com.xxyy.quandaiban1.domain.model.QGroup;
import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.domain.repository.MemberRepository;
import com.xxyy.quandaiban1.domain.repository.UserRepository;
import com.xxyy.quandaiban1.domain.specs.UserSpecs;
import com.xxyy.quandaiban1.infrastructure.AppException;
import com.xxyy.quandaiban1.infrastructure.JwtUtils;
import com.xxyy.quandaiban1.infrastructure.SecurityUtil;
import com.xxyy.quandaiban1.infrastructure.common.Ioc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author xy
 * @date 2023-09-26 20:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository, AuthenticationManager manager, JwtUtils jwtUtils, JPAQueryFactory queryFactory) {
        this.userRepository = repository;
        this.manager = manager;
        this.jwtUtils = jwtUtils;
        this.queryFactory = queryFactory;
    }

    private final UserRepository userRepository;
    private final AuthenticationManager manager;
    private final JwtUtils jwtUtils;
    private final JPAQueryFactory queryFactory;

    @Override
    public UserLoginOutput login(UserLoginInput input) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(input.getUsername(),input.getPassword());
        // 通过AuthenticationManager的authenticate方法来进行用户认证
        Authentication authenticate = manager.authenticate(authenticationToken);
        // 判断是否验证成功
        if(Objects.isNull(authenticate)){
            throw new AppException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user = userRepository.findOne(UserSpecs.username(loginUser.getUsername())).orElse(null);
        String token = jwtUtils.createToken(user.getId());
        return UserLoginOutput.of(user,token);
    }

    @Override
    @Transactional( rollbackFor = Exception.class )
    public Boolean joinToGroupBy(String invited) {
        Group group = queryFactory.selectFrom(QGroup.group).where(QGroup.group.invited.eq(invited)).fetchOne();
        if( group == null ) {
            throw new AppException("群组不存在");
        }
        Ioc.getBean(MemberRepository.class).save(group.addMember(SecurityUtil.getUser()));
        return true;
    }


}
