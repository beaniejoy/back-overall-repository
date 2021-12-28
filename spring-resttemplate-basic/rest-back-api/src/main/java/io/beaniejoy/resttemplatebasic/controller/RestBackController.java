package io.beaniejoy.resttemplatebasic.controller;

import io.beaniejoy.resttemplatebasic.entity.Member;
import io.beaniejoy.resttemplatebasic.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/back")
public class RestBackController {

    private final MemberService memberService;

    public RestBackController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member")
    public Member getMember() {
        return memberService.getMember();
    }
}
