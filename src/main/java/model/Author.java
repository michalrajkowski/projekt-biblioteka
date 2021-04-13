package main.java.model;

public class Author {

    public int id;
    public String name;
    public String lastName;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
