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
        return allBooks;

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
    public BookDto getBookById(@RequestParam Long id) {
        BookDto book = convertToDto(libraryService.getBookById(id));
        return book;
    }

    @PutMapping(value = "/api/library/edit")
    public ResponseEntity<String> editBook(@RequestBody BookDto bookDto, @RequestParam Long id) {
        bookDto.setId(id);
        Book book = convertToEntity(bookDto);
        libraryService.save(book);
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

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
        book.setLoan(true);
        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        libraryService.borrowBook(book,user.get());
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


