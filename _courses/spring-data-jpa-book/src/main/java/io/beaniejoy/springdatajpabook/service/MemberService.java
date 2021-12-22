package io.beaniejoy.springdatajpabook.service;

import io.beaniejoy.springdatajpabook.dto.MemberResponse;
import io.beaniejoy.springdatajpabook.entity.Member;
import io.beaniejoy.springdatajpabook.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> findByNameStartingWith(String name) {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name").descending());

        Page<Member> result = memberRepository.findByNameStartingWith(name, pageRequest);

        List<Member> members = result.getContent();
        int totalPages = result.getTotalPages();
        long totalElements = result.getTotalElements();
        boolean hasNext = result.hasNext();

        logger.info(String.format("totalPages: %d, totalElements: %d", totalPages, totalElements));

        return members.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MemberResponse toResponse(Member member) {
        if (member == null) return null;
        return new MemberResponse(member.getId(), member.getName(), member.getEmail());
    }
}
