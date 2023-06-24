package com.hello.enter.api.request;

import com.hello.enter.api.repository.MemberRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class MemberCreate {

    @NotBlank(message = "이름을 입력해 주세요")
    private String name;

    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;

    @NotBlank(message = "휴대전화 번호를 입력해 주세요")
    private String phone;

    @Builder
    public MemberCreate(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

}
