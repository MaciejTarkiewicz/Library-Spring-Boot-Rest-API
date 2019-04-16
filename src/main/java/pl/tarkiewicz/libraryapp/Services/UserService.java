package pl.tarkiewicz.libraryapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.dao.UserRepo;
import pl.tarkiewicz.libraryapp.dao.entity.User;
import pl.tarkiewicz.libraryapp.pojos.UserLogin;

import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo accountRepo) {
        this.userRepo = accountRepo;
    }

    public User save(User user){
        return this.userRepo.save(user);
    }

    public Optional<User> findById (Long id){
        return this.userRepo.findById(id);
    }

    public Iterable<User> getUser(){
        return userRepo.findAll();
    }

    public boolean existUser(UserLogin u) {
        boolean status = false;
        for (User user:getUser()){
            if (user.getPassword().equals(u.getPassword())){
                status = true;
                break;
            }
        }
        return status;

    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new User("maciek","123","maciek@gmail.pl"));
    }

}
