package pl.tarkiewicz.libraryapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.dao.entity.User;
import pl.tarkiewicz.libraryapp.Services.UserService;
import pl.tarkiewicz.libraryapp.pojos.UserLogin;
import pl.tarkiewicz.libraryapp.pojos.UserRegistration;

@RestController
//@RequestMapping
public class UserController {

    private UserService userService;
    private String passwordpom;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register")
    public String register (@RequestBody UserRegistration userRegistrtion){
       if(!userRegistrtion.checkPassword()){
           //return userRegistrtion.getUsername() +" "+ userRegistrtion.getPassword() +" "+ userRegistrtion.getConfirmPassword() +" "+ userRegistrtion.getEmail();
            return "rózne hasłą";
        }else{
           userService.save(new User(userRegistrtion.getUsername(), userRegistrtion.getPassword(),userRegistrtion.getEmail()));
           return "OKEJ!";
       }
       }

    @PostMapping(value = "/login")
    public String login (@RequestBody UserLogin userlogin) throws Exception {
        userlogin.existUser();
        return "OKEJ!";

    }

    @PostMapping(value = "/")
    public String indexPost(){
        return "OKEJ!";
    }



}
