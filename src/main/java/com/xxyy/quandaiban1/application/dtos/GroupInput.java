package com.xxyy.quandaiban1.application.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @author xy
 * @date 2023-11-14 19:38
 */
@Data
public class GroupInput {

    @NotEmpty
    private String groupName;

    private String invited;

    @NotEmpty
    private Set<Long> userId;

}
