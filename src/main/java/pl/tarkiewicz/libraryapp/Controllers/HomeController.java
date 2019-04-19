
package pl.tarkiewicz.libraryapp.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.tarkiewicz.libraryapp.Services.UserService;
import pl.tarkiewicz.libraryapp.dao.entity.User;
import pl.tarkiewicz.libraryapp.pojos.UserLogin;


@Controller
public class HomeController {

   /* private UserService userService;*/

   /* @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping(value = "/")
    public String indexGet() {
        return "index";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping (value = "/library")
    public String getAllBook(Long id) {
        return "library";
    }

    @GetMapping (value = "/library/add")
    public String getAddBook() {
        return "addbook";
    }

 /*   @PostMapping(value = "login")
    public boolean login (@RequestBody UserLogin userlogin) throws Exception {
        if(this.userService.checkUser(userlogin)){
            return true;
        }else{
            throw new Exception("invalida");
        }

    }
*/


}

