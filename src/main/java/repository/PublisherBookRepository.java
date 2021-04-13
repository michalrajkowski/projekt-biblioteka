package main.java.repository;

import main.java.model.PublisherBook;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PublisherBookRepository extends Repository{

    public PublisherBookRepository(Statement statement) {
        super(statement);
    }

    public void save(PublisherBook publisherBook) throws SQLException {

        StringBuilder sb =new StringBuilder("INSERT INTO PublisherBook(img,pages,isbn,book_id,publisher_id) VALUES(");

        if(publisherBook.img!=null)
            sb.append("\"" + publisherBook.img +"\",");
        else
            sb.append("NULL,");
        if(publisherBook.pages!=null)
            sb.append("" + publisherBook.pages +",");
        else
            sb.append("NULL,");
        if(publisherBook.isbn!=null)
            sb.append("" + publisherBook.isbn.toString() +",");
        else
            sb.append("NULL,");

        sb.append("" + publisherBook.bookId +"," + publisherBook.publisherId + ");");

        String sql = sb.toString();
        statement.execute(sql);
    }

    public void update(PublisherBook publisherBook) throws SQLException {

        StringBuilder sb = new StringBuilder("UPDATE PublisherBook " +
                "SET book_id = " + publisherBook.bookId + ", publisher_id = " + publisherBook.publisherId);

        if(publisherBook.img != null)
            sb.append(", img =\"" + publisherBook.img + "\"");

        else
            sb.append(", img = NULL");
        if(publisherBook.pages != null)
            sb.append(", pages = " + publisherBook.pages.intValue() );
        else
            sb.append(", pages = NULL" );
        if(publisherBook.isbn != null)
            sb.append(", isbn = " + publisherBook.isbn.longValue() );
        else
            sb.append(", isbn = NULL");

        sb.append(" WHERE id = " + publisherBook.id +";");

        String sql = sb.toString();

        statement.execute(sql);
    }

    public void removeById(int id) throws SQLException {

        String sql = "DELETE FROM PublisherBook WHERE id = "+ id;

        statement.execute(sql);

    }

    public PublisherBook findById(int id) throws SQLException {
        String sql = "SELECT * FROM PublisherBook WHERE id = " + id;
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        PublisherBook publisherBook = getPublisherBook(rs);
        return publisherBook;
    }

    private PublisherBook getPublisherBook(ResultSet rs) throws SQLException {
        PublisherBook publisherBook = new PublisherBook();
        publisherBook.id= rs.getInt("id");
        Object pages = rs.getObject("pages");
        if(pages != null)
            publisherBook.pages = (Integer) pages;
        else
            publisherBook.pages=null;
        publisherBook.bookId= rs.getInt("book_id");
        publisherBook.publisherId= rs.getInt("publisher_id");
        BigDecimal isbn = rs.getBigDecimal("isbn");
        if(isbn != null)
            publisherBook.isbn = isbn.longValue();
        else
            publisherBook.isbn=null;
        publisherBook.img= rs.getString("img");
        return publisherBook;
    }

    public List<PublisherBook> findByBookId(int bookId) throws SQLException {
        String sql = "SELECT * FROM PublisherBook WHERE book_id = " + bookId;
        List<PublisherBook> publisherBooks = getPublisherBooks(sql);
        return publisherBooks;
    }

    public List<PublisherBook> findAll() throws SQLException {
        String sql = "SELECT * FROM PublisherBook";
        List<PublisherBook> publisherBooks = getPublisherBooks(sql);
        return publisherBooks;
    }

    private List<PublisherBook> getPublisherBooks(String sql) throws SQLException {
        ResultSet rs = statement.executeQuery(sql);
        List<PublisherBook> publisherBooks = new ArrayList<>();
        while (rs.next()) {
            PublisherBook publisherBook = getPublisherBook(rs);
            publisherBooks.add(publisherBook);
        }
        return publisherBooks;
    }
}
