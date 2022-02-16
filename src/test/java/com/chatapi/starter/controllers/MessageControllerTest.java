package com.chatapi.starter.controllers;

import com.chatapi.starter.models.Conversation;
import com.chatapi.starter.models.ConversationList;
import com.chatapi.starter.models.Message;
import com.chatapi.starter.models.NewMessage;
import com.chatapi.starter.repositories.message.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageRepository messageRepository;

    @Test
    public void sendMessageTest() {
        Message message = new Message();
        List<NewMessage> newMessages = new ArrayList<>();
        NewMessage newMessage = new NewMessage();
        newMessage.setMessage("Hello");
        newMessage.setCreatedAt(new Date());
        newMessage.setFromId("abc123");
        newMessages.add(newMessage);
        message.setNewMessages(newMessages);
        message.setFromId("abc123");
        message.setToId("def456");

        when(messageRepository.sendMessage(message)).thenReturn(message);
        ResponseEntity<Message> actualResponse =messageController.sendMessage(message);
        assertNotNull(actualResponse);
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    public void getMessageTest() {
        Message message = new Message();
        List<NewMessage> newMessages = new ArrayList<>();
        NewMessage newMessage = new NewMessage();
        newMessage.setMessage("Hello");
        newMessage.setCreatedAt(new Date());
        newMessage.setFromId("abc123");
        newMessages.add(newMessage);
        message.setNewMessages(newMessages);
        message.setFromId("abc123");
        message.setToId("def456");

        Conversation conversation = new Conversation();
        conversation.setFromId("abc123");
        conversation.setToId("def456");

        when(messageRepository.sendMessage(message)).thenReturn(message);
        ResponseEntity<List<Message>> actualResponse =messageController.getMessages(conversation);
        assertNotNull(actualResponse);
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testGetUserConvoListTest() {
        ConversationList conversationList = new ConversationList();
        conversationList.setUserId("testId123");
        List<ConversationList> conversationLists = new ArrayList<>();
        conversationLists.add(conversationList);

        when(messageRepository.getMessageWithUsers("sendUser123")).thenReturn(conversationLists);
        ResponseEntity<List<ConversationList>> actualResponse = messageController.getUserConvoList("sendUser123");
        assertNotNull(actualResponse);
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
