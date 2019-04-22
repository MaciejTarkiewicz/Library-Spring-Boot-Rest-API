package pl.tarkiewicz.libraryapp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Services.LibraryService;
import pl.tarkiewicz.libraryapp.dao.entity.Library;
import pl.tarkiewicz.libraryapp.pojos.Book;

@RestController
@RequestMapping
public class LibraryController {

    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @PostMapping (value =  "/library/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        if (!book.checkWebEdit()){
            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
        }
        this.libraryService.save(new Library(book.getTitle(), book.getAuthor(),book.getYear(),book.getType()));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }

    @GetMapping (value = "/api/library")
    public Iterable<Library> getAllBook() {
        return this.libraryService.getLibrary();

    }


}
