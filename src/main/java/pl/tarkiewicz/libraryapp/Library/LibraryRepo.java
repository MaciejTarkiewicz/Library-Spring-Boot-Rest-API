package pl.tarkiewicz.libraryapp.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepo  extends JpaRepository<Book, Long> {

    List<Book> findBookByUserId(Long id);

}
