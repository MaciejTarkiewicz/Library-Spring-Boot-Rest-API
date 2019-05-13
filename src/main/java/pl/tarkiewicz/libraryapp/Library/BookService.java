package pl.tarkiewicz.libraryapp.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.User.Entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    void borrowBook(Book book, User user) {
        book.setUser(user);
        bookRepository.save(book);
    }

    void giveBack(Book book) {
        book.setUser(null);
        bookRepository.save(book);
    }


    void save(Book book) {
        bookRepository.save(book);
    }

    public Book addBook(BookDto bookDto) {
        Book book = new Book.Builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .productionYear(bookDto.getProductionYear())
                .type(bookDto.getType())
                .build();
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        this.bookRepository.findAll().iterator().forEachRemaining(list::add);
        return list;

    }

    public void delete(Book book) {
        this.bookRepository.delete(book);
    }

    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    public List<Book> getBooksByUserId(Long id) {
        return bookRepository.findByUserId(id);
    }

    public Book getById(Long id) {
        return bookRepository.getOne(id);
    }

}
