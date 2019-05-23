package com.github.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hrms.model.User;
import com.github.hrms.dao.UserRepository;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String email) {
        User n = new User();
        n.setUsername(username);
        n.setEmail(email);
        userRepository.save(n);
        return "saved";
    }

    // TODO: Remove this Mapping.
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
