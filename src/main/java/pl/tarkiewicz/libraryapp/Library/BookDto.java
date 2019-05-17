package pl.tarkiewicz.libraryapp.Library;

import pl.tarkiewicz.libraryapp.Rate.Rate;
import pl.tarkiewicz.libraryapp.Rate.RateDto;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public class BookDto {

    @NotNull
    private Long id;

    private String title;
    private String author;
    private LocalDate productionYear;
    private String type;
    private Set<RateDto> rates;
    private boolean loan;

    public BookDto(String title, String author, LocalDate productionYear, String type) {
        this.title = title.trim();
        this.author = author.trim();
        this.productionYear = productionYear;
        this.type = type.trim();
    }

    public BookDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(LocalDate productionYear) {
        this.productionYear = productionYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLoan() {
        return loan;
    }

    public void setLoan(boolean loan) {
        this.loan = loan;
    }

    public void setRates(Set<RateDto> rates) {
        this.rates = rates;
    }

    public String getRates() {
        int sum = 0;
        if (!rates.isEmpty()) {

            for (RateDto rateDto : rates) {
                sum = sum + Integer.valueOf(rateDto.getRate());
            }

            return String.valueOf(sum / rates.size());
        } else {
            return "No Rating";
        }
    }

}
