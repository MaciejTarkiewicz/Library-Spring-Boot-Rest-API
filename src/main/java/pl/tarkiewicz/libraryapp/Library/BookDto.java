package pl.tarkiewicz.libraryapp.Library;

import pl.tarkiewicz.libraryapp.Rate.Rate;
import pl.tarkiewicz.libraryapp.User.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public class BookDto {


    @NotNull
    private Long id;
    private User user;

    private Set<Rate> rates;

    public String getRates() {
        int sum = 0;
        if (!rates.isEmpty()) {


            for (Rate rate : rates) {
                sum = sum + Integer.valueOf(rate.getRate());
            }

            return String.valueOf(sum /rates.size());
        }else{
            return "Brak oceny";
        }
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String title;
    private String author;
    private LocalDate productionYear;
    private String type;

    public BookDto(String title, String author, LocalDate productionYear, String type) {
        this.title = title.trim();
        this.author = author.trim();
        this.productionYear = productionYear;
        this.type = type.trim();
    }


    public BookDto(){

    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
