package pl.tarkiewicz.libraryapp.Library.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
import pl.tarkiewicz.libraryapp.Library.Repo.LibraryRepo;

import java.util.ArrayList;
import java.util.List;

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
