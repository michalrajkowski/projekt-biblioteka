package main.java.model;

public class BookCopy {

    public int id;
    public int publisherBookId;
    public int stateId;
    public String description;
    public Long signature;

    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + id +
                ", publisherBookId=" + publisherBookId +
                ", stateId=" + stateId +
                ", description='" + description + '\'' +
                ", signature=" + signature +
                '}';
    }
}
