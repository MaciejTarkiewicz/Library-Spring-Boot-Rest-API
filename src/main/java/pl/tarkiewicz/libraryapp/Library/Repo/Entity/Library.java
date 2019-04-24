package pl.tarkiewicz.libraryapp.Library.Repo.Entity;

import pl.tarkiewicz.libraryapp.User.Repo.Entity.User;

import javax.persistence.*;

@Entity
public class Library {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String author;
    private String productionYear;
    private String type;


    public Library(String title, String author, String productionYear, String type) {
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

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
