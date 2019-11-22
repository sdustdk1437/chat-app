package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserControler {
    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    @ResponseBody
    public String getUsers(
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false,defaultValue = "15") int limit
    ){
        List<User> userList = userService.searchAll();
        JSONArray json = new JSONArray();
        for (User u :userList){
            JSONObject jo = new JSONObject();
            jo.put("id",u.getId());
            jo.put("username",u.getUsername());

            jo.put("experience",u.getExperience());
            jo.put("sex",u.getSex());
            jo.put("score",u.getScore());
            jo.put("city",u.getCity());
            jo.put("sign",u.getSign());
            jo.put("classify",u.getClassify());

            jo.put("wealth",u.getWealth());
            json.add(jo);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",1);
        jsonObject.put("data",json);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();

    }

    @GetMapping(value = "page")
    public String page(Model model){
        model.addAttribute("pageName","index");
        return "index";
    }
}
