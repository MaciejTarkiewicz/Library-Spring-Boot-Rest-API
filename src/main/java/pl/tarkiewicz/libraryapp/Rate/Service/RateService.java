package pl.tarkiewicz.libraryapp.Rate.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
import pl.tarkiewicz.libraryapp.Rate.Entity.Rate;
import pl.tarkiewicz.libraryapp.Rate.Repo.RateRepo;
import pl.tarkiewicz.libraryapp.User.Entity.User;

@Service
public class RateService {

    private RateRepo rateRepo;

    @Autowired
    public RateService(RateRepo rateRepo) {
        this.rateRepo = rateRepo;
    }

    public Rate addRate(Rate rate){
        return this.rateRepo.save(rate);

    }

}
