package pl.tarkiewicz.libraryapp.Library.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;

import java.util.Set;

@Repository
public interface LibraryRepo  extends CrudRepository<Book, Long> {

    @Query("SELECT l FROM Book l WHERE l.user.id = :id")
    Set<Book> findBookByUserId(@Param("id") Long id);
}
