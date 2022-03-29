package io.beaniejoy.jacksonbindtest.controller;

import io.beaniejoy.jacksonbindtest.dto.*;
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

    @PostMapping("/three")
    public MemberRequestDto3 postMember3(@RequestBody MemberRequestDto3 requestDto) {
        logger.info(requestDto.toString());
        return requestDto;
    }

    @PostMapping("/four")
    public MemberRequestDto4 postMember4(@RequestBody MemberRequestDto4 requestDto) {
        logger.info(requestDto.toString());
        return requestDto;
    }

    @PostMapping("/five")
    public MemberRequestDto5 postMember5(@RequestBody MemberRequestDto5 requestDto) {
        logger.info(requestDto.toString());
        return requestDto;
    }
}
