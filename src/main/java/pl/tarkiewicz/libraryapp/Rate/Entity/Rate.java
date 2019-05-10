package pl.tarkiewicz.libraryapp.Rate.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
import pl.tarkiewicz.libraryapp.User.Entity.User;

import javax.persistence.*;

@Entity
@Table(name="RATES")
public class Rate {

    @ManyToOne
    @JsonIgnoreProperties("Rate")
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("Rate")
    @JoinColumn(name = "id_book")
    private Book book;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Double rate;

    public Rate(Book book,User user, Double rate) {
        this.user = user;
        this.book = book;
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
