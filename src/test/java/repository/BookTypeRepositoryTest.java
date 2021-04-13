package test.java.repository;
import main.java.factory.LocalStatementBuilder;
import main.java.model.BookType;
import main.java.repository.BookTypeRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookTypeRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final BookTypeRepository bookTypeRepository =
            new BookTypeRepository(statement);

    @Test
    public void shouldTypeTheBook() throws SQLException {
        List<BookType> bookTypes = bookTypeRepository.findByBookId(1);
        assertEquals(bookTypes.size(),1);
        assertEquals(bookTypes.get(0).id,1);
        bookTypeRepository.typeTheBook(1,2);
        bookTypes = bookTypeRepository.findByBookId(1);
        assertEquals(bookTypes.size(),2);
        assertEquals(bookTypes.get(1).id,2);
    }


}
