package com.xxyy.quandaiban1.application.service;

import com.xxyy.quandaiban1.application.dtos.NoticeOutput;
import com.xxyy.quandaiban1.application.dtos.TaskOutput;
import com.xxyy.quandaiban1.infrastructure.common.PageOutput;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author xy
 * @date 2023-09-27 20:35
 */


public interface TaskService {

    PageOutput<TaskOutput> pageTaskBy(Pageable pageable);

    TaskOutput finishedBy(Long taskId, boolean finished);


    Map<LocalDate, List<NoticeOutput>> pageOrderTaskBy();

    TaskOutput neglectTaskBy(Long taskId);
}
