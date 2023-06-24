package com.hello.enter.api.service;

import com.hello.enter.api.domain.Member;
import com.hello.enter.api.exception.ExistMemberEmail;
import com.hello.enter.api.exception.MemberNotFound;
import com.hello.enter.api.repository.MemberRepository;
import com.hello.enter.api.request.MemberCreate;
import com.hello.enter.api.request.MemberEdit;
import com.hello.enter.api.response.MemberResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    @DisplayName("테스트전 모두 삭제")
    void beforeTest() throws Exception {
        memberRepository.deleteAll();
    }


    @Test
    @DisplayName("회원 생성")
    void create() throws Exception {
        // given
        MemberCreate createMember = MemberCreate.builder()
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .name("짭종")
                .phone("01029419010")
                .build();

        // when
        memberService.create(createMember);


        // then
        Assertions.assertThat(memberRepository.count())
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("회원 조회")
    void find() throws Exception {
        // given
        MemberCreate createMember = MemberCreate.builder()
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .name("짭종")
                .phone("01029419010")
                .build();

        Long memberId = memberService.create(createMember);

        // when
        MemberResponse findMember = memberService.find(memberId);


        // then
        Assertions.assertThat(findMember.getEmail())
                .isEqualTo("ilikeamoney@gmail.com");

        Assertions.assertThat(findMember.getName())
                .isEqualTo("짭종");

        Assertions.assertThat(findMember.getPhone())
                .isEqualTo("01029419010");

    }

    @Test
    @DisplayName("멤버 수정")
    void edit() throws Exception {
        // given
        MemberCreate createMember = MemberCreate.builder()
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .name("짭종")
                .phone("01029419010")
                .build();

        Long memberId = memberService.create(createMember);

        MemberEdit editMember = MemberEdit.builder()
                .password("5678")
                .phone("01012345678")
                .name("호돌맨")
                .build();

        // when
        memberService.update(memberId, editMember);

        MemberResponse findMember = memberService.find(memberId);

        Assertions.assertThat(findMember.getPhone())
                .isEqualTo("01012345678");

        Assertions.assertThat(findMember.getName())
                .isEqualTo("호돌맨");
    }

    @Test
    @DisplayName("회원 탈퇴")
    void delete() throws Exception {
        // given
        MemberCreate createMember = MemberCreate.builder()
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .name("짭종")
                .phone("01029419010")
                .build();

        Long memberId = memberService.create(createMember);

        // when
        memberService.delete(memberId);

        // then
        Assertions.assertThat(memberRepository.findAll().size())
                .isEqualTo(0);
    }


    @Test
    @DisplayName("존재하지 않는 멤버 조회시 예외")
    void notFoundMember() throws Exception {
        // given
        MemberCreate createMember = MemberCreate.builder()
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .name("짭종")
                .phone("01029419010")
                .build();

        memberService.create(createMember);

        // expected
        Assertions.assertThatThrownBy(() -> memberService.find(2L))
                .isInstanceOf(MemberNotFound.class);
    }

    @Test
    @DisplayName("이미 가입된 이메일을 사용시 예외발생")
    void existEmailError() throws Exception {
        // given
        MemberCreate createMember = MemberCreate.builder()
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .name("짭종")
                .phone("01029419010")
                .build();

        memberService.create(createMember);

        MemberCreate duplicateEmail = MemberCreate.builder()
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .name("짭종")
                .phone("01029419010")
                .build();

        // expected
        Assertions.assertThatThrownBy(() -> memberService.create(duplicateEmail))
                .isInstanceOf(ExistMemberEmail.class);
    }
}