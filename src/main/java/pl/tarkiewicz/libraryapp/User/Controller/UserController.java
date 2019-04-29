package pl.tarkiewicz.libraryapp.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.tarkiewicz.libraryapp.Config.Config;
import pl.tarkiewicz.libraryapp.User.Entity.User;
import pl.tarkiewicz.libraryapp.User.Service.UserService;
import pl.tarkiewicz.libraryapp.User.UserLogin;
import pl.tarkiewicz.libraryapp.User.UserRegistration;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserService userService, Config config) {
        this.userService = userService;
    }


    @PostMapping(value = "/api/register")
    public ResponseEntity<String> register(@RequestBody UserRegistration userRegistrtion) {
        if (!userRegistrtion.checkWebEdit()) {
            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
        }
        else if (!userRegistrtion.checkPassword()) {
            return new ResponseEntity<>("Password and Confirm Password are not the same!", HttpStatus.BAD_REQUEST);
        }
        else if (!userRegistrtion.checkEmail(userRegistrtion.getEmail())) {
            return new ResponseEntity<>("Bad Email format", HttpStatus.CONFLICT);
        }

        else {
            userRegistrtion.setPassword(passwordEncoder.encode(userRegistrtion.getPassword()));
            userService.save(new User(userRegistrtion.getUsername(), userRegistrtion.getPassword(), userRegistrtion.getEmail()));
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userlogin, HttpSession session) {
        if (this.userService.checkUser(userlogin)) {
            session.setAttribute("User_id", userService.findByLogin(userlogin.getUsername()).getId());
            return new ResponseEntity<>("Correct", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Lipa", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/api/library/user")
    public String getUsernameByid (HttpSession session) {
        return this.userService.findById((Long)session.getAttribute("User_id")).get().getLogin();
    }


}
