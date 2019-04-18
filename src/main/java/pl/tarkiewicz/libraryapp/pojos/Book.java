package pl.tarkiewicz.libraryapp.pojos;

import java.time.LocalDate;

public class Book {

    private String title;
    private String author;
    private LocalDate productionYear;
    private String type;

    public Book(String title, String author, LocalDate productionYear, String type) {
        this.title = title;
        this.author = author;
        this.productionYear = productionYear;
        this.type = type;
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
}
