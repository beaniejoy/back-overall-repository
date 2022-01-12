package io.beaniejoy.resttemplatebasic.service;

import io.beaniejoy.resttemplatebasic.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public Member getMember() {
        return new Member(1L, "beanie", "beanie@example.com", "address1");
    }
}
