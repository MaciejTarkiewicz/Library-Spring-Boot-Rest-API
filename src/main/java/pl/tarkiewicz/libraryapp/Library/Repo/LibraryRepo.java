package pl.tarkiewicz.libraryapp.Library.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.Library.Entity.Library;

import java.util.Set;

@Repository
public interface LibraryRepo  extends CrudRepository<Library, Long> {

    @Query("SELECT l FROM Library l WHERE l.user.id = :id")
    Set<Library> findLibraryByUserId(@Param("id") Long id);
}
