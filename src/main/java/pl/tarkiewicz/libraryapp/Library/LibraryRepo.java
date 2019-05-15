package pl.tarkiewicz.libraryapp.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface LibraryRepo  extends JpaRepository<Book, Long> {

//    @Query("SELECT l FROM Book l WHERE l.user.id = :id")
//    Set<Book> findBookByUserId(@Param("id") Long id);

    List<Book> findBookByUserId(Long id);
}
