package pl.tarkiewicz.libraryapp.Library;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.User.User;

import java.util.List;

@Service
public class LibraryService {

    private LibraryRepo libraryRepo;

    @Autowired
    public LibraryService(LibraryRepo libraryRepo) {
        this.libraryRepo = libraryRepo;

    }

    public void borrowBook(Book book, User user) {
        book.setUser(user);
        book.setLoan(true);
        this.libraryRepo.save(book);
    }

    public void giveBack(Book book) {
        book.setUser(null);
        book.setLoan(false);
        this.libraryRepo.save(book);
    }

    public Book save(Book book) {
        return libraryRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return this.libraryRepo.findAll();

    }

    public void delete(Book book) {
        this.libraryRepo.delete(book);
    }

    public void deleteById(Long id) {
        this.libraryRepo.deleteById(id);
    }

    public List<Book> getBooksByUserId(Long id) {
        return this.libraryRepo.findBookByUserId(id);
    }

    public Book getBookById(Long id) throws NotFoundException {
        return libraryRepo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Book with following id %s not found", id)));
    }

}
