package pl.tarkiewicz.libraryapp.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.tarkiewicz.libraryapp.User.Entity.User;
import pl.tarkiewicz.libraryapp.User.UserLogin;
import pl.tarkiewicz.libraryapp.User.UserRegistration;
import pl.tarkiewicz.libraryapp.User.Service.UserService;

import javax.servlet.http.HttpSession;

@RestController
//@RequestMapping
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register")
    public ResponseEntity<String> register (@RequestBody UserRegistration userRegistrtion){
        if (!userRegistrtion.checkWebEdit()){
            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
        }
        if(!userRegistrtion.checkPassword() || !userRegistrtion.checkWebEdit()){
           return new ResponseEntity<>("Password and Confirm Password are not the same!", HttpStatus.BAD_REQUEST);
        }else{
           userService.save(new User(userRegistrtion.getUsername(), userRegistrtion.getPassword(),userRegistrtion.getEmail()));
           return new ResponseEntity<>("Correct!", HttpStatus.OK);
       }
       }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login (@RequestBody UserLogin userlogin, HttpSession session){
       if(this.userService.checkUser(userlogin)){
           session.setAttribute("User_id", userService.findByLogin(userlogin.getUsername()).getId());
           return new ResponseEntity<>("Correct", HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Lipa", HttpStatus.BAD_REQUEST);
       }

    }

    @PostMapping(value = "/")
    public ResponseEntity<String> indexPost(){
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }



}
