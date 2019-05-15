package pl.tarkiewicz.libraryapp.Library;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.tarkiewicz.libraryapp.Rate.Rate;
import pl.tarkiewicz.libraryapp.User.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
public class Book {

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "book")
    private Set<Rate> rates;

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String author;
    private LocalDate productionYear;
    private String type;


    public Book(String title, String author, LocalDate productionYear, String type, User user) {
        this.title = title;
        this.author = author;
        this.productionYear = productionYear;
        this.type = type;
        this.user = user;
    }

    public Book(Long id , String title, String author, LocalDate productionYear, String type, User user) {
        this(title, author, productionYear, type, user);
        this.id = id;

    }


    public Book(){

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static final class Builder {
        private Long id;
        private String title;
        private String author;
        private LocalDate productionYear;
        private String type;
        private User user;

        public Builder id (Long id){
            this.id = id;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder author(String author){
            this.author = author;
            return this;
        }
        public Builder productionYear(LocalDate productionYear){
            this.productionYear = productionYear;
            return this;
        }

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Book build(){

            Book book = new Book();
            book.id = this.id;
            book.title = this.title;
            book.author = this.author;
            book.productionYear = this.productionYear;
            book.type = this.type;
            book.user = this.user;
            return book;

        }


    }

}