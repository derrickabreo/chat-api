package com.chatapi.starter.models;

public class Conversation {

    private String fromId;
    private String toId;

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "fromId='" + fromId + '\'' +
                ", toID='" + toId + '\'' +
                '}';
    }
}
