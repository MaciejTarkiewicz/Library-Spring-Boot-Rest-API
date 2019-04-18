package pl.tarkiewicz.libraryapp.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Library {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String author;
    private LocalDate productionYear;
    private String type;


    public Library(String title, String author, LocalDate productionYear, String type) {
        this.title = title;
        this.author = author;
        this.productionYear = productionYear;
        this.type = type;
    }

    public Library(){

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


//   private User user;
//
//
//    @ManyToOne
//    @JoinColumn(name = "library_id")
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
