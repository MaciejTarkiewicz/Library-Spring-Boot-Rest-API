package pl.tarkiewicz.libraryapp.User.Dao;

import java.util.regex.Pattern;

public class UserRegistration {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;


    public UserRegistration() {
    }

    public UserRegistration(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password.trim();
        this.confirmPassword = confirmPassword.trim();
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkPassword() {
        return getPassword().equals(getConfirmPassword());
    }

    public boolean checkWebEdit(){
        return !getUsername().isEmpty() && !getPassword().isEmpty() && !getConfirmPassword().isEmpty() && !getEmail().isEmpty();
    }

    public boolean checkEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }



}


