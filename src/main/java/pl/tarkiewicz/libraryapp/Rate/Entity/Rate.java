package pl.tarkiewicz.libraryapp.Rate.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
import pl.tarkiewicz.libraryapp.User.Entity.User;

import javax.persistence.*;

@Entity
@Table(name = "RATES")
public class Rate {

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private Book book;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String rate;


    public Rate() {

    }

    public Rate(Book book, User user, String rate) {
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }


}
