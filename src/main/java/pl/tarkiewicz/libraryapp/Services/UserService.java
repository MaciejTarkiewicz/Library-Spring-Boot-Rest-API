package pl.tarkiewicz.libraryapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.dao.UserRepo;
import pl.tarkiewicz.libraryapp.dao.entity.User;

import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo accountRepo) {
        this.userRepo = accountRepo;
    }

    public User save(User account){
        return this.userRepo.save(account);
    }

    public Optional<User> findById (Long id){
        return this.userRepo.findById(id);
    }

    public User getUser(String username){
        return userRepo.findByUsername(username);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new User("maciek","123","maciek@gmail.pl"));
    }

}
