package pl.tarkiewicz.libraryapp.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tarkiewicz.libraryapp.Services.LibraryService;
import pl.tarkiewicz.libraryapp.dao.entity.Library;

@RestController
@RequestMapping(value = "/library")
public class LibraryController {

    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public Iterable<Library> getAllBook() {
        return this.libraryService.getLibrary();

    }


}
