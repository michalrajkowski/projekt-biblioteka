package main.java.repository;

import main.java.model.BookCopy;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookCopyRepository extends Repository{

    public BookCopyRepository(Statement statement) {
        super(statement);
    }

    public void save(BookCopy bookCopy) throws SQLException {
        StringBuilder sb=new StringBuilder("INSERT INTO BookCopy(publisherbook_id,state_id");
        if(bookCopy.description!=null)
            sb.append(",description");
        if(bookCopy.signature!=null)
            sb.append(",signature");
        sb.append(") VALUES("+bookCopy.publisherBookId +","+bookCopy.stateId);
        if(bookCopy.description!=null)
            sb.append(",\""+bookCopy.description+"\"");
        if(bookCopy.signature!=null)
            sb.append(","+bookCopy.signature+");");
        String sql = sb.toString();
        statement.execute(sql);
    }

    public void update(BookCopy bookCopy) throws SQLException {
        StringBuilder sb = new StringBuilder("UPDATE BookCopy SET publisherbook_id =" + bookCopy.publisherBookId +
                ", state_id=" + bookCopy.stateId);
        if(bookCopy.description!=null)
            sb.append(", description =\"" + bookCopy.description + "\"");
        else
            sb.append(", description = NULL");
        if(bookCopy.signature!=null)
            sb.append(", signature =" + bookCopy.signature);
        else
            sb.append(", signature = NULL");
        sb.append(" WHERE id = " + bookCopy.id +";");
        String sql = sb.toString();
        statement.execute(sql);
    }

    public void removeById(int id) throws SQLException {
        String sql = "DELETE FROM BookCopy WHERE id =" + id;
        statement.execute(sql);
    }

    public BookCopy findById(int id) throws SQLException {
        String sql = "SELECT * FROM BookCopy WHERE id=" + id;
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        BookCopy bookCopy = getBookCopy(rs);
        return bookCopy;
    }

    public List<BookCopy> findByPublisherBookId(int publisherBookId) throws SQLException {
        String sql = "SELECT * FROM BookCopy WHERE publisherbook_id=" + publisherBookId;
        List<BookCopy> bookCopies = getBookCopies(sql);
        return bookCopies;
    }

    public List<BookCopy> findAll() throws SQLException {
        String sql = "SELECT * FROM BookCopy";
        List<BookCopy> bookCopies = getBookCopies(sql);
        return bookCopies;
    }

    private List<BookCopy> getBookCopies(String sql) throws SQLException {
        List<BookCopy> bookCopies = new ArrayList<>();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            BookCopy bookCopy = getBookCopy(rs);
            bookCopies.add(bookCopy);
        }
        return bookCopies;
    }

    private BookCopy getBookCopy(ResultSet rs) throws SQLException {
        BookCopy bookCopy = new BookCopy();
        bookCopy.id = rs.getInt("id");
        bookCopy.publisherBookId = rs.getInt("publisherbook_id");
        bookCopy.stateId = rs.getInt("state_id");
        bookCopy.description = rs.getString("description");
        BigDecimal signature = rs.getBigDecimal("signature");
        if(signature != null)
            bookCopy.signature = signature.longValue();
        else
            bookCopy.signature = null;
        return bookCopy;
    }
}
