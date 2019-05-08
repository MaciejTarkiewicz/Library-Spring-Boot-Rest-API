package pl.tarkiewicz.libraryapp.Library.Dao;

public class BookDao {

    private String title;
    private String author;
    private String productionYear;
    private String type;

    public BookDao(String title, String author, String productionYear, String type, Object o) {
        this.title = title.trim();
        this.author = author.trim();
        this.productionYear = productionYear.trim();
        this.type = type.trim();
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

}
