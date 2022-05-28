package com.donovan.controller;

import com.donovan.model.User;
import com.donovan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println("id:" + user.getId());
            System.out.println("name:" + user.getName());
        }

        return "hello";

    }

}
