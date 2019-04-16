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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register")
    public String register (@RequestBody UserRegistration userRegistrtion){
       if(!userRegistrtion.checkPassword()){
            return "Podałeś różne hasłą";
        }else{
           userService.save(new User(userRegistrtion.getUsername(), userRegistrtion.getPassword(),userRegistrtion.getEmail()));
           return "Uzytkownik został zapisany w bazie!";
       }
       }

    @PostMapping(value = "/login")
    public String login (@RequestBody UserLogin userlogin){
       if(this.userService.existUser(userlogin)){
            return "Uzytkownik istenieje, zalogowałeś się!";
        }else{
            return "błędne hasło";
        }


    }

    @PostMapping(value = "/")
    public String indexPost(){
        return "OKEJ!";
    }



}
