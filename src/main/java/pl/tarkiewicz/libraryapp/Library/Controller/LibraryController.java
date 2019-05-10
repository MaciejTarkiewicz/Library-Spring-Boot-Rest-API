package pl.tarkiewicz.libraryapp.Library.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Library.Dto.BookDao;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
import pl.tarkiewicz.libraryapp.Library.Service.LibraryService;
import pl.tarkiewicz.libraryapp.Rate.Dto.RateDto;
import pl.tarkiewicz.libraryapp.Rate.Entity.Rate;
import pl.tarkiewicz.libraryapp.Rate.Service.RateService;
import pl.tarkiewicz.libraryapp.User.Service.UserService;
import pl.tarkiewicz.libraryapp.User.Entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class LibraryController {

    private LibraryService libraryService;
    private UserService userService;
    private RateService rateService;

    @Autowired
    public LibraryController(LibraryService libraryService, UserService userService,RateService rateService ) {
        this.libraryService = libraryService;
        this.userService = userService;
        this.rateService = rateService;
    }

    @PostMapping(value = "/api/library/add")
    public ResponseEntity<String> addBook(@RequestBody BookDao bookDao) {
        try {
            this.libraryService.addBook(bookDao);
            return new ResponseEntity<>("Correct!", HttpStatus.OK);

        } catch (java.time.format.DateTimeParseException e) {
            return new ResponseEntity<>("Bad format production year!", HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/api/library")
    public List<Book> getAllBookByUser(HttpSession session) {
        return this.libraryService.getBooksByUserId((Long) session.getAttribute("User_id"));
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
    public Book getBookById(@RequestParam Long id) {
        return this.libraryService.getBookById(id);
    }


    @PutMapping(value = "/api/library/edit")
    public ResponseEntity<String> editBook(@RequestBody BookDao bookDao,@RequestParam Long id) {
            this.libraryService.editBook(bookDao,id);
        return new ResponseEntity<>("Correct!", HttpStatus.OK);

    }

//    @PutMapping(value = "/api/library/edit/user")
//    public ResponseEntity<String> putBookUser(@RequestBody BookDao bookDao, HttpSession session, @RequestParam Long id) {
//        //LocalDate date = LocalDate.parse(bookDao.getYear());
//        Optional<User> user = this.userService.findById((Long) session.getAttribute("User_id"));
//        //bookDao.setUser(user.get());
//        this.libraryService.editBookByUser(bookDao,user.get());
//        //this.libraryService.save(new Book(id, bookDao.getTitle(), bookDao.getAuthor(), date, bookDao.getType(), user.get()));
//        return new ResponseEntity<>("Correct!", HttpStatus.OK);
//
//    }

    @PutMapping(value = "/api/library/user/{id}")
    public ResponseEntity<String> giveBackBook(@PathVariable Long id) {
        Book book = this.libraryService.getBookById(id);
        this.libraryService.giveBack(book);
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    @PutMapping(value = "/api/library/borrow/{id}")
    public ResponseEntity<String> borrowBook(@PathVariable Long id, HttpSession session) {
        Book book = this.libraryService.getBookById(id);
        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        this.libraryService.borrowBook(book ,user.get());
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }




    @PostMapping(value = "/api/library/rebook/{id}")
    public ResponseEntity<String> rateBook(@RequestBody RateDto rateDto, @PathVariable Long id, HttpSession session) {
        Book book = this.libraryService.getBookById(id);
        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        //double value = Double.parseDouble(rateDto.getRate());
        System.out.println(rateDto.getRate());
//        this.rateService.addRate(new Rate(book,user.get(),rateDto.getRate()));
        this.rateService.addRate(new Rate(book,user.get(),"5"));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

}


