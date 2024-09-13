package com.xxyy.quandaiban1.infrastructure.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * @author xy
 * @date 2023-09-22 18:46
 */
@Data
public class PageOutput<T> {
    /**
     * 页内元素
     */
    private Collection<T> content;
    /**
     * 页码
     */
    private int pageNumber;
    /**
     * 页长
     */
    private int pageSize;
    /**
     * 元素总页数
     */
    private int totalPages;
    /**
     * 元素总个数
     */
    private long totalElements;

    public static <T> PageOutput<T> of(Page<T> source) {
        PageOutput<T> output = new PageOutput<>();
        output.setContent(source.getContent());
        output.setPageNumber(source.getNumber());
        output.setPageSize(source.getSize());
        output.setTotalPages(source.getTotalPages());
        output.setTotalElements(source.getTotalElements());
        return output;
    }
}
