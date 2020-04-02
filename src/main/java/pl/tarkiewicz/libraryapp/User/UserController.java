package pl.tarkiewicz.libraryapp.User;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        if (!userDto.checkWebEdit()) {
            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
        } else if (!userDto.checkPassword()) {
            return new ResponseEntity<>("Password and Confirm Password are not the same!", HttpStatus.BAD_GATEWAY);
        } else if (!userDto.checkEmail(userDto.getEmail())) {
            return new ResponseEntity<>("Bad Email format", HttpStatus.CONFLICT);
        } else {
            userDto.setPassword((passwordEncoder().encode(userDto.getPassword())));
            User user = convertToEntity(userDto);
            userService.save(user);
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto, HttpSession session) throws NotFoundException {
        if (this.userService.checkUser(userDto)) {
            session.setAttribute("User_id", userService.findByLogin(userDto.getUsername()).getId());
            return new ResponseEntity<>("Correct", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/library/user")
    public String getUsernameByid(HttpSession session) throws NotFoundException {
        User user = this.userService.findById((Long) session.getAttribute("User_id")).orElseThrow(() -> new NotFoundException("cannot find the user"));
        return convertToDto(user).getUsername();
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
