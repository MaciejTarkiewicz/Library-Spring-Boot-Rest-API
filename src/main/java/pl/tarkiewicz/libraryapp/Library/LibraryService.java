package pl.tarkiewicz.libraryapp.Library;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.User.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    private LibraryRepo libraryRepo;

    @Autowired
    public LibraryService(LibraryRepo libraryRepo){
        this.libraryRepo = libraryRepo;

    }
    public Book borrowBook(Book book, User user){
        book.setUser(user);
        book.setLoan(true);
        return this.libraryRepo.save(book);
    }

    public Book giveBack(Book book){
        book.setUser(null);
        book.setLoan(false);
        return this.libraryRepo.save(book);
    }


//    public Book editBook(BookDto bookDto, Long id){
//        Book book = new Book.Builder()
//                .id(id)
//                .title(bookDto.getTitle())
//                .author(bookDto.getAuthor())
////                .productionYear(LocalDate.parse(bookDto.getProductionYear()))
//                .productionYear(bookDto.getProductionYear())
//                .type(bookDto.getType())
//                .build();
//
//        return this.libraryRepo.save(book);
//    }

//    public Book editBookByUser(BookDto bookDto, User user){
//        Book book = new Book.Builder()
//                .title(bookDto.getTitle())
//                .author(bookDto.getAuthor())
//                .productionYear(LocalDate.parse(bookDto.getProductionYear()))
//                .type(bookDto.getType())
//                .user(user)
//                .build();
//        return this.libraryRepo.save(book);
//    }

    void save(Book book) {
        libraryRepo.save(book);
    }

//    public Book addBook(BookDto bookDto){
//        Book book = new Book.Builder()
//                .title(bookDto.getTitle())
//                .author(bookDto.getAuthor())
////                .productionYear(LocalDate.parse(bookDto.getProductionYear()))
//                .productionYear(bookDto.getProductionYear())
//                .type(bookDto.getType())
//                .build();
//        return this.libraryRepo.save(book);
//    }

    public List<Book> getAllBooks(){
        List<Book> list = new ArrayList<>();
        this.libraryRepo.findAll().iterator().forEachRemaining(list::add);
        return list;

    }

    public void delete (Book book){
        this.libraryRepo.delete(book);
    }

    public void deleteById(Long id){ this.libraryRepo.deleteById(id);}

    public List<Book> getBooksByUserId(Long id){
        List<Book> list = new ArrayList<>();
        this.libraryRepo.findBookByUserId(id).iterator().forEachRemaining(list::add);
        return list;
    }

    public Book getBookById(Long id){
        return libraryRepo.findById(id).get();
    }



}
