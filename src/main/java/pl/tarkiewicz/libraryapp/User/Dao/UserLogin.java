package pl.tarkiewicz.libraryapp.User.Dao;

import java.util.Objects;

public class UserLogin {

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
        return Objects.equals(getUsername(), userLogin.getUsername()) &&
                Objects.equals(getPassword(), userLogin.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
