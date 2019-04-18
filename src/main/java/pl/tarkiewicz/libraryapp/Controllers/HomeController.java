
package pl.tarkiewicz.libraryapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.tarkiewicz.libraryapp.Services.LibraryService;
import pl.tarkiewicz.libraryapp.dao.entity.Library;

@Controller
public class HomeController {

    private LibraryService libraryService;

    @Autowired
    public HomeController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

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
    public String getAllBook() {
        return "library";

    }


}

