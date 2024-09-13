package com.xxyy.quandaiban1.Api;

import com.xxyy.quandaiban1.application.dtos.NoticeOutput;
import com.xxyy.quandaiban1.application.dtos.TaskOutput;
import com.xxyy.quandaiban1.application.service.TaskService;
import com.xxyy.quandaiban1.infrastructure.common.PageOutput;
import com.xxyy.quandaiban1.infrastructure.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author xy
 * @date 2023-09-27 20:31
 */

@RestController
@RequestMapping(value = "/task")
@CrossOrigin
public class TaskApi {

    @Autowired
    TaskService service;

    @GetMapping
    public Result<PageOutput<TaskOutput>> pageTask() {
        Pageable pageable = PageRequest.of(0,10);
        return Result.data(service.pageTaskBy(pageable));
    }

    @PutMapping(value = "/finished/{taskId}/{finished}")
    public Result<TaskOutput> finished(@PathVariable Long taskId,@PathVariable boolean finished) {
        return Result.data(service.finishedBy(taskId, finished));
    }

    @PutMapping(value = "/neglect/{taskId}")
    public Result<TaskOutput> neglectTask(@PathVariable Long taskId) {
        return Result.data(service.neglectTaskBy(taskId));
    }


    @GetMapping(value = "/order")
    public Result<Map<LocalDate,List<NoticeOutput>>> pageOrderTask() {
        return Result.data(service.pageOrderTaskBy());
    }
}
