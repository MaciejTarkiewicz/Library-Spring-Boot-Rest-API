package pl.tarkiewicz.libraryapp.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.dao.LibraryRepo;
import pl.tarkiewicz.libraryapp.dao.entity.Library;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class LibraryService {

    private LibraryRepo libraryRepo;

    @Autowired
    public LibraryService(LibraryRepo libraryRepo) {
        this.libraryRepo = libraryRepo;
    }

    public Library save(Library library){
        return this.libraryRepo.save(library);
    }

    public Iterable<Library> getLibrary(){
        return this.libraryRepo.findAll();
    }


    public void delete (Long id)
    {
        if (libraryRepo.existsById(id)){
            this.libraryRepo.deleteById(id);
        }
    }

    public Set<Library> findByUserId(Long id){
        return this.libraryRepo.findLibraryByUserId(id);
    }

    public Iterable<Library> getAllById(Long id){
        
        return this.libraryRepo.findAllById(id);
    }

    /*@EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Library("Harry Potter","J.K. Rowling", "1998-05-05","Fantasy" ));
    }*/

}
