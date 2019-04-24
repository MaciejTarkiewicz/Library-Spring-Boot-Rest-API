package pl.tarkiewicz.libraryapp.Library.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Library.Book;
import pl.tarkiewicz.libraryapp.Library.Entity.Library;
import pl.tarkiewicz.libraryapp.Library.Service.LibraryService;
import pl.tarkiewicz.libraryapp.User.Service.UserService;
import pl.tarkiewicz.libraryapp.User.Entity.User;

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
        return this.libraryService.getLibraryByUserId((Long)session.getAttribute("User_id"));

    }


}
