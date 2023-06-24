package com.hello.enter.api.service;


import com.hello.enter.api.domain.Member;
import com.hello.enter.api.domain.MemberEditor;
import com.hello.enter.api.exception.ExistMemberEmail;
import com.hello.enter.api.exception.MemberNotFound;
import com.hello.enter.api.repository.MemberRepository;
import com.hello.enter.api.request.MemberCreate;
import com.hello.enter.api.request.MemberEdit;
import com.hello.enter.api.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


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

    @Transactional(readOnly = true)
    public MemberResponse find(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFound::new);

        return new MemberResponse(member);
    }

    @Transactional(readOnly = true)
    public MemberResponse find(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFound::new);

        return new MemberResponse(member);
    }


    public void update(Long memberId, MemberEdit memberEdit) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        MemberEditor memberEditor = MemberEditor.callEditor(memberEdit);

        member.editMember(memberEditor);
    }


    public void delete(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        memberRepository.delete(findMember);
    }
}
