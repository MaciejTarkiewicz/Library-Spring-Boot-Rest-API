package pl.tarkiewicz.libraryapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.dao.entity.Account;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

}
