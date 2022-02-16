package com.chatapi.starter.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    private String fromId;
    private String toId;
    private List<NewMessage> newMessages;

    public String getFromId() {
        return fromId;
    }

    public Message setFromId(String fromId) {
        this.fromId = fromId;
        return this;
    }

    public String getToId() {
        return toId;
    }

    public Message setToId(String toId) {
        this.toId = toId;
        return this;
    }

    public List<NewMessage> getNewMessages() {
        return newMessages;
    }

    public Message setNewMessages(List<NewMessage> newMessages) {
        this.newMessages = newMessages;
        return this;
    }

}
