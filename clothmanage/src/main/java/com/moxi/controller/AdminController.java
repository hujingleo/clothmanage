package com.moxi.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moxi.model.Admin;
import com.moxi.model.User;
import com.moxi.service.UserService;


@RestController
@RequestMapping("/admin")
public class AdminController {
    
	
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public User getUser(){
        User user =null;
        try {
            user = userService.findById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


//  @Autowired
//  private AdminService service;
//    @Autowired
//    private AdminService service;
//
//    @RequestMapping("login")
//    public Admin page1(Admin admin) {
//        return service.findByNameAndPassword(admin);
//    }
    
}