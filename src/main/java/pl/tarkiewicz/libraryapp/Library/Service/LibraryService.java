package pl.tarkiewicz.libraryapp.Library.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.Library.Entity.Library;
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


    public Library save(Library library){
        return this.libraryRepo.save(library);
    }

    public List<Library> getLibrary(){
        List<Library> list = new ArrayList<>();
        this.libraryRepo.findAll().iterator().forEachRemaining(list::add);
        return list;

    }

    public void delete (Library library){
        this.libraryRepo.delete(library);
    }

    public void deleteById(Long id){ this.libraryRepo.deleteById(id);}

    public List<Library> getLibraryByUserId(Long id){
        List<Library> list = new ArrayList<>();
        this.libraryRepo.findLibraryByUserId(id).iterator().forEachRemaining(list::add);
        return list;
    }

    public Library getLibrabyById(Long id){
        return libraryRepo.findById(id).get();
    }



}
