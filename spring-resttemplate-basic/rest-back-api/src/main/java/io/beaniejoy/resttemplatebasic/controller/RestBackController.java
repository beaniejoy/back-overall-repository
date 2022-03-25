package io.beaniejoy.resttemplatebasic.controller;

import io.beaniejoy.resttemplatebasic.dto.ExampleGetter;
import io.beaniejoy.resttemplatebasic.entity.Member;
import io.beaniejoy.resttemplatebasic.service.ExampleGetterService;
import io.beaniejoy.resttemplatebasic.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/back")
public class RestBackController {

    private final Logger logger = LoggerFactory.getLogger(RestBackController.class);

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
        logger.info("ExampleGetter");
        return exampleGetterService.getExampleGetter();
    }
}
