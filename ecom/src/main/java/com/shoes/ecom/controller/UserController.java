package com.shoes.ecom.controller;

import com.shoes.ecom.po.User;
import com.shoes.ecom.repo.UserRepo;
import com.shoes.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping("/create")
    public User create(@RequestBody User userToCreate){
        return userService.createUser(userToCreate);
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody User userToCreate){
        return userService.login(userToCreate);
    }

    @PostMapping("/signup")
    public Boolean signup(@RequestBody User userToCreate){ return userService.signup(userToCreate); }

/*
    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return blogRespository.findOne(blogId);
    }*/

}
