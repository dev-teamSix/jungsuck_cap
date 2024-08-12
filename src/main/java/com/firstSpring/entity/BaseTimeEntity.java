package com.firstSpring.entity;

import java.time.LocalDateTime;

public abstract class BaseTimeEntity {
    private LocalDateTime regTime;
    public LocalDateTime updateTime;

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
