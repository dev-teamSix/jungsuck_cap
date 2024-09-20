package com.firstSpring.domain.order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestSendToFlaskDto {
    private String user_input;
    private String cust_id;

    public String getUser_input() {
        return user_input;
    }

    public void setUser_input(String user_input) {
        this.user_input = user_input;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }
}