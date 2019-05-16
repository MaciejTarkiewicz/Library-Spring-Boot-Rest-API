package pl.tarkiewicz.libraryapp.Rate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class RateService {

    private RateRepo rateRepo;
    private int sum;

    @Autowired
    public RateService(RateRepo rateRepo) {
        this.rateRepo = rateRepo;
    }

    public Rate addRate(Rate rate) throws ValidationException {

        if (Integer.valueOf(rate.getRate()) > 10 ||  Integer.valueOf(rate.getRate()) < 0){
            throw new ValidationException("Invalid");
        }
        return this.rateRepo.save(rate);

    }

    public Integer getBookRate(Long id){
        sum = 0;
        List<Rate> avg = this.rateRepo.findRateByBookId(id);
        for(Rate rate : avg){
            sum = sum + Integer.valueOf(rate.getRate());
        }
        return (sum/avg.size());
    }

}
