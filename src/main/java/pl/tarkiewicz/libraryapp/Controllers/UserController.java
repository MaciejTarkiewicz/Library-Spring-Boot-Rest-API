package pl.tarkiewicz.libraryapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login (@RequestBody UserLogin userlogin){
       if(this.userService.checkUser(userlogin)){
           return new ResponseEntity<>("Correct!", HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Invalid username or password!", HttpStatus.BAD_REQUEST);
       }

    }

    @PostMapping(value = "/")
    public ResponseEntity<String> indexPost(){
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }





}
