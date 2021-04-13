package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.BookCopy;
import main.java.repository.BookCopyRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class BookCopyRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final BookCopyRepository bookCopyRepository =
            new BookCopyRepository(statement);

    @Test
    public void shouldSaveBookCopy() throws SQLException {
        //given
        BookCopy bookCopy1 = new BookCopy();
        bookCopy1.publisherBookId = 1;
        bookCopy1.stateId = 1;
        bookCopy1.description = "elo";
        bookCopy1.signature = 9999999999l;
        //when
        bookCopyRepository.save(bookCopy1);
        //then
        BookCopy bookCopy2 = bookCopyRepository.findByPublisherBookId(1).get(1);
        bookCopy1.id=bookCopy2.id;
        assertEquals(bookCopy2.toString(),bookCopy1.toString());
    }

    @Test
    public void shouldReturnBookCopy() throws SQLException {
        //when
        BookCopy bookCopy = bookCopyRepository.findById(1);
        //then
        assertEquals(bookCopy.id,1);
        assertEquals(bookCopy.publisherBookId,1);
        assertEquals(bookCopy.stateId,1);
        assertEquals(bookCopy.description,"opis1");
        assertEquals(1111111111l,bookCopy.signature.longValue());

    }

    @Test
    public void shouldUpdateBookCopy() throws SQLException {
        //given
        BookCopy bookCopy1 = bookCopyRepository.findById(2);
        bookCopy1.publisherBookId=3;
        bookCopy1.stateId=3;
        bookCopy1.description="123";
        bookCopy1.signature=9l;
        //when
        bookCopyRepository.update(bookCopy1);
        //then
        BookCopy bookCopy2 = bookCopyRepository.findById(2);
        assertEquals(bookCopy1.toString(),bookCopy2.toString());
    }

}
