package com.chatapi.starter.repositories.user;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.chatapi.starter.models.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoUsersRepository implements UserRepository {


    private final MongoClient client;
    private MongoCollection<User> usersCollection;

    public MongoUsersRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        usersCollection = client.getDatabase("chatAPI").getCollection("users", User.class);
    }

    @Override
    public User createUser(User user) {
        user.setId(new ObjectId());
        usersCollection.insertOne(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return usersCollection.find().into(new ArrayList<>());
    }

    @Override
    public User findUser(String id) {
        return usersCollection.find(eq("_id", new ObjectId(id))).first();
    }
}
