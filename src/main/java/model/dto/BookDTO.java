package main.java.model.dto;

public class BookDTO {

    public String title;
    public String description;
    public String img;
    public String authorName;
    public String authorLastName;
    public String releaseDate;

    public BookDTO(String title, String description, String img, String authorName, String authorLastName, String releaseDate) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.releaseDate = releaseDate;
    }

    public BookDTO(){}

}
