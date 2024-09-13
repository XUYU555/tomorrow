package com.xxyy.quandaiban1.application.dtos;

import com.xxyy.quandaiban1.domain.model.Notice;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xy
 * @date 2023-10-14 10:56
 */
@Data
public class NoticeOutput {

    private Long id;

    private String content;

    private LocalDateTime deadline;

    private String source;

    private String creator;

    private List<String> followed;

    private List<String> looked;

    private List<String> total;

    private List<String> finished;



    public static NoticeOutput of(Notice notice){
        NoticeOutput noticeOutput = new NoticeOutput();
        noticeOutput.id=notice.getId();
        noticeOutput.content=notice.getContent();
        noticeOutput.deadline=notice.getDeadline();
        noticeOutput.source=notice.getSource();
        noticeOutput.creator=notice.getCreator().getUsername();
        return noticeOutput;
    }

    public static Map<LocalDate, List<NoticeOutput>> of(Map<LocalDate, List<Notice>> notices) {
        Map<LocalDate, List<NoticeOutput>> order = new LinkedHashMap<>();
        notices.forEach((key, value) -> {
            List<NoticeOutput> noticeOutputs = value.stream().map(NoticeOutput::of).collect(Collectors.toList());
            order.put(key,noticeOutputs);
        });
        return order;
    }

    public static NoticeOutput ofMyNotice(Notice notice){
        NoticeOutput myNoticeOutput = new NoticeOutput();
        myNoticeOutput.id=notice.getId();
        myNoticeOutput.content=notice.getContent();
        myNoticeOutput.deadline=notice.getDeadline();
        myNoticeOutput.source=notice.getSource();
        myNoticeOutput.creator=notice.getCreator().getUsername();
        myNoticeOutput.total=notice.total();
        myNoticeOutput.followed=notice.peopleList(true,false);
        myNoticeOutput.looked=notice.peopleList(false,false);
        myNoticeOutput.finished=notice.peopleList(true,true);
        return myNoticeOutput;
    }

}
