package pl.tarkiewicz.libraryapp.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepo  extends JpaRepository<Book, Long> {

//    @Query("SELECT l FROM Book l WHERE l.user.id = :id")
//    Set<Book> findBookByUserId(@Param("id") Long id);

    List<Book> findBookByUserId(Long id);
}
