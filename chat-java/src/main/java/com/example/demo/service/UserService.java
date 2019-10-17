package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserRepository userRepository;

    public List<User> searchAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public List<User> searchByUserName(String userName) {
        List<User> userList = userRepository.findByUsername(userName);
        return userList;
    }

    public int save(User user) {
        userRepository.save(user);
        return 0;
    }
}
