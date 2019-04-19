package pl.tarkiewicz.libraryapp.pojos;

import java.time.LocalDate;

public class Book {

    //dodać id i pytac o to w controlerze i zrobic findById
    private Long id;
    private String title;
    private String author;
    private String year;
    private String type;

    public Book(){

    }

    public Book(String title, String author, String year, String type) {
        this.title = title.trim();
        this.author = author.trim();
        this.year = year.trim();
        this.type = type.trim();
    }

    public Book(String title, String author, String year, String type, Long id) {
        this.title = title.trim();
        this.author = author.trim();
        this.year = year.trim();
        this.type = type.trim();
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

    public String getYear() {
        return year;
    }

    public void setProductionYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean checkWebEdit(){
        return !getTitle().isEmpty() && !getAuthor().isEmpty() && !getYear().isEmpty() && !getType().isEmpty();
    }
}
