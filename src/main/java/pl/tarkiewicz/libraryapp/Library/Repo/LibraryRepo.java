package pl.tarkiewicz.libraryapp.Library.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.Library.Repo.Entity.Library;

@Repository
public interface LibraryRepo  extends CrudRepository<Library, Long> {
}
