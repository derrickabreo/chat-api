package com.chatapi.starter.models;

public class ConversationList {

    String userId;

    public ConversationList() {
    }

    public ConversationList(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ConversationList{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
