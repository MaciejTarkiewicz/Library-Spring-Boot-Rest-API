package pl.tarkiewicz.libraryapp.Library.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.Library.Entity.Library;
import pl.tarkiewicz.libraryapp.Library.Repo.LibraryRepo;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepo libraryRepo;


    public Library save(Library library){
        return this.libraryRepo.save(library);
    }

    public Iterable<Library> getLibrary(){
        return this.libraryRepo.findAll();
    }

    public void delete (Library library){
        this.libraryRepo.delete(library);
    }

    public void deleteById(Long id){ this.libraryRepo.deleteById(id);}

    public Iterable<Library> getLibraryByUserId(Long id){
        return this.libraryRepo.findLibraryByUserId(id);
    }

    public Library getLibrabyById(Long id){
        return libraryRepo.findById(id).get();
    }


}
