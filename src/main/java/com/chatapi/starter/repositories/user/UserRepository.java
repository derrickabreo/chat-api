package com.chatapi.starter.repositories.user;

import com.chatapi.starter.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User createUser(User users);

    List<User> findAll();

    User findUser(String id);


}
