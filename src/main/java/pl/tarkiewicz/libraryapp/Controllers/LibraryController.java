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

//    //@GetMapping(value = "/{username}")
//    @GetMapping
//    public Iterable<Library> getAllBook() {
//        return this.libraryService.getLibrary();
//
//    }

    @PostMapping (value =  "/library")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        this.libraryService.save(new Library(book.getTitle(), book.getAuthor(),book.getProductionYear(),book.getType()));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }


    @GetMapping (value = "/api/library")
    public Iterable<Library> getAllBook() {
        return this.libraryService.getLibrary();

    }


}
