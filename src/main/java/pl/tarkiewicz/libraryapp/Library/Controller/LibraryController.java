package pl.tarkiewicz.libraryapp.Library.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Library.Book;
import pl.tarkiewicz.libraryapp.Library.Service.LibraryService;
import pl.tarkiewicz.libraryapp.Library.Repo.Entity.Library;

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
