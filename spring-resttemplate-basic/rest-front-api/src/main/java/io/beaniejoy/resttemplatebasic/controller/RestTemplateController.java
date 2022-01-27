package io.beaniejoy.resttemplatebasic.controller;

import io.beaniejoy.resttemplatebasic.application.RestTemplateFacade;
import io.beaniejoy.resttemplatebasic.entity.ExampleGetterDto;
import io.beaniejoy.resttemplatebasic.entity.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest")
public class RestTemplateController {

    private final Logger logger = LoggerFactory.getLogger(RestTemplateController.class);

    private final RestTemplateFacade restTemplateFacade;

    public RestTemplateController(RestTemplateFacade restTemplateFacade) {
        this.restTemplateFacade = restTemplateFacade;
    }

    @GetMapping("/member/object")
    public MemberDto getMemberForObjectApi() {
        return restTemplateFacade.getMember();
    }

    @PostMapping("/member/post")
    public void postMember(@RequestBody MemberDto memberDto) {
        logger.info(memberDto.toString());
    }

    @GetMapping("/example/getter")
    public ExampleGetterDto getExampleGetter() {
        return restTemplateFacade.getExampleGetter();
    }

    @PostMapping("/example/getter/post")
    public void postExampleGetter(@RequestBody ExampleGetterDto exampleGetterDto) {
        logger.info(exampleGetterDto.toString());
    }
}
