package pl.tarkiewicz.libraryapp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.tarkiewicz.libraryapp.dao.entity.Library;
import pl.tarkiewicz.libraryapp.dao.entity.User;

import java.util.Set;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {


}
