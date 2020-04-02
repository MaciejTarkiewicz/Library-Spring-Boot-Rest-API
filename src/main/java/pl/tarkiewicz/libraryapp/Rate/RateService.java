package pl.tarkiewicz.libraryapp.Rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

import java.util.List;

@Service
public class RateService {

    private RateRepo rateRepo;

    @Autowired
    public RateService(RateRepo rateRepo) {
        this.rateRepo = rateRepo;
    }

    public void addRate(Rate rate) throws ValidationException {

        if (Integer.parseInt(rate.getRate()) > 10 || Integer.parseInt(rate.getRate()) < 0) {
            throw new ValidationException("Invalid");
        }
        this.rateRepo.save(rate);

    }

    public Integer getBookRate(Long id) {
        int sum = 0;
        List<Rate> avg = this.rateRepo.findRateByBookId(id);
        for (Rate rate : avg) {
            sum = sum + Integer.parseInt(rate.getRate());
        }
        return (sum / avg.size());
    }

}
