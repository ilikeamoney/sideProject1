package com.hello.enter.api.repository;

import com.hello.enter.api.domain.Member;
import com.hello.enter.api.request.MemberSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.hello.enter.api.domain.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> getList(MemberSearch memberSearch) {
        return queryFactory.selectFrom(member)
                .offset(memberSearch.getOffset())
                .limit(memberSearch.getSize())
                .orderBy(member.id.desc())
                .fetch();
    }
}
