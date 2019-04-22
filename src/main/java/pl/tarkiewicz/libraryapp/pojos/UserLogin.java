package pl.tarkiewicz.libraryapp.pojos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tarkiewicz.libraryapp.Services.UserService;
import pl.tarkiewicz.libraryapp.dao.entity.User;
import java.util.List;
import java.util.Objects;

public class UserLogin {

    private UserService userService;


    private String username;
    private String password;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLogin)) return false;
        UserLogin userLogin = (UserLogin) o;
        return Objects.equals(userService, userLogin.userService) &&
                Objects.equals(getUsername(), userLogin.getUsername()) &&
                Objects.equals(getPassword(), userLogin.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userService, getUsername(), getPassword());
    }
}
