package pl.tarkiewicz.libraryapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.dao.BookRepo;
import pl.tarkiewicz.libraryapp.dao.entity.Book;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookManager {

    private BookRepo bookRepo;


    @Autowired
    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Optional<Book> findById (Long id){
        return this.bookRepo.findById(id);
    }

    public Iterable<Book> findAll(){
        return this.bookRepo.findAll();
    }

    public Book save(Book book){
       return this.bookRepo.save(book);
    }

    public void delete (Long Id){
        this.bookRepo.deleteById(Id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Book(1L,"Harry Potter", LocalDate.of(1997, 1, 1),"J.K. Rowling"));
        save(new Book(2L,"Mały Ksiąze", LocalDate.of(1947, 2, 2),"Antoine de Saint-Exupéry"));
    }




}
