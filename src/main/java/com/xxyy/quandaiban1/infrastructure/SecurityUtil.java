package com.xxyy.quandaiban1.infrastructure;

import com.xxyy.quandaiban1.application.dtos.LoginUser;
import com.xxyy.quandaiban1.domain.model.*;
import com.xxyy.quandaiban1.domain.repository.GroupRepository;
import com.xxyy.quandaiban1.domain.repository.NoticeRepository;
import com.xxyy.quandaiban1.domain.repository.TaskRepository;
import com.xxyy.quandaiban1.domain.repository.UserRepository;
import com.xxyy.quandaiban1.infrastructure.common.Ioc;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author xy
 * @date 2023-10-07 22:25
 */
@Component
public class SecurityUtil {

    public static User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = Ioc.getBean(UserRepository.class).findOne(QUser.user.username.eq(loginUser.getUsername())).orElse(null);
        if (user==null) {
            throw new AppException("不存在的用户");
        }
        return user;
    }

    public static Notice getNotice(Long id){
        Notice notice = Ioc.getBean(NoticeRepository.class).findById(id).orElse(null);
        if(notice ==null) {
            throw new AppException("不存在此通知");
        }
        return notice;
    }

    public static Task getTask(Long id){
        Task task = Ioc.getBean(TaskRepository.class).findById(id).orElse(null);
        if(task ==null) {
            throw new AppException("不存在此代办");
        }
        return task;
    }

    public static Group getGroup(Long groupId) {
        Group group = Ioc.getBean(GroupRepository.class).findOne(QGroup.group.id.eq(groupId))
                .orElseThrow(() -> new AppException("群组不存在"));
        return group;
    }
}
