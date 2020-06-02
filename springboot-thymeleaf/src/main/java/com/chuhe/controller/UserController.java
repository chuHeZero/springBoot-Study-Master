package com.chuhe.controller;

import com.chuhe.entity.UserEntity;
import com.chuhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        System.out.println("查询所有");
        List<UserEntity> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(UserEntity user) {
        userService.addUser(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, int id) {
        UserEntity user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(UserEntity user) {
        userService.updateUser(user);
        return "redirect:/list";
    }


    @RequestMapping("/toDelete")
    public String delete(int id) {
        userService.deleteUser(id);
        return "redirect:/list";
    }
}
