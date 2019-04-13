package pl.tarkiewicz.libraryapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.dao.entity.Account;
import pl.tarkiewicz.libraryapp.Services.AccountManager;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AccountApi {

    private AccountManager accountManager;

    @Autowired
    public AccountApi(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @GetMapping(value = "/login")
    public Optional<Account> getById(@RequestParam Long index){
        return this.accountManager.findById(index);
    }


   /* @PostMapping(value = "/login")
    public Account addAccount(@RequestBody Account account){


        return accountManager.save(account);
    }*/


    @GetMapping(value = "/login/{id}")
    public Optional<Account> getByUsername(@PathVariable Long id){
        return accountManager.findById(id);
    }

    @PostMapping
    public Account addAccount(@RequestBody Account account){


        return accountManager.save(account);
    }

}
