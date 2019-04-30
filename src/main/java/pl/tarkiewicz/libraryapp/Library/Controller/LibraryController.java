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
import java.time.LocalDate;
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

    @PostMapping (value =  "/api/library/add")
    public ResponseEntity<String> addBook(@RequestBody Book book, HttpSession session) {
        if (!book.checkWebEdit()){
            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
        }
        try {
            LocalDate date = LocalDate.parse(book.getYear());
            Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
            this.libraryService.save(new Library(book.getTitle(), book.getAuthor(),date,book.getType(),user.get()));
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Bad format production year!", HttpStatus.CONFLICT);
        }

    }

    @GetMapping (value = "/api/library")
    public Iterable<Library> getAllBook(HttpSession session) {
        return this.libraryService.getLibraryByUserId((Long)session.getAttribute("User_id"));
    }

    @GetMapping (value = "/api/logout")
    public void CleanSession(HttpSession session) {
        session.removeAttribute("User_id");
    }

    @DeleteMapping (value = "/api/library/{id}")
    public void DeleteById(@PathVariable Long id) {
        this.libraryService.deleteById(id);

    }
    @GetMapping (value = "/api/library/edit")
    public Library EditById(@RequestParam Long id) {
        return this.libraryService.getLibrabyById(id);

    }

    @PutMapping (value = "/api/library/edit")
    public ResponseEntity<String> PutBook(@RequestBody Book book ,HttpSession session, @RequestParam Long id) {
        LocalDate date = LocalDate.parse(book.getYear());
        Optional<User> user = this.userService.findById((Long) session.getAttribute("User_id"));
        this.libraryService.save(new Library(id,book.getTitle(), book.getAuthor(), date, book.getType(), user.get()));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);

    }

}
