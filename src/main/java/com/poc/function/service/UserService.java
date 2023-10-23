package com.poc.function.service;

import com.poc.function.dto.GreetingResponse;
import com.poc.function.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    List<User> users = new ArrayList<>();
    @PostConstruct
    public void init() {
        User user = new User();
        user.setId("1");
        user.setName("John");
        user.setEmail("service@gmail.com");
        user.setPhone("1234567890");
        user.setAddress("123 Main St, Anytown USA");
        users.add(user);
    }

    public GreetingResponse getUsers(String name) {
        User user = new User();
        user.setId("1");
        user.setName(name);
        users.add(user);
        return new GreetingResponse(users.toString());
    }

    public GreetingResponse saveUser(User user) {
        users.add(user);
        return new GreetingResponse(users.toString());
    }
}
