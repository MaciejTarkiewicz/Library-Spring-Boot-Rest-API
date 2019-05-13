package pl.tarkiewicz.libraryapp.Library;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.tarkiewicz.libraryapp.User.Entity.User;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private LocalDate productionYear; //moze data wydania?
    private String type;

    @ManyToOne
    private User user;

    public Book(String title, String author, LocalDate productionYear, String type, User user) {
        this.title = title;
        this.author = author;
        this.productionYear = productionYear;
        this.type = type;
        this.user = user;
    }

    public Book(Long id, String title, String author, LocalDate productionYear, String type, User user) {
        this(title, author, productionYear, type, user);
        this.id = id;
    }

    public Book() {
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

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder productionYear(LocalDate productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Book build() {
            return new Book(id, title, author, productionYear, type, user);
        }

    }

}