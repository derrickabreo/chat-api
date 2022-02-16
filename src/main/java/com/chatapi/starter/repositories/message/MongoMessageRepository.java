package com.chatapi.starter.repositories.message;

import com.chatapi.starter.models.Conversation;
import com.chatapi.starter.models.ConversationList;
import com.chatapi.starter.models.Message;
import com.chatapi.starter.models.User;
import com.mongodb.client.*;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoMessageRepository implements MessageRepository{

    private final MongoClient client;
    private final String DB_NAME = "chatAPI";
    private MongoCollection<User> usersCollection;
    private MongoCollection<Message> messageCollection;
    private MongoCollection<ConversationList> convoListCollection;
    private MongoCollection<Collection> conversationCollection;
    private MongoDatabase database;

    public MongoMessageRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        database = client.getDatabase(DB_NAME);
    }

    @Override
    public Message sendMessage(Message message) {

        String fromId = message.getFromId();
        String toId = message.getToId();

        if(toId.equalsIgnoreCase(fromId))
            return null;

        if (collectionExists(DB_NAME,fromId+toId)) {
            sendNewMessage(fromId,toId,message);
        } else if(collectionExists(DB_NAME,toId+fromId)) {
            sendNewMessage(toId,fromId,message);
        } else {
            if (checkUserExists(fromId) && checkUserExists(toId)) {
                sendNewMessage(fromId,toId,message);
                addUserToConversationList(fromId, toId);
            }
        }
        return message;
    }

    @Override
    public List<Message> getAllMessage(Conversation message) {
        if (collectionExists(DB_NAME,message.getFromId()+message.getToId())) {
            messageCollection = database.getCollection(message.getFromId()+message.getToId(),
                    Message.class);
            return messageCollection.find().into(new ArrayList<>());
        } else if (collectionExists(DB_NAME,message.getToId()+message.getFromId())) {
            messageCollection = database.getCollection(message.getToId()+message.getFromId(),
                    Message.class);
            return messageCollection.find().into(new ArrayList<>());
        }
        return null;
    }

    @Override
    public List<ConversationList> getMessageWithUsers(String userId) {
        convoListCollection = database.getCollection(userId, ConversationList.class);
        return convoListCollection.find().into(new ArrayList<>());
    }

    private boolean collectionExists(final String db, final String collectionName) {

         MongoIterable<String> collection = database.listCollectionNames();

        for(String s : collection) {
            if(s.equals(collectionName)) {
                return true;
            }
        }
        return false;

    }

    private boolean checkUserExists(String userId) {
        usersCollection = client.getDatabase(DB_NAME).getCollection("users", User.class);
        User user = usersCollection.find(eq("_id", new ObjectId(userId))).first();
        return user != null;
    }

    private void sendNewMessage(String firstId, String secondId, Message message) {
        messageCollection = database.getCollection(firstId+secondId,
                Message.class);
        messageCollection.insertOne(message);
    }

    private void addUserToConversationList(String from, String to) {
        ConversationList conversationList = new ConversationList(to);
        convoListCollection = database.getCollection(from, ConversationList.class);
        convoListCollection.insertOne(conversationList);
        ConversationList toConvo = new ConversationList(from);
        convoListCollection = database.getCollection(to, ConversationList.class);
        convoListCollection.insertOne(toConvo);

    }

}
