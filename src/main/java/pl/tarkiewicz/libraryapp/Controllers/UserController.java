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
       if(!userRegistrtion.getPassword().equals(userRegistrtion.getPasswordConfirmation())){
            return "rózne hasłą";
        }
        userService.save(new User(userRegistrtion.getUsername(), userRegistrtion.getPassword(),userRegistrtion.getEmail()));
        return "welcome";
    }

    @PostMapping(value = "/login")
    public String login (@RequestBody UserLogin userlogin){
        try{
            passwordpom = this.userService.getUser(userlogin.getUsername()).getPassword();
            if(userlogin.getPassword().equals(passwordpom)){
                return "welcome";
            }else{
                return "błędne hasło";
            }
        }catch(Exception e){
            return "nie znaleziono loginu w bazie";
        }

    }


}
