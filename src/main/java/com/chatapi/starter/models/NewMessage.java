package com.chatapi.starter.models;

import java.util.Date;

public class NewMessage {

    private String fromId;
    private String message;
    private Date createdAt = new Date();

    public String getFromId() {
        return fromId;
    }

    public NewMessage setFromId(String fromId) {
        this.fromId = fromId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NewMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public NewMessage setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
