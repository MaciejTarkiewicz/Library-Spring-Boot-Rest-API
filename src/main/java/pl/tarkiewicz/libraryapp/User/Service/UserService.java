package pl.tarkiewicz.libraryapp.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.User.Entity.User;
import pl.tarkiewicz.libraryapp.User.Repo.UserRepo;
import pl.tarkiewicz.libraryapp.User.UserLogin;

import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepo accountRepo) {
        this.userRepo = accountRepo;
    }

    public User save(User user) {
        return this.userRepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return this.userRepo.findById(id);
    }

    public Iterable<User> getUser() {
        return userRepo.findAll();
    }

    public boolean checkUser(UserLogin u) {
        boolean status = false;
        for (User user : getUser()) {
            if (passwordEncoder.matches(u.getPassword(),user.getPassword()) & user.getLogin().equals(u.getUsername())) {
                status = true;
                break;
            }
        }
        return status;

    }

    public User findByLogin(String username) {
        for (User u : getUser()) {
            if (u.getLogin().equals(username)) {
                return u;
            }
        }
        return null;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new User("maciek", passwordEncoder.encode("123"), "maciek@gmail.pl"));
    }

}
