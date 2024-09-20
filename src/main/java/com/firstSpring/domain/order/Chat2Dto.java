package com.firstSpring.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Chat2Dto {
    private final String role_data;
    private final String content_data;
}