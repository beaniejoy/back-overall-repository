package io.beaniejoy.jacksonbindtest.controller;

import io.beaniejoy.jacksonbindtest.dto.MemberRequestDto1;
import io.beaniejoy.jacksonbindtest.dto.MemberRequestDto2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @PostMapping("/one")
    public MemberRequestDto1 postMember1(@RequestBody MemberRequestDto1 requestDto) {
        logger.info(requestDto.toString());
        return requestDto;
    }

    @PostMapping("/two")
    public MemberRequestDto2 postMember2(@RequestBody MemberRequestDto2 requestDto) {
        logger.info(requestDto.toString());
        return requestDto;
    }
}
