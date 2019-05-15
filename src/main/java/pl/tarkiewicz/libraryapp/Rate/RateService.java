package pl.tarkiewicz.libraryapp.Rate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public void deleteRateByBookId(Long id){
//        this.rateRepo.deleteByBookId(id);
//
//    }

}
