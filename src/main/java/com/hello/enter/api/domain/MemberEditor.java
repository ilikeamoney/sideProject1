package com.hello.enter.api.domain;

import com.hello.enter.api.request.MemberEdit;
import lombok.Getter;

@Getter
public class MemberEditor {
    private String password;

    private String phone;

    private String name;

    public MemberEditor(String password, String phone, String name) {
        this.password = password;
        this.phone = phone;
        this.name = name;
    }

    public static MemberEditor callEditor(MemberEdit memberEdit) {
        return new MemberEditorBuilder()
                .name(memberEdit.getName())
                .password(memberEdit.getPassword())
                .phone(memberEdit.getPhone())
                .build();
    }

    public static class MemberEditorBuilder {
        private String password;
        private String phone;
        private String name;

        public MemberEditorBuilder password(String password) {
            this.password = password;
            return this;
        }

        public MemberEditorBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public MemberEditorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MemberEditor build() {
            return new MemberEditor(this.password, this.phone, this.name);
        }
    }
}
