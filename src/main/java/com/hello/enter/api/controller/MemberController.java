package com.hello.enter.api.controller;

import com.hello.enter.api.request.MemberCreate;
import com.hello.enter.api.request.MemberEdit;
import com.hello.enter.api.response.MemberResponse;
import com.hello.enter.api.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/")
    public void signUp(@RequestBody @Valid MemberCreate memberCreate) {
        memberService.create(memberCreate);
    }

    @GetMapping("/find")
    public MemberResponse find(@RequestBody String email) {
        return memberService.find(email);
    }

    @PatchMapping("/update/{id}")
    public void edit(@PathVariable Long id, @RequestBody MemberEdit memberEdit) {
        memberService.update(id, memberEdit);
    }

    @DeleteMapping("/delete{id}")
    public void delete(@PathVariable Long id) {
        memberService.delete(id);
    }

}
