package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.UserDetails;
import com.moviereview.Movie.API.repository.UserRepository;
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
        userRepository.save(new UserDetails(userDetails.getUserName(),userDetails.getEmail(),userDetails.getPassword()));;
        userDetails.setConfirmationToken(userDetails.getId());
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(userDetails.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8085/confirm-account?token="+userDetails.getConfirmationToken());
        emailService.sendMail(mailMessage);

        System.out.println("Confirmation Token: " + userDetails.getConfirmationToken());


    }
}
