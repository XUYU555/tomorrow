package com.xxyy.quandaiban1.Api;

import com.xxyy.quandaiban1.application.dtos.GroupInput;
import com.xxyy.quandaiban1.application.dtos.GroupOutput;
import com.xxyy.quandaiban1.application.dtos.MemberOutput;
import com.xxyy.quandaiban1.application.service.GroupService;
import com.xxyy.quandaiban1.infrastructure.common.PageOutput;
import com.xxyy.quandaiban1.infrastructure.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author xy
 * @date 2023-11-22 19:45
 */
@RestController
@RequestMapping(value = "/group")
@CrossOrigin
public class GroupApi {

    @Autowired
    GroupService service;

    @PostMapping
    public Result<GroupOutput> create(@RequestBody GroupInput input) {
        return Result.data(service.createBy(input));
    }


    @PutMapping(value = "/{memberId}")
    public Result<Boolean> withRole(@RequestParam(name = "adminRole") boolean adminRole, @PathVariable Long memberId) {
        return Result.data(service.withRoleBy(adminRole, memberId));
    }


    @DeleteMapping(value = "/member/{groupId}")
    public Result<Boolean> deleteMember(@RequestParam(value = "usersId") Set<Long> usersId, @PathVariable Long groupId) {
        return Result.data(service.deleteMembers(usersId, groupId));
    }

    @DeleteMapping(value = "/{groupId}")
    public Result<Boolean> deleteGroup(@PathVariable Long groupId) {
        return Result.data(service.deleteGroup(groupId));
    }

    @GetMapping
    public Result<PageOutput<GroupOutput>> pageGroup() {
        Pageable pageable = PageRequest.of(0,10);
        return Result.data(service.pageGroupBy(pageable));
    }

    @GetMapping(value = "/{groupId}")
    public Result<PageOutput<MemberOutput>> pageMember(@PathVariable Long groupId) {
        Pageable pageable = PageRequest.of(0,10);
        return Result.data(service.pageMemberBy(pageable, groupId));
    }

}
