package io.beaniejoy.springdatajpabook.service;

import io.beaniejoy.springdatajpabook.entity.Member;
import io.beaniejoy.springdatajpabook.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void findByNameStartingWith(String name) {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name"));
        // why can't make PageRequest instance by using constructor? (new PageRequest()) <- Sort problem

        Page<Member> result = memberRepository.findByNameStartingWith(name, pageRequest);

        List<Member> members = result.getContent();
        int totalPages = result.getTotalPages();
        long totalElements = result.getTotalElements();
        boolean hasNext = result.hasNext();
    }
}
