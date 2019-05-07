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
import java.util.List;
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

    @PostMapping(value = "/api/library/add")
    public ResponseEntity<String> addBook(@RequestBody Book book, HttpSession session) {
        System.out.println(book.getAuthor());
        if (!book.checkWebEdit()) {
            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
        }
        try {
            LocalDate date = LocalDate.parse(book.getYear());
            this.libraryService.save(new Library(book.getTitle(), book.getAuthor(), date, book.getType(), null));
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Bad format production year!", HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/api/library")
    public List<Library> getAllBookByUser(HttpSession session) {
        return this.libraryService.getLibraryByUserId((Long) session.getAttribute("User_id"));
    }

    @GetMapping(value = "/api/library/all")
    public List<Library> getAllBook()
    {
        return this.libraryService.getLibrary();
    }

    @GetMapping(value = "/api/logout")
    public void CleanSession(HttpSession session) {
        session.removeAttribute("User_id");
    }

    @DeleteMapping(value = "/api/library/{id}")
    public void DeleteById(@PathVariable Long id) {
        this.libraryService.deleteById(id);

    }

    @GetMapping(value = "/api/library/edit")
    public Library EditById(@RequestParam Long id) {
        return this.libraryService.getLibrabyById(id);
    }

    @PutMapping(value = "/api/library/edit")
    public ResponseEntity<String> PutBook(@RequestBody Book book, @RequestParam Long id) {
        LocalDate date = LocalDate.parse(book.getYear());
        this.libraryService.save(new Library(id, book.getTitle(), book.getAuthor(), date, book.getType(), null));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);

    }

    @PutMapping(value = "/api/library/edit/user")
    public ResponseEntity<String> PutBookUser(@RequestBody Book book, HttpSession session, @RequestParam Long id) {
        LocalDate date = LocalDate.parse(book.getYear());
        Optional<User> user = this.userService.findById((Long) session.getAttribute("User_id"));
        this.libraryService.save(new Library(id, book.getTitle(), book.getAuthor(), date, book.getType(), user.get()));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);

    }

    @PutMapping(value = "/api/library/user/{id}")
    public ResponseEntity<String> GiveBackBook(@PathVariable Long id) {
        Library library = this.libraryService.getLibrabyById(id);
        this.libraryService.save(new Library(id, library.getTitle(), library.getAuthor(), library.getProductionYear(), library.getType(), null));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    @PutMapping(value = "/api/library/borrow/{id}")
    public ResponseEntity<String> BorrowBook(@PathVariable Long id, HttpSession session) {
        Library library = this.libraryService.getLibrabyById(id);
        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        this.libraryService.save(new Library(id, library.getTitle(), library.getAuthor(), library.getProductionYear(), library.getType(), user.get()));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

}


