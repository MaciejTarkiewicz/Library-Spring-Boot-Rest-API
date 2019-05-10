package pl.tarkiewicz.libraryapp.Rate.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.Rate.Entity.Rate;

@Repository
public interface RateRepo extends CrudRepository<Rate,Long> {

}
