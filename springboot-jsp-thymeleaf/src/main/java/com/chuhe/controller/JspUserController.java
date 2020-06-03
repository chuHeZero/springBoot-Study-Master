package com.chuhe.controller;

import java.util.List;

import com.chuhe.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chuhe.service.UserService;


@Controller
public class JspUserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    public String index() {
        return "redirect:/list";
    }


    @RequestMapping("/list2")
    public String list(Model model) {
        System.out.println("JSP查询所有");
        List<UserEntity> userEntities = userService.findAll();
        model.addAttribute("users", userEntities);
        return "jsp/user/list2";
    }


    @RequestMapping("/toAdd2")
    public String toAdd() {
        return "jsp/user/userAdd2";
    }


    @RequestMapping("/add2")
    public String add(UserEntity userEntity) {
        userService.addUser(userEntity);
        return "redirect:/list2";
    }


    @RequestMapping("/toEdit2")
    public String toEdit(Model model, Long id) {
        UserEntity userEntity = userService.findUserById(id);
        model.addAttribute("user", userEntity);
        return "jsp/user/userEdit2";
    }


    @RequestMapping("/edit2")
    public String edit(UserEntity userEntity) {
        userService.updateUser(userEntity);
        return "redirect:/list2";
    }


    @RequestMapping("/toDelete2")
    public String delete(Long id) {
        userService.deleteUser(id);
        return "redirect:/list2";
    }
}
