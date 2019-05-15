package pl.tarkiewicz.libraryapp.Rate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping(value = "/api/library/all/rates/{id}")
    public Integer getAllRate(@PathVariable Long id) {
        return this.rateService.getBookRate(id);



    }
}
