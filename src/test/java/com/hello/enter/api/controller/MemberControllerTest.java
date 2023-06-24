package com.hello.enter.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.enter.api.domain.Member;
import com.hello.enter.api.repository.MemberRepository;
import com.hello.enter.api.request.MemberCreate;
import com.hello.enter.api.request.MemberEdit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeTest() throws Exception {
        memberRepository.deleteAll();
    }



    @Test
    @DisplayName("회원가입 요청")
    void register() throws Exception {
        // given
        MemberCreate request = MemberCreate.builder()
                .name("짭종")
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .phone("01029419010")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 요청시 예외발생 테스트")
    void registerErrorValid() throws Exception {
        // given
        MemberCreate request = MemberCreate.builder()
                .name("")
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .phone("01029419010")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


    @Test
    @DisplayName("가입한 이메일로 정보 찾기")
    void findEmail() throws Exception {
        // given
        memberRepository.save(Member.builder()
                .name("짭종")
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .phone("01029419010")
                .build());

        String email = "ilikeamoney@gmail.com";

        // expected
        mockMvc.perform(get("/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(email))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원정보 수정")
    void editMember() throws Exception {
        // given
        Long memberId = memberRepository.save(Member.builder()
                .name("짭종")
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .phone("01029419010")
                .build()).getId();

        String json = objectMapper.writeValueAsString(MemberEdit.builder()
                .name("호돌맨")
                .phone("01012345678")
                .password("9876")
                .build());

        // expected
        mockMvc.perform(patch("/update/{id}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteMember() throws Exception {
        // given
        Long memberId = memberRepository.save(Member.builder()
                .name("짭종")
                .email("ilikeamoney@gmail.com")
                .password("1234")
                .phone("01029419010")
                .build()).getId();

        // expected
        mockMvc.perform(delete("/delete{id}", memberId))
                .andExpect(status().isOk())
                .andDo(print());

        Assertions.assertThat(memberRepository.findAll().size())
                .isEqualTo(0);
    }

}