package pl.tarkiewicz.libraryapp.User.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
import pl.tarkiewicz.libraryapp.Rate.Entity.Rate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Set<Book> books;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Set<Rate> rates;

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }



    public static final class Builder {
        private String username;
        private String password;
        private String email;


        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public User build(){

            User user = new User();
            user.username = this.username;
            user.password = this.password;
            user.email = this.email;
            return user;

        }




    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getEmail());
    }
}