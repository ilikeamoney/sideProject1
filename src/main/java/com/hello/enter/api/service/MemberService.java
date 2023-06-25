package com.hello.enter.api.service;


import com.hello.enter.api.domain.Member;
import com.hello.enter.api.domain.MemberEditor;
import com.hello.enter.api.exception.ExistMemberEmail;
import com.hello.enter.api.exception.MemberNotFound;
import com.hello.enter.api.repository.MemberRepository;
import com.hello.enter.api.request.MemberCreate;
import com.hello.enter.api.request.MemberEdit;
import com.hello.enter.api.request.MemberSearch;
import com.hello.enter.api.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성
    public Long create(MemberCreate memberCreate) {
        if (memberRepository.findByEmail(memberCreate.getEmail()).isPresent()) {
            throw new ExistMemberEmail();
        }

        return memberRepository.save(Member.builder()
                .email(memberCreate.getEmail())
                .password(memberCreate.getPassword())
                .name(memberCreate.getName())
                .phone(memberCreate.getPhone())
                .build()).getId();
    }

    // 조회 ID
    @Transactional(readOnly = true)
    public MemberResponse find(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFound::new);

        return new MemberResponse(member);
    }

    // 조회 Email
    @Transactional(readOnly = true)
    public MemberResponse find(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFound::new);

        return new MemberResponse(member);
    }

    // 수정
    public void update(Long memberId, MemberEdit memberEdit) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        MemberEditor memberEditor = MemberEditor.callEditor(memberEdit);

        member.editMember(memberEditor);
    }

    // 삭제
    public void delete(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        memberRepository.delete(findMember);
    }

    // 다수 조회
    public List<MemberResponse> findAll(MemberSearch memberSearch) {
        return memberRepository.getList(memberSearch)
                .stream()
                .map(MemberResponse::new)
                .toList();
    }

}
