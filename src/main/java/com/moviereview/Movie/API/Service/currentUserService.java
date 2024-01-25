package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.currentUser;
import com.moviereview.Movie.API.repository.currentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class currentUserService {
    @Autowired
    private currentUserRepository currentUserRepo;
    public String addUser(String email){
        List<currentUser> users=currentUserRepo.findAll();
        if(users.isEmpty())
            currentUserRepo.save(new currentUser(email));
        else{
            currentUserRepo.deleteAll();
            currentUserRepo.save(new currentUser(email));}
        return email;
    }
    public String getCurrentUser(){
        List<currentUser> user=currentUserRepo.findAll();
        return user.getFirst().getEmail();
    }
}
