package com.hello.enter.api.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEdit {

    private String password;

    private String phone;

    private String name;

    @Builder
    public MemberEdit(String password, String phone, String name) {
        this.password = password;
        this.phone = phone;
        this.name = name;
    }
}
