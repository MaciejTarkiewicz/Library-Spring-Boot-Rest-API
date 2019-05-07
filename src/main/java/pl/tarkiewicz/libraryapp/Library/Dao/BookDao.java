package pl.tarkiewicz.libraryapp.Library.Dao;

public class BookDao {

    private String title;
    private String author;
    private String year;
    private String type;

    public BookDao(String title, String author, String year, String type, Object o) {
        this.title = title.trim();
        this.author = author.trim();
        this.year = year.trim();
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

    public boolean checkWebEdit(){
        return !getTitle().isEmpty() && !getAuthor().isEmpty() && !getYear().isEmpty() && !getType().isEmpty();
    }
}
