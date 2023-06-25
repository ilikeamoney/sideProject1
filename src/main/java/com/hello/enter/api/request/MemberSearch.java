package com.hello.enter.api.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSearch {

    private final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;


    public int getOffset() {
        return (Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }
}
