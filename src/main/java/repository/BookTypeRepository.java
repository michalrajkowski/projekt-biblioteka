package main.java.repository;

import main.java.model.BookType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookTypeRepository extends Repository{

    public BookTypeRepository(Statement statement) {
        super(statement);
    }

    public List<BookType> findAll() throws SQLException {
        String sql = "SELECT * FROM BookType;";
        List<BookType> bookTypes = getBookTypes(sql);
        return bookTypes;
    }

    public List<BookType> findByBookId(int bookId) throws SQLException {
        String sql = "SELECT BookType.id, BookType.type FROM Book_BookType " +
                "INNER JOIN BookType ON BookType.id = Book_BookType.booktype_id " +
                "WHERE Book_BookType.book_id = " + bookId + ";";
        List<BookType> bookTypes = getBookTypes(sql);
        return bookTypes;
    }

    public void typeTheBook(int bookId,int bookTypeId) throws SQLException {
        String sql = "INSERT INTO Book_BookType VALUES(" + bookId + "," + bookTypeId + ")";
        statement.execute(sql);
    }

    public void removeByBookId(int id) throws SQLException {
        String sql = "DELETE FROM Book_BookType WHERE book_id=" + id +";";
        statement.execute(sql);
    }

    private List<BookType> getBookTypes(String sql) throws SQLException {
        ResultSet rs = statement.executeQuery(sql);
        List<BookType> bookTypes = new ArrayList<>();
        while(rs.next()){
            BookType bookType = new BookType();
            bookType.id = rs.getInt("id");
            bookType.type = rs.getString("type");
            bookTypes.add(bookType);
        }
        return bookTypes;
    }

}
