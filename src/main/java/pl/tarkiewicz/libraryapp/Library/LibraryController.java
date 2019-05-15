package pl.tarkiewicz.libraryapp.Library;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Rate.RateDto;
import pl.tarkiewicz.libraryapp.Rate.Rate;
import pl.tarkiewicz.libraryapp.Rate.RateService;
import pl.tarkiewicz.libraryapp.User.User;
import pl.tarkiewicz.libraryapp.User.UserService;

import org.modelmapper.ModelMapper;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class LibraryController {

    private LibraryService libraryService;
    private UserService userService;
    private RateService rateService;
    private ModelMapper modelMapper;

    @Autowired
    public LibraryController(LibraryService libraryService, UserService userService,RateService rateService ) {
        this.libraryService = libraryService;
        this.userService = userService;
        this.rateService = rateService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping(value = "/api/library/add")
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        try {
            this.libraryService.addBook(bookDto);
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
    public List<BookDto> getAllBook()
    {
        List<BookDto> allBooks = new ArrayList<>();
        for (Book book : libraryService.getAllBooks()) {
            allBooks.add(convertToDto(book));
        }
        System.out.println(allBooks.get(0));
        return allBooks;

//        return this.libraryService.getAllBooks();
    }

    @GetMapping(value = "/api/logout")
    public void cleanSession(HttpSession session) {
        session.removeAttribute("User_id");
    }

    @DeleteMapping(value = "/api/library/{id}")
    public void deleteById(@PathVariable Long id) {
        //this.rateService.deleteRateByBookId(id);
        this.libraryService.deleteById(id);
    }

    @GetMapping(value = "/api/library/edit")
    public Book getBookById(@RequestParam Long id) {
        return this.libraryService.getBookById(id);
    }

//
//    @PutMapping(value = "/api/library/edit")
//    public ResponseEntity<String> editBook(@RequestBody BookDto bookDto, @RequestParam Long id) {
//            this.libraryService.editBook(bookDto,id);
//        return new ResponseEntity<>("Correct!", HttpStatus.OK);
//
//    }

    @PutMapping(value = "/api/library/edit")
    public ResponseEntity<String> editBook(@RequestBody BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        libraryService.save(book);
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

//    @PutMapping(value = "/api/library/edit/user")
//    public ResponseEntity<String> putBookUser(@RequestBody BookDto bookDao, HttpSession session, @RequestParam Long id) {
//        //LocalDate date = LocalDate.parse(bookDao.getYear());
//        Optional<User> user = this.userService.findById((Long) session.getAttribute("User_id"));
//        //bookDao.setUser(user.get());
//        this.libraryService.editBookByUser(bookDao,user.get());
//        //this.libraryService.save(new Book(id, bookDao.getTitle(), bookDao.getAuthor(), date, bookDao.getType(), user.get()));
//        return new ResponseEntity<>("Correct!", HttpStatus.OK);
//
//    }

    @PutMapping(value = "/api/library/user/{id}")
    public ResponseEntity<String> giveBackBook(@PathVariable Long id, HttpSession session) {
        session.setAttribute("book_id",id);
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


    @PostMapping(value = "/library/book/rate")
    public ResponseEntity<String> rateBook(@RequestBody RateDto rateDto, HttpSession session) {
        Book book = this.libraryService.getBookById((Long)session.getAttribute("book_id"));
        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        try{
            Integer num = Integer.parseInt(rateDto.getRat());
            this.rateService.addRate(new Rate(book,user.get(),rateDto.getRat()));
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Invalid!", HttpStatus.BAD_GATEWAY);
        }

    }


    private BookDto convertToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return book;
    }
    

}


