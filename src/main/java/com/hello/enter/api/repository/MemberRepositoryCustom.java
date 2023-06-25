package com.hello.enter.api.repository;

import com.hello.enter.api.domain.Member;
import com.hello.enter.api.request.MemberSearch;
import com.hello.enter.api.response.MemberResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberRepositoryCustom {


    List<Member> getList(MemberSearch memberSearch);
}
