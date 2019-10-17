package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    @ResponseBody
    public List<User> getUsers() {
        List<User> userList = userService.searchAll();

        return userList;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public String getUsersByUserName(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String password
    ) {
        List<User> userList = userService.searchByUserName(userName);
        if (userList.size() > 0) {
            return "登陆成功";
        }
        return "登陆失败";

    }

    @PostMapping(value = "/register")
    @ResponseBody
    public String setUsernameAndPassword(
            @RequestBody(required = false) User user
    ) {
        int result = userService.save(user);
        if (result == 0) {
            return "注册成功";
        }
        return "注册失败";

    }

    @GetMapping(value = "page")
    public String page(Model model) {
        model.addAttribute("pageName", "index");
        return "index";
    }
}
