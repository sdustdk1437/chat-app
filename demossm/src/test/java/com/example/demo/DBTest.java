package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBTest {
    @Autowired
    UserDao userDao;

    @Test
    public void test() {
        User user = userDao.getUserById(1);
        System.out.println(user.getUserName() + user.getPassword());
    }
}
