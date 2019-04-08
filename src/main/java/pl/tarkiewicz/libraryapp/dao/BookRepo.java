package pl.tarkiewicz.libraryapp.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.dao.entity.Book;

@Repository
public interface BookRepo extends CrudRepository<Book,Long> {

}
