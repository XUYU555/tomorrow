package com.xxyy.quandaiban1.application.dtos;

import com.xxyy.quandaiban1.domain.model.Member;
import lombok.Data;

/**
 * @author xy
 * @date 2023-11-22 20:49
 */
@Data
public class MemberOutput {

    private UserOutput user;

    private boolean isAdmin;

    public static MemberOutput of(Member member) {
        MemberOutput memberOutput = new MemberOutput();
        memberOutput.user = UserOutput.of(member.getUser());
        memberOutput.isAdmin = member.isAdmin();
        return memberOutput;
    }
}
