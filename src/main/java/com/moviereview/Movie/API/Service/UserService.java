package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.UserDetails;
import com.moviereview.Movie.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailService emailService;

    public void saveUser(UserDetails userDetails){
        if(userRepository.existsByEmail(userDetails.getEmail())){
            return;}
        if(userRepository.existsByUserName(userDetails.getUserName()))
            return;
        Random random=new Random();
        long token=random.nextLong();
        UserDetails newData =new UserDetails(userDetails.getUserName(),userDetails.getEmail(),userDetails.getPassword(),false,token);
        userRepository.save(newData);
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(userDetails.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"https://movie-review-3gg6.onrender.com/user/confirm-account?token="+newData.getConfirmationToken());
        emailService.sendMail(mailMessage);
    }
    public String loginUser(String email,String password){
        UserDetails userDetails=userRepository.findByEmailIgnoreCase(email);
        if (userDetails==null)
            return null;
        if(!userDetails.getPassword().equals(password) && !userDetails.getVerified()){
            return null;
        }

        return "success";
    }

    public String confirmEmail(long token){
        UserDetails user=userRepository.findByConfirmationToken(token);
        userRepository.delete(user);
        user.setVerified(true);
        userRepository.save(user);
        return "Email verified successfully!";
    }
    public String forgotPassword(String email){
        UserDetails user=userRepository.findByEmailIgnoreCase(email);
        if(user==null)
            return null;

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Forgot Password!");
        mailMessage.setText("To show your account's password, please click here : "
                +"https://movie-review-3gg6.onrender.com/user/show-Password?yourPassword="+user.getPassword());
        emailService.sendMail(mailMessage);
        return "sent to email!";

    }

    public String PasswordShow(String password){
        return "Your password is : "+password;
    }
}
