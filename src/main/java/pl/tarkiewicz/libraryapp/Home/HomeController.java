
package pl.tarkiewicz.libraryapp.Home;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }

    @GetMapping(value = "/register")
    public String getRegister() {
        return "register";
    }
    @GetMapping(value = "/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping (value = "/library")
    public String getAllBook() {
        return "library";
    }

    @GetMapping (value = "/library/add")
    public String getAddBook() {
        return "addbook";
    }

    @GetMapping (value = "/library/edit")
    public String getEditBook() {
        return "editbook";
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> indexPost() {
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }


}

