package pl.tarkiewicz.libraryapp.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.User.Dto.UserRegistration;
import pl.tarkiewicz.libraryapp.User.Entity.User;
import pl.tarkiewicz.libraryapp.User.Repo.UserRepo;
import pl.tarkiewicz.libraryapp.User.Dto.UserLogin;

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

    public User RegisterUser(UserRegistration userRegistration) {
        User user = new User.Builder()
                .username(userRegistration.getUsername())
                .password((userRegistration.getPassword()))
                .email(userRegistration.getEmail())
                .build();

        return this.userRepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return this.userRepo.findById(id);
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        this.userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public void deleteUser(User user){
        userRepo.delete(user);
    }

    public boolean checkUser(UserLogin u) {
        return getUsers().stream()
                .filter(item->item.getUsername().equals(u.getUsername()))
                .anyMatch(item ->passwordEncoder.matches(u.getPassword(),item.getPassword()));
    }

    public User findByLogin(String username) {
        if (getUsers().stream().anyMatch(item -> item.getUsername().equals(username))){
            return getUsers().stream().filter(item -> item.getUsername().equals(username)).findFirst().get();

        }else{
            return null;
        }

    }



}
