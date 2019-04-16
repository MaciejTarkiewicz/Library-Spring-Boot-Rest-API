package pl.tarkiewicz.libraryapp.pojos;

import org.springframework.beans.factory.annotation.Autowired;
import pl.tarkiewicz.libraryapp.Services.UserService;

public class UserLogin {

    private String passwordpom;
    private UserService userService;

    @Autowired
    public UserLogin(UserService userService) {
        this.userService = userService;
    }

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


    public boolean existUser() throws Exception {
        try{
            passwordpom = this.userService.getUser(this.username).getPassword();
                return passwordpom.equals(password);

        }catch(Exception e){
            throw new Exception ("Nie znaleziono loginu w bazie");
        }
    }


}
