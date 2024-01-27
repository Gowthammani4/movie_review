package com.moviereview.Movie.API.Controller;

import com.moviereview.Movie.API.Service.currentUserService;
import com.moviereview.Movie.API.model.currentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currentUser")
public class currentUserController {
    @Autowired
    private currentUserService curruserService;

    @GetMapping
    private currentUser getCurrentUser(){
        return curruserService.getCurrentUser();
    }
}
