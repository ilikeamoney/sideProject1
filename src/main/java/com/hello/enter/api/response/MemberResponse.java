package com.hello.enter.api.response;

import com.hello.enter.api.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberResponse {

    private Long id;

    private String email;

    private String name;

    private String phone;

    private LocalDate registerDate;

    @Builder
    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.registerDate = member.getRegisterDate();
    }
}
