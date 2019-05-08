package pl.tarkiewicz.libraryapp.Library.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Library.Dao.BookDao;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
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
    public ResponseEntity<String> addBook(@RequestBody BookDao bookDao) {
//        System.out.println(bookDao.getAuthor());
//        if (!bookDao.checkWebEdit()) {
//            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
//        }
        try {
            //LocalDate date = LocalDate.parse(bookDao.getYear());
            this.libraryService.addBook(bookDao);
            //this.libraryService.save(new Book(bookDao.getTitle(), bookDao.getAuthor(), date, bookDao.getType(), null));
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Bad format production year!", HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/api/library")
    public List<Book> getAllBookByUser(HttpSession session) {
        return this.libraryService.getBookByUserId((Long) session.getAttribute("User_id"));
    }

    @GetMapping(value = "/api/library/all")
    public List<Book> getAllBook()
    {
        return this.libraryService.getAllBooks();
    }

    @GetMapping(value = "/api/logout")
    public void cleanSession(HttpSession session) {
        session.removeAttribute("User_id");
    }

    @DeleteMapping(value = "/api/library/{id}")
    public void deleteById(@PathVariable Long id) {
        this.libraryService.deleteById(id);

    }

    @GetMapping(value = "/api/library/edit")
    public Book editById(@RequestParam Long id) {
        return this.libraryService.getBookById(id);
    }

    @PutMapping(value = "/api/library/edit")
    public ResponseEntity<String> putBook(@RequestBody BookDao bookDao, @RequestParam Long id) {
        LocalDate date = LocalDate.parse(bookDao.getYear());
        this.libraryService.save(new Book(id, bookDao.getTitle(), bookDao.getAuthor(), date, bookDao.getType(), null));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);

    }

    @PutMapping(value = "/api/library/edit/user")
    public ResponseEntity<String> putBookUser(@RequestBody BookDao bookDao, HttpSession session, @RequestParam Long id) {
        //LocalDate date = LocalDate.parse(bookDao.getYear());
        Optional<User> user = this.userService.findById((Long) session.getAttribute("User_id"));
        //bookDao.setUser(user.get());
        this.libraryService.EditBook(bookDao,user.get());
        //this.libraryService.save(new Book(id, bookDao.getTitle(), bookDao.getAuthor(), date, bookDao.getType(), user.get()));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);

    }

    @PutMapping(value = "/api/library/user/{id}")
    public ResponseEntity<String> giveBackBook(@PathVariable Long id) {
        Book book = this.libraryService.getBookById(id);
        this.libraryService.GiveBack(book);
        //this.libraryService.save(new Book(id, library.getTitle(), library.getAuthor(), library.getProductionYear(), library.getType(), null));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    @PutMapping(value = "/api/library/borrow/{id}")
    public ResponseEntity<String> borrowBook(@PathVariable Long id, HttpSession session) {
        Book library = this.libraryService.getBookById(id);
        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        this.libraryService.save(new Book(id, library.getTitle(), library.getAuthor(), library.getProductionYear(), library.getType(), user.get()));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

}


