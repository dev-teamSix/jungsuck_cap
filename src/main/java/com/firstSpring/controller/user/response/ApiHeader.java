package com.firstSpring.controller.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiHeader {

    private int code;
    private String message;
}