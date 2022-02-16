package com.chatapi.starter.controllers;

import com.chatapi.starter.models.Conversation;
import com.chatapi.starter.models.ConversationList;
import com.chatapi.starter.models.Message;
import com.chatapi.starter.repositories.message.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
public class MessageController {

    final private MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /*
     * This API creates a new user
     * @param Users
     */
    @PutMapping("sendMessage")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message messageResponse = messageRepository.sendMessage(message);
        if (messageResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("getMessages")
    public ResponseEntity<List<Message>> getMessages(@RequestBody Conversation message) {
        List<Message> messages =  messageRepository.getAllMessage(message);
        if (messages == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("getUserConvoList/{userId}")
    public ResponseEntity<List<ConversationList>> getUserConvoList(@PathVariable String userId) {
        List<ConversationList> getCovoList = messageRepository.getMessageWithUsers(userId);
        if (getCovoList == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(getCovoList);
    }
}
