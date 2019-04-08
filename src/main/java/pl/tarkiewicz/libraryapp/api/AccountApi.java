package pl.tarkiewicz.libraryapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.dao.entity.Account;
import pl.tarkiewicz.libraryapp.manager.AccountManager;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class AccountApi {

    private AccountManager accountManager;

    @Autowired
    public AccountApi(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @GetMapping
    public Optional<Account> getById(@RequestParam Long index){
        return this.accountManager.findById(index);
    }

    @PostMapping
    public Account addAccount(@RequestBody Account account){
        return accountManager.save(account);
    }
}
