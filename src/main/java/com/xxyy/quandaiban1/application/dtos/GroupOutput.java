package com.xxyy.quandaiban1.application.dtos;

import com.xxyy.quandaiban1.domain.model.Group;
import com.xxyy.quandaiban1.infrastructure.common.PageOutput;
import lombok.Data;

/**
 * @author xy
 * @date 2023-11-22 20:34
 */
@Data
public class GroupOutput {

    private Long id;

    private String groupName;

    private String invited;

    private UserOutput creator;

    private PageOutput<MemberOutput> members;

    public static GroupOutput of(Group group, PageOutput<MemberOutput> members) {
        GroupOutput groupOutput = new GroupOutput();
        groupOutput.id = group.getId();
        groupOutput.groupName = group.getName();
        groupOutput.invited = group.getInvited();
        groupOutput.creator = UserOutput.of(group.getCreator());
        groupOutput.members = members;
        return groupOutput;
    }
}
