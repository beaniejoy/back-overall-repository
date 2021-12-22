package io.beaniejoy.springdatajpabook.controller;

import io.beaniejoy.springdatajpabook.dto.MemberResponse;
import io.beaniejoy.springdatajpabook.entity.Member;
import io.beaniejoy.springdatajpabook.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/converted")
    public Member findMember(@RequestParam("id") Member member) {
        return member;
    }

    @GetMapping("/members")
    public List<MemberResponse> findAllMember(
            @RequestParam("name") String name,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return memberService.findByNameStartingWith(name);
    }

    private MemberResponse toResponse(Member member) {
        if (member == null) return null;
        return new MemberResponse(member.getId(), member.getName(), member.getEmail());
    }
}
