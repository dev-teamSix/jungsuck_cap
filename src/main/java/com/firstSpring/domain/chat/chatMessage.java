package com.firstSpring.domain.chat;

import java.util.List;

public class chatMessage {
    private String user_input;
    private List<Message> messages;

    // Getters and Setters
    public String getUser_input() {
        return user_input;
    }

    public void setUser_input(String user_input) {
        this.user_input = user_input;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // 내부 클래스 정의
    public static class Message {
        private String role;
        private String content;

        // Getters and Setters
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
