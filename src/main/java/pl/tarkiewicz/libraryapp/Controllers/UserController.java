package pl.tarkiewicz.libraryapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.dao.entity.User;
import pl.tarkiewicz.libraryapp.Services.UserService;
import pl.tarkiewicz.libraryapp.pojos.UserRegistrtion;

import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    private UserService accountManager;

    @Autowired
    public UserController(UserService accountManager) {
        this.accountManager = accountManager;
    }



/*    @PostMapping(value = "/register")
    public User addAccount(@RequestBody User account){
        return accountManager.save(account);
    }*/

    @PostMapping(value = "/")
    public String Register (@RequestBody UserRegistrtion accountRegistration){
        if(!accountRegistration.getPassword().equals(accountRegistration.getPasswordConfirmation())){
            return "Error the two passwords do not match";
        }
        accountManager.save(new User(accountRegistration.getUsername(), accountRegistration.getPassword(),accountRegistration.getEmail()));
        return "User created";
    }

    @GetMapping(value = "/register")
    public Optional<User> getById(@RequestParam Long index){
        return this.accountManager.findById(index);
    }

    @GetMapping(value = "/register/{id}")
    public Optional<User> getByUsername(@PathVariable Long id){
        return accountManager.findById(id);
    }



}
