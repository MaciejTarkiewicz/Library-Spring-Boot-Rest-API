package pl.tarkiewicz.libraryapp.Rate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepo extends JpaRepository<Rate,Long> {
    List<Rate> findRateByBookId(Long id);

}
