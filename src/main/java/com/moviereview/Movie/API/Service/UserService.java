package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailService emailService;

    private void saveUser(User user){
    }
}
