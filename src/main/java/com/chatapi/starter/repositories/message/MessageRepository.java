package com.chatapi.starter.repositories.message;

import com.chatapi.starter.models.Conversation;
import com.chatapi.starter.models.ConversationList;
import com.chatapi.starter.models.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository {

    Message sendMessage(Message message);

    List<Message> getAllMessage(Conversation message);

    List<ConversationList> getMessageWithUsers(String id);
}
