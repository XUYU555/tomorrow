package com.xxyy.quandaiban1.application.service;

import com.xxyy.quandaiban1.application.dtos.NoticeInput;
import com.xxyy.quandaiban1.application.dtos.NoticeOutput;
import com.xxyy.quandaiban1.application.dtos.TaskOutput;
import com.xxyy.quandaiban1.infrastructure.common.PageOutput;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


/**
 * @author xy
 * @date 2023-09-27 20:41
 */
public interface NoticeService {

    public PageOutput<NoticeOutput> pageNoticeBy(Pageable pageable, int type);

    NoticeOutput createBy(NoticeInput noticeInput);

    TaskOutput followBy(Long noticeId, boolean followed);

    NoticeOutput updateBy(NoticeInput noticeInput);

    Map<LocalDate, List<NoticeOutput>> pageOrderNoticeBy();
}
