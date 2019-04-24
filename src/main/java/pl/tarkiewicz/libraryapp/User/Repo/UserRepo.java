package pl.tarkiewicz.libraryapp.User.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.User.Repo.Entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

}
