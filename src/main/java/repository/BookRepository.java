package main.java.repository;

import main.java.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends Repository {

    public BookRepository(Statement statement) {
        super(statement);
    }

    public void save(Book book) throws SQLException {

        String sql = "INSERT INTO Book(title,description,release_date) " +
                "VALUES(\"" + book.title + "\",\"" + book.description + "\"," +
                "\"" + book.releaseDate + "\");";

        statement.execute(sql);

    }

    public void update(Book book) throws SQLException {

        StringBuilder sb = new StringBuilder("UPDATE Book " +
                "SET title = \"" + book.title + "\" ");

        if (book.description != null)
            sb.append(", description =\"" + book.description + "\" ");
        else
            sb.append(", description = NULL");

        if (book.releaseDate != null)
            sb.append(", release_date =\"" + book.releaseDate + "\" ");
        else
            sb.append(", release_date = NULL ");

        sb.append("WHERE id = " + book.id + ";");

        String sql = sb.toString();

        statement.execute(sql);
    }

    public void removeById(int id) throws SQLException {

        String sql = "DELETE FROM Book WHERE id =" + id;

        statement.execute(sql);

    }

    public List<Book> selectThatBeginWith(String title, String description) throws SQLException {

        String sql = null;

        if (description != null)
            sql = "SELECT * FROM Book " +
                    "WHERE title LIKE \"" + title + "%\"" +
                    "AND description LIKE \"" + description + "%\";";
        else
            sql = "SELECT * FROM Book " +
                    "WHERE title LIKE \"" + title + "%\";";

        List<Book> books = getBooks(sql);

        return books;

    }

    public Book findById(int id) throws SQLException {
        String sql = "SELECT * FROM Book WHERE id = " + id;
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Book book = new Book();
        book.id = rs.getInt("id");
        book.title = rs.getString("title");
        book.description = rs.getString("description");
        book.releaseDate = rs.getDate("release_date").toString();
        return book;
    }

    public List<Book> findAll() throws SQLException {
        String sql = "SELECT * FROM Book";

        List<Book> books = getBooks(sql);

        return books;
    }

    private List<Book> getBooks(String sql) throws SQLException {
        ResultSet rs = statement.executeQuery(sql);

        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            Book book = new Book();

            book.id = rs.getInt("id");
            book.title = rs.getString("title");
            book.description = rs.getString("description");
            book.releaseDate = rs.getDate("release_date").toString();

            books.add(book);
        }
        return books;
    }

    public List<Book> findBooksByPublisherId(int publisherId) throws SQLException {
        String sql = "SELECT Book.id, Book.title, Book.description, Book.release_date FROM PublisherBook " +
                "INNER JOIN Book ON Book.id = PublisherBook.book_id " +
                "INNER JOIN Publisher ON Publisher.id = PublisherBook.publisher_id " +
                "WHERE Publisher.id = " + publisherId + ";";

        List<Book> books = getBooks(sql);
        return books;
    }


}

