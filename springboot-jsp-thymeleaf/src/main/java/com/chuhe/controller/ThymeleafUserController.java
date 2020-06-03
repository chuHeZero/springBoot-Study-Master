package com.chuhe.controller;

import java.util.List;

import com.chuhe.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chuhe.service.UserService;


@Controller
public class ThymeleafUserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }


    @RequestMapping("/list")
    public String list(Model model) {
        System.out.println("Thymeleaf查询所有");
        List<UserEntity> userEntities = userService.findAll();
        model.addAttribute("users", userEntities);
        return "templates/user/list";
    }


    @RequestMapping("/toAdd")
    public String toAdd() {
        return "templates/user/userAdd";
    }


    @RequestMapping("/add")
    public String add(UserEntity userEntity) {
        userService.addUser(userEntity);
        return "redirect:/list";
    }


    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        UserEntity userEntity = userService.findUserById(id);
        model.addAttribute("user", userEntity);
        return "templates/user/userEdit";
    }


    @RequestMapping("/edit")
    public String edit(UserEntity userEntity) {
        userService.updateUser(userEntity);
        return "redirect:/list";
    }


    @RequestMapping("/toDelete")
    public String delete(Long id) {
        userService.deleteUser(id);
        return "redirect:/list";
    }
}
