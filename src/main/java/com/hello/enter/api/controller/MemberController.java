package com.hello.enter.api.controller;

import com.hello.enter.api.request.MemberCreate;
import com.hello.enter.api.request.MemberEdit;
import com.hello.enter.api.request.MemberSearch;
import com.hello.enter.api.response.MemberResponse;
import com.hello.enter.api.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/")
    public void signUp(@RequestBody @Valid MemberCreate memberCreate) {
        memberService.create(memberCreate);
    }

    @GetMapping("/find/{id}")
    public MemberResponse find(@PathVariable Long id) {
        return memberService.find(id);
    }

    @PatchMapping("/update/{id}")
    public void edit(@PathVariable Long id, @RequestBody MemberEdit memberEdit) {
        memberService.update(id, memberEdit);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        memberService.delete(id);
    }

    @GetMapping("/members")
    public List<MemberResponse> gets(@ModelAttribute MemberSearch memberSearch) {
        return memberService.findAll(memberSearch);
    }

}
