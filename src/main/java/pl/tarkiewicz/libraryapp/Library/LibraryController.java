package pl.tarkiewicz.libraryapp.Library;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tarkiewicz.libraryapp.User.Service.UserService;

@RestController
public class LibraryController {

    private BookService bookService;
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public LibraryController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping(value = "/api/library/add")
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        if (book != null) {
            bookService.save(book);
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Correct!", HttpStatus.CONFLICT);// Do zmiany
    }

    @GetMapping(value = "/api/library/all")
    public List<BookDto> getAllBook() {
        List<BookDto> allBooks = new ArrayList<>();
        for (Book book : bookService.getAllBooks()) {
            allBooks.add(convertToDto(book));
        }
        return  allBooks;
    }

    @DeleteMapping(value = "/api/library/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping(value = "/api/library/edit")
    public BookDto getBookById(@RequestParam Long id) {
        Book book = bookService.getById(id);
        return convertToDto(book);
    }

    @PutMapping(value = "/api/library/edit")
    public ResponseEntity<String> editBook(@RequestBody BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        bookService.save(book);
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    private BookDto convertToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return book;
    }
/*


    @PutMapping(value = "/api/library/user/{id}")
    public ResponseEntity<String> giveBackBook(@PathVariable Long id) {
        Book book = this.bookService.getBookById(id);
        this.bookService.giveBack(book);
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    @PutMapping(value = "/api/library/borrow/{id}")
    public ResponseEntity<String> borrowBook(@PathVariable Long id, HttpSession session) {
        Book book = this.bookService.getBookById(id);
        Optional<User> user = this.userService.findById((Long) session.getAttribute("User_id"));
        this.bookService.borrowBook(book, user.get());
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }
*/

}


