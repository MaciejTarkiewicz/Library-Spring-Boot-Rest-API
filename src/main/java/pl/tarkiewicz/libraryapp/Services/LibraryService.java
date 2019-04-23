package pl.tarkiewicz.libraryapp.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.dao.LibraryRepo;
import pl.tarkiewicz.libraryapp.dao.entity.Library;

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

    public void delete (Library library){
        this.libraryRepo.delete(library);
    }

    public Iterable<Library> getLibraryByUserId(Long id){
        return this.libraryRepo.findLibraryByUserId(id);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB(){
//        save(new Library("Harry Potter","J.K. Rowling", "1998-05-05","Fantasy"));
//    }

}
