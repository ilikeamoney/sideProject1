package com.hello.enter.api.domain;


import com.hello.enter.api.request.MemberEdit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private LocalDate registerDate;

    @Builder
    public Member(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.registerDate = LocalDate.now();
    }

    public void editMember(MemberEditor memberEditor) {
        this.name = memberEditor.getName() != null ? memberEditor.getName() : this.getName();
        this.password = memberEditor.getPassword() != null ? memberEditor.getPassword() : this.getPassword();
        this.phone = memberEditor.getPhone() != null ? memberEditor.getPhone() : this.getPhone();
    }

}
