package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.UserDetails;
import com.moviereview.Movie.API.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;

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
        userRepository.save(newData);
        System.out.println(newData);
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(userDetails.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:9090/user/confirm-account?token="+newData.getUserName());
        emailService.sendMail(mailMessage);

        System.out.println("Confirmation Token: " + newData.getUserName());


    }
    public String loginUser(String email,String password){
        UserDetails userDetails=userRepository.findByEmailIgnoreCase(email);
        if(!userDetails.getPassword().equals(password)){
            return null;
        }
        return "success";
    }

    public String confirmEmail(String userName){
        if(userName==null){
            userRepository.deleteByUserName(userName);

        }
        return "Email verified successfully!";
    }
}
