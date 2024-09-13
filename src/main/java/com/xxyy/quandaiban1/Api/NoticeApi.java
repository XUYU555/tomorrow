package com.xxyy.quandaiban1.Api;

import com.xxyy.quandaiban1.application.dtos.NoticeInput;
import com.xxyy.quandaiban1.application.dtos.NoticeOutput;
import com.xxyy.quandaiban1.application.dtos.TaskOutput;
import com.xxyy.quandaiban1.application.service.NoticeService;
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
 * @date 2023-09-27 20:41
 */

@RestController
@RequestMapping(value = "/notice")
@CrossOrigin
public class NoticeApi {

    @Autowired
    NoticeService service;


    /**
     * 1 为我发布的通知    0 为我接的通知
     * @param type
     * @return
     */
    @GetMapping(value = "/{type}")
    public Result<PageOutput<NoticeOutput>> pageNotice(@PathVariable int type) {
        Pageable pageable = PageRequest.of(0,10);
        return Result.data(service.pageNoticeBy(pageable,type));
    }


    @GetMapping(value = "/order")
    public Result<Map<LocalDate, List<NoticeOutput>>> pageOrderNotice() {
        return Result.data(service.pageOrderNoticeBy());
    }


    @PostMapping
    public Result<NoticeOutput> create(@RequestBody NoticeInput noticeInput) {
        return Result.data(service.createBy(noticeInput));
    }


    @PutMapping
    public Result<NoticeOutput> update(@RequestBody NoticeInput noticeInput) {
        return Result.data(service.updateBy(noticeInput));
    }


    @PutMapping(value = "/follow/{noticeId}/{followed}")
    public Result<TaskOutput> followed(@PathVariable Long noticeId,@PathVariable boolean followed) {
        return Result.data(service.followBy(noticeId,followed));
    }

}
