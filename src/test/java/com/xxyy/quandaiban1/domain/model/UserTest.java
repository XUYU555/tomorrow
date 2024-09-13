package com.xxyy.quandaiban1.domain.model;

import com.xxyy.quandaiban1.domain.repository.TaskRepository;
import com.xxyy.quandaiban1.infrastructure.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * @author xy
 * @date 2023-11-03 13:04
 */


@ExtendWith(MockitoExtension.class)
class UserTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    SecurityUtil securityUtil;

    @InjectMocks
    User user;

    @Test
    void ignoreTask() {

        Task task = new Task();
        task.setFollowed(true);

        Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(task));

        Task result = user.ignoreTask(1L);
        Assertions.assertFalse(result.isFollowed());
    }
}