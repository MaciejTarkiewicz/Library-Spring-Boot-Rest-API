package pl.tarkiewicz.libraryapp.User;

import java.util.List;
import java.util.Optional;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserService(UserRepo accountRepo) {
        this.userRepo = accountRepo;
    }

    public User save(User user) {
        this.userRepo.save(user);
        return user;
    }

    public Optional<User> findById(Long id) {
        return this.userRepo.findById(id);
    }

    public List<User> getUsers() {
        return this.userRepo.findAll();
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public boolean checkUser(UserDto u) {
        return getUsers().stream()
                .filter(item -> item.getUsername().equals(u.getUsername()))
                .anyMatch(item -> passwordEncoder().matches(u.getPassword(), item.getPassword()));
    }

    public User findByLogin(String username) throws NotFoundException {
        if (getUsers().stream().anyMatch(item -> item.getUsername().equals(username))) {
            return getUsers().stream()
                    .filter(item -> item.getUsername().equals(username)).findFirst()
                    .orElseThrow(() -> new NotFoundException((String.format("User with following username %s not found", username))));

        } else {
            return null;
        }

    }

}
