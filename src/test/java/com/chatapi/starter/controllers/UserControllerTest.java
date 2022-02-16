package com.chatapi.starter.controllers;

import com.chatapi.starter.models.User;
import com.chatapi.starter.repositories.message.MessageRepository;
import com.chatapi.starter.repositories.user.UserRepository;
import org.bson.types.ObjectId;
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
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(new ObjectId());
        user.setUserName("testName");
        user.setFirstName("First");
        user.setLastname("Last");
        user.setCreatedAt(new Date());

        when(userRepository.createUser(user)).thenReturn(user);
        User actualUSer = userController.createUser(user);
        assertNotNull(actualUSer);

    }

    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setId(new ObjectId());
        user.setUserName("testName");
        user.setFirstName("First");
        user.setLastname("Last");
        user.setCreatedAt(new Date());

        List<User> allUser = new ArrayList<>();
        allUser.add(user);

        when(userRepository.findAll()).thenReturn(allUser);
        ResponseEntity<List<User>> actualResponse  = userController.getUsers();
        assertNotNull(actualResponse);
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setId(new ObjectId());
        user.setUserName("testName");
        user.setFirstName("First");
        user.setLastname("Last");
        user.setCreatedAt(new Date());


        when(userRepository.findUser("test1231")).thenReturn(user);
        ResponseEntity<User> actualResponse  = userController.getUser("test1231");
        assertNotNull(actualResponse);
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
