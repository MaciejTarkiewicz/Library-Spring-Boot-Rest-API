package pl.tarkiewicz.libraryapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.dao.AccountRepo;
import pl.tarkiewicz.libraryapp.dao.entity.Account;

import java.util.Optional;

@Service
public class AccountManager {

    private AccountRepo accountRepo;

    @Autowired
    public AccountManager(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account save(Account account){
        return this.accountRepo.save(account);
    }

    public Optional<Account> findById (Long id){
        return this.accountRepo.findById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        save(new Account(1L,"Ronni","Barca"));
    }

}
