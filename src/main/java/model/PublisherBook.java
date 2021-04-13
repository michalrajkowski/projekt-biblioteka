package main.java.model;

public class PublisherBook {

    public int id;
    public int bookId;
    public int publisherId;
    public String img;
    public Integer pages;
    public Long isbn;

    @Override
    public String toString() {
        return "PublisherBook{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", publisherId=" + publisherId +
                ", img='" + img + '\'' +
                ", pages=" + pages +
                ", isbn=" + isbn +
                '}';
    }
}
