package pl.tarkiewicz.libraryapp.Library;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class BookDto {


    @NotNull
    private Long id;
    private String title;
    private String author;
    private String productionYear;
    private String type;

    public BookDto(String title, String author, String productionYear, String type) {
        this.title = title.trim();
        this.author = author.trim();
        this.productionYear = productionYear.trim();
        this.type = type.trim();
    }

    public BookDto() {
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
        return LocalDate.parse(this.productionYear);
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

}
