package com.moviereview.Movie.API.Controller;

import com.moviereview.Movie.API.Service.UserService;
import com.moviereview.Movie.API.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {
@Autowired
    private UserService userService;
@PostMapping("/register")
    private String registerUser(@RequestBody UserDetails user){
    userService.saveUser(user);
    return "registered";
}
}
