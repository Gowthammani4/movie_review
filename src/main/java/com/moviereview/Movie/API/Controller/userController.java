package com.moviereview.Movie.API.Controller;

import com.moviereview.Movie.API.Service.UserService;
import com.moviereview.Movie.API.Service.currentUserService;
import com.moviereview.Movie.API.model.Movie;
import com.moviereview.Movie.API.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController {
@Autowired
    private UserService userService;
@Autowired
private currentUserService currUserService;
@PostMapping("/register")
    private String registerUser(@RequestBody UserDetails user){
    userService.saveUser(user);
    return "registered";
}
@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
public String confirmUserAccount(@RequestParam("token")String confirmationToken) {
    return userService.confirmEmail(Long.parseLong(confirmationToken));
}
@PostMapping("/forgotPassword/{email}")
public String forgetPassword(@PathVariable String email){
    return userService.forgotPassword(email);
}
@RequestMapping(value="/show-Password", method= {RequestMethod.GET, RequestMethod.POST})
public String showPassword(@RequestParam("yourPassword")String password) {
    return userService.PasswordShow(password);
}
@PostMapping("/login")
public String loginUser(@RequestBody Map<String,String> user){
    String state=userService.loginUser(user.get("email"),user.get("password"));
    currUserService.addUser(user.get("email"));
    return "Success";
}
@PostMapping("/newPassword")
    public String newPassword(@RequestBody Map<String,String> userCredentials){
    return userService.passwordChange(userCredentials.get("email"),userCredentials.get("newPassword"));
}

}
