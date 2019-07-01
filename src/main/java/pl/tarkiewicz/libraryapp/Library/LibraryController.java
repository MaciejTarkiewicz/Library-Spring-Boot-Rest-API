package pl.tarkiewicz.libraryapp.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.User.User;
import pl.tarkiewicz.libraryapp.User.UserService;

import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
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

    @PostMapping(value = "/library/add")
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        if (book != null) {
            libraryService.save(book);
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Bad format production year!", HttpStatus.CONFLICT);// Do zmiany
    }

    @GetMapping(value = "/library")
    public List<BookDto> getAllBookByUser(HttpSession session) {
        return  this.libraryService.getBooksByUserId((Long) session.getAttribute("User_id"))
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/library/all")
    public List<BookDto> getAllBook() {
        return libraryService.getAllBooks()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/logout")
    public void cleanSession(HttpSession session) {
        session.removeAttribute("User_id");
    }

    @DeleteMapping(value = "/library/{id}")
    public void deleteById(@PathVariable Long id) {
        this.libraryService.deleteById(id);
    }

    @GetMapping(value = "/library/edit")
    public BookDto getBookById(@RequestParam Long id) {
        BookDto book = convertToDto(libraryService.getBookById(id));
        return book;
    }

    @PutMapping(value = "/library/edit")
    public ResponseEntity<String> editBook(@RequestBody BookDto bookDto, @RequestParam Long id) {
        bookDto.setId(id);
        Book book = convertToEntity(bookDto);
        libraryService.save(book);
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    @PutMapping(value = "/library/user/{id}")
    public ResponseEntity<String> giveBackBook(@PathVariable Long id, HttpSession session) {
        session.setAttribute("book_id", id);
        Book book = this.libraryService.getBookById(id);
        this.libraryService.giveBack(book);
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    @PutMapping(value = "/library/borrow/{id}")
    public ResponseEntity<String> borrowBook(@PathVariable Long id, HttpSession session) {
        Book book = this.libraryService.getBookById(id);
        book.setLoan(true);
        Optional<User> user = this.userService.findById((Long) session.getAttribute("User_id"));
        libraryService.borrowBook(book, user.get());
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


