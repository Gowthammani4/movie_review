package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.UserDetails;
import com.moviereview.Movie.API.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public void saveUser(UserDetails userDetails){
        if(userRepository.existsByEmail(userDetails.getEmail())){
            return;}
        UserDetails newData =new UserDetails(userDetails.getUserName(),userDetails.getEmail(),userDetails.getPassword());
        newData.setConfirmationToken(newData.getId());
        userRepository.save(newData);
        userDetails.setConfirmationToken(userDetails.getId());
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(userDetails.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8085/confirm-account?token="+userDetails.getId());
        emailService.sendMail(mailMessage);

        System.out.println("Confirmation Token: " + userDetails.getId());


    }
}
