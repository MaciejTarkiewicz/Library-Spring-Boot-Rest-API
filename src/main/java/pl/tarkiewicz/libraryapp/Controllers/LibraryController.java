package pl.tarkiewicz.libraryapp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Services.LibraryService;
import pl.tarkiewicz.libraryapp.Services.UserService;
import pl.tarkiewicz.libraryapp.dao.entity.Library;
import pl.tarkiewicz.libraryapp.dao.entity.User;
import pl.tarkiewicz.libraryapp.pojos.Book;

import java.util.Set;

@RestController
@RequestMapping
public class LibraryController {

    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService , UserService userService) {
        this.libraryService = libraryService;
    }
/*
    @PostMapping (value =  "/library/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        if (!book.checkWebEdit()){
            return new ResponseEntity<>("Fill in all fields", HttpStatus.BAD_REQUEST);
        }
        User user = );
        this.libraryService.save(new Library(book.getTitle(), book.getAuthor(),book.getYear(),book.getType(),user));
        return new ResponseEntity<>("Correct!", HttpStatus.OK);
    }*/

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        libraryService.delete(id);
        return "library";
    }

    @GetMapping (value = "/api/library/{id}")
    public Set<Library>  getAllBookUser(@PathVariable("id") Long id) {
        return libraryService.findByUserId(id);
    }


   /* @GetMapping (value = "/api/library")
    public Iterable<Library> getAllBookUser1 (Long id) {
        return this.libraryService.findByUserId(id);

    }*/

}
