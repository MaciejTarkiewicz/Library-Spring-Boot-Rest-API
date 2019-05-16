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
    private ModelMapper modelMapper;

    @Autowired
    public LibraryController(LibraryService libraryService, UserService userService) {
        this.libraryService = libraryService;
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping(value = "/api/library/add")
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        if (book != null) {
            libraryService.save(book);
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Bad format production year!", HttpStatus.CONFLICT);// Do zmiany
    }


//    @PostMapping(value = "/api/library/add")
//    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
//        try {
//            this.libraryService.addBook(bookDto);
//            return new ResponseEntity<>("Correct!", HttpStatus.OK);
//
//        } catch (java.time.format.DateTimeParseException e) {
//            return new ResponseEntity<>("Bad format production year!", HttpStatus.CONFLICT);
//        }
//    }

    @GetMapping(value = "/api/library")
    public List<BookDto> getAllBookByUser(HttpSession session) {
        List<BookDto> allByUserBooks = new ArrayList<>();
        for (Book book : this.libraryService.getBooksByUserId((Long) session.getAttribute("User_id"))) {
            allByUserBooks.add(convertToDto(book));
        }
        return allByUserBooks;
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
    public BookDto getBookById(@RequestParam Long id) {

        BookDto book = convertToDto(libraryService.getBookById(id));

        return book;
    }



//    @GetMapping(value = "/api/library/edit")
//    public Book getBookById(@RequestParam Long id) {
//        return this.libraryService.getBookById(id);
//    }


//    @PutMapping(value = "/api/library/edit")
//    public ResponseEntity<String> editBook(@RequestBody BookDto bookDto, @RequestParam Long id) {
//            this.libraryService.editBook(bookDto,id);
//        return new ResponseEntity<>("Correct!", HttpStatus.OK);
//
//    }

    @PutMapping(value = "/api/library/edit")
    public ResponseEntity<String> editBook(@RequestBody BookDto bookDto, @RequestParam Long id) {
        bookDto.setId(id);
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
        //bookDto.setId(id);
        //Book book = convertToEntity(bookDto);
        Book book1 = this.libraryService.getBookById(id);
        book1.setLoan(true);

        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        libraryService.borrowBook(book1,user.get() );
//        this.libraryService.borrowBook(book ,user.get());
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }



    private BookDto convertToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return book;
    }



    

}


