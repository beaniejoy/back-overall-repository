package io.beaniejoy.springdatajpabook.controller;

import io.beaniejoy.springdatajpabook.dto.MemberResponse;
import io.beaniejoy.springdatajpabook.entity.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/member")
    public MemberResponse findMember(@RequestParam("id") Member member) {
        return toResponse(member);
    }

    private MemberResponse toResponse(Member member) {
        if (member == null) return null;
        return new MemberResponse(member.getId(), member.getName(), member.getEmail());
    }
}
