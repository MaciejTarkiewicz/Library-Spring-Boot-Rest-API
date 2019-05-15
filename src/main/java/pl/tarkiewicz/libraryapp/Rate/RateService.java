package pl.tarkiewicz.libraryapp.Rate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    private RateRepo rateRepo;
    private int sum;

    @Autowired
    public RateService(RateRepo rateRepo) {
        this.rateRepo = rateRepo;
    }

    public Rate addRate(Rate rate){
        return this.rateRepo.save(rate);

    }

    public Integer getBookRate(Long id){
        sum = 0;
        List<Rate> avg = this.rateRepo.findRateByBookId(id);
        for(Rate rate : avg){
            //rate.getRate().valueOf()
            sum = sum + Integer.valueOf(rate.getRate());
        }
        return (sum/avg.size());
    }

}
