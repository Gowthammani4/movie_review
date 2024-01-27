package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.UserDetails;
import com.moviereview.Movie.API.model.currentUser;
import com.moviereview.Movie.API.repository.UserRepository;
import com.moviereview.Movie.API.repository.currentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class currentUserService {
    @Autowired
    private currentUserRepository currentUserRepo;
    @Autowired
    private UserRepository userRepo;
    public currentUser addUser(String email){
        UserDetails user=userRepo.findByEmailIgnoreCase(email);
        currentUser newUser= new currentUser(email,user.getUserName());

        List<currentUser> users=currentUserRepo.findAll();
        if(users.isEmpty())
            currentUserRepo.save(newUser);
        else{
            currentUserRepo.deleteAll();
            currentUserRepo.save(newUser);
        }
        return newUser;
    }
    public currentUser getCurrentUser(){
        List<currentUser> user=currentUserRepo.findAll();
        return user.getFirst();
    }
}
