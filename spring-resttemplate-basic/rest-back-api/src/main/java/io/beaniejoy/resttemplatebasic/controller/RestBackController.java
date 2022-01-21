package io.beaniejoy.resttemplatebasic.controller;

import io.beaniejoy.resttemplatebasic.entity.ExampleGetter;
import io.beaniejoy.resttemplatebasic.entity.Member;
import io.beaniejoy.resttemplatebasic.service.ExampleGetterService;
import io.beaniejoy.resttemplatebasic.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/back")
public class RestBackController {

    private final MemberService memberService;
    private final ExampleGetterService exampleGetterService;

    public RestBackController(MemberService memberService, ExampleGetterService exampleGetterService) {
        this.memberService = memberService;
        this.exampleGetterService = exampleGetterService;
    }

    @GetMapping("/member")
    public Member getMember() {
        return memberService.getMember();
    }

    @GetMapping("/example/getter")
    public ExampleGetter getExampleGetter() {
        return exampleGetterService.getExampleGetter();
    }
}