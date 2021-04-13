package main.java.repository;

import main.java.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository extends Repository{

    public AuthorRepository(Statement statement) {
        super(statement);
    }

    public void save(Author author) throws SQLException {
        String sql = "INSERT INTO Author(name,last_name) " +
                "VALUES(\"" + author.name + "\",\"" + author.lastName + "\");";
        statement.execute(sql);
    }

    public List<Author> findByBookId(int bookId) throws SQLException {
        String sql = "SELECT Author.id,Author.name,Author.last_name FROM Book_Author " +
                "INNER JOIN Author ON Author.id = Book_Author.author_id " +
                "WHERE Book_Author.book_id = " + bookId + ";";
        List<Author> authors = getAuthors(sql);
        return authors;
    }

    public void removeById(int id) throws SQLException {
        String sql="DELETE FROM Book_Author WHERE author_id = "+ id +";";
        statement.execute(sql);
    }

    public List<Author> findByNameAndLastName(String name,String lastName) throws SQLException {
        String sql = "SELECT * FROM Author WHERE name LIKE \"" + name + "%\" " +
                "AND last_name LIKE \"" + lastName + "%\";";
        List<Author> authors = getAuthors(sql);
        return authors;
    }

    public void giveBookToAuthorById(int bookId,int authorId) throws SQLException {
        String sql = "INSERT INTO Book_Author VALUES(" + bookId + "," + authorId + ");";
        statement.execute(sql);
    }

    private List<Author> getAuthors(String sql) throws SQLException {
        ResultSet rs = statement.executeQuery(sql);
        List<Author> authors = new ArrayList<>();
        while(rs.next()){
            Author author=new Author();
            author.id = rs.getInt("id");
            author.name = rs.getString("name");
            author.lastName = rs.getString("last_name");
            authors.add(author);
        }
        return authors;
    }

}
