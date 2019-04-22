package pl.tarkiewicz.libraryapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.dao.entity.Library;
import pl.tarkiewicz.libraryapp.dao.entity.User;

@Repository
public interface LibraryRepo  extends CrudRepository<Library, Long> {
}
