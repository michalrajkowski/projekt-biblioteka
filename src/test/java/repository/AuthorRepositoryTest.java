package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Author;
import main.java.repository.AuthorRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AuthorRepositoryTest{

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final AuthorRepository authorRepository =
                new AuthorRepository(statement);


    @Test
    public void shouldGiveBookToAuthor() throws SQLException {
        List<Author> authors = authorRepository.findByBookId(1);
        assertEquals(authors.size(),1);
        authorRepository.giveBookToAuthorById(1,2);
        authors = authorRepository.findByBookId(1);
        assertEquals(2,authors.size());
    }

    @Test
    public void shouldFind_AdamMickiewicz_ByNameAndLastName() throws SQLException {
        List<Author> authors = authorRepository.findByNameAndLastName("Adam", "Mic");
        assertEquals(authors.get(0).name,"Adam");
        assertEquals("Mickiewicz",authors.get(0).lastName);
    }

}
