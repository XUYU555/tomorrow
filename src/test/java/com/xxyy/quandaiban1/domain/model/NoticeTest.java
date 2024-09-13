package com.xxyy.quandaiban1.domain.model;

import com.xxyy.quandaiban1.domain.repository.TaskRepository;
import com.xxyy.quandaiban1.infrastructure.common.Ioc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

/**
 * @author xy
 * @date 2023-11-03 20:06
 */
@ExtendWith(MockitoExtension.class)
class NoticeTest {

    // Ioc类中的ctx，需要设置，直接mock会出现空指针
    @Spy
    ApplicationContext ctx;
    @Mock
    TaskRepository taskRepository;
    @InjectMocks
    Notice notice;

    @Test
    void followed() {
        // 准备数据
        Ioc ioc = new Ioc();
        // 设置ctx
        ioc.setApplicationContext(ctx);
        User user = new User();
        Task task1 = new Task(notice,user,true);

        // 打桩
        Mockito.when(ctx.getBean(TaskRepository.class)).thenReturn(taskRepository);
        Mockito.when(taskRepository.findOne(Mockito.any(Specification.class))).thenReturn(Optional.empty());

        //执行测试代码
        Task task2 = notice.add(notice, user, false);

        // 断言
        Assertions.assertNotNull(task1);
        Assertions.assertNotNull(task2);
        Assertions.assertTrue(task1.isFollowed());
        Assertions.assertFalse(task2.isFollowed());
        Assertions.assertNotEquals(task1.isFollowed(),task2.isFollowed());
    }
}