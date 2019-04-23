package pl.tarkiewicz.libraryapp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Services.LibraryService;
import pl.tarkiewicz.libraryapp.Services.UserService;
import pl.tarkiewicz.libraryapp.dao.entity.Library;
import pl.tarkiewicz.libraryapp.dao.entity.User;
import pl.tarkiewicz.libraryapp.pojos.Book;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping
public class LibraryController {

    private LibraryService libraryService;
    private UserService userService;

    @Autowired
    public LibraryController(LibraryService libraryService, UserService userService) {
        this.libraryService = libraryService;
        this.userService = userService;
    }


    @PostMapping (value =  "/library/add")
    public ResponseEntity<String> addBook(@RequestBody Book book, HttpSession session) {
        if (!book.checkWebEdit()){
            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        this.libraryService.save(new Library(book.getTitle(), book.getAuthor(),book.getYear(),book.getType(),user.get()));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    @GetMapping (value = "/api/library")
    public Iterable<Library> getAllBook(HttpSession session) {
        System.out.println(session.getAttribute("User_id"));
//        Cookie[] cookies= request.getCookies();
//        for (Cookie cook : cookies){
//            if (cook.getName().equals("User_name")){
//                String name = cook.getValue();
//                System.out.println(name);
//            }
//        }

        return this.libraryService.getLibraryByUserId((Long)session.getAttribute("User_id"));

    }


}
