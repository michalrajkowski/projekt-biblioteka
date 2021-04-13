package main.java.model;

public class Book {

    public int id;
    public String title;
    public String description;
    public String releaseDate;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
