package pl.tarkiewicz.libraryapp.Library.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.Library.Dao.BookDao;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
import pl.tarkiewicz.libraryapp.Library.Repo.LibraryRepo;
import pl.tarkiewicz.libraryapp.User.Entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private LibraryRepo libraryRepo;

    @Autowired
    public LibraryService(LibraryRepo libraryRepo){
        this.libraryRepo = libraryRepo;

    }

    public Book save(Book book){
        return this.libraryRepo.save(book);
    }

    public Book GiveBack(Book book){
        book.setUser(null);
        return this.libraryRepo.save(book);
    }


    public Book EditBook(BookDao bookDao,User user){
        Book book = new Book.Builder()
                .title(bookDao.getTitle())
                .author(bookDao.getAuthor())
                .productionYear(LocalDate.parse(bookDao.getYear()))
                .type(bookDao.getType())
                .user(user)
                .build();
        return this.libraryRepo.save(book);
    }


    public Book addBook(BookDao bookDao){
        Book book = new Book.Builder()
                .title(bookDao.getTitle())
                .author(bookDao.getAuthor())
                .productionYear(LocalDate.parse(bookDao.getYear()))
                .type(bookDao.getType())
                .user(null)
                .build();
        return this.libraryRepo.save(book);
    }

    public List<Book> getAllBooks(){
        List<Book> list = new ArrayList<>();
        this.libraryRepo.findAll().iterator().forEachRemaining(list::add);
        return list;

    }

    public void delete (Book book){
        this.libraryRepo.delete(book);
    }

    public void deleteById(Long id){ this.libraryRepo.deleteById(id);}

    public List<Book> getBookByUserId(Long id){
        List<Book> list = new ArrayList<>();
        this.libraryRepo.findBookByUserId(id).iterator().forEachRemaining(list::add);
        return list;
    }

    public Book getBookById(Long id){
        return libraryRepo.findById(id).get();
    }



}
