package pl.tarkiewicz.libraryapp.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.Library.Entity.Library;
import pl.tarkiewicz.libraryapp.User.Entity.User;
import pl.tarkiewicz.libraryapp.User.Repo.UserRepo;
import pl.tarkiewicz.libraryapp.User.UserLogin;

import java.util.ArrayList;
import java.util.List;
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

    public List<User> getUser() {
        List<User> list = new ArrayList<>();
        this.userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public void deleteUser(User user){
        userRepo.delete(user);
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



}
