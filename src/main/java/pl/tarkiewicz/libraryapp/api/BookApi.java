package pl.tarkiewicz.libraryapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.dao.entity.Book;
import pl.tarkiewicz.libraryapp.manager.BookManager;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookApi {


    private BookManager bookManager;

    @Autowired
    public BookApi(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    @GetMapping("/all")
   public Iterable<Book> getAll(){
        return this.bookManager.findAll();
   }

    @GetMapping
    public Optional<Book> getById(@RequestParam Long index){
       return bookManager.findById(index);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
       return bookManager.save(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
        return bookManager.save(book);
    }

    @DeleteMapping
    public void deleteBook(@RequestParam Long index){
        bookManager.delete(index);
    }


}
