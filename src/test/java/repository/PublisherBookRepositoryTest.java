package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.PublisherBook;
import main.java.repository.PublisherBookRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PublisherBookRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final PublisherBookRepository publisherBookRepository = new PublisherBookRepository(statement);

    @Test
    public void shouldSavePublisherBook() throws SQLException {
        //given
        PublisherBook publisherBook = new PublisherBook();
        publisherBook.publisherId = 1;
        publisherBook.img = "img";
        publisherBook.isbn = 1111111111111l;
        publisherBook.bookId=1;
        publisherBook.pages=12;
        //when
        publisherBookRepository.save(publisherBook);
        //then
        List<PublisherBook> publisherBooks = publisherBookRepository.findByBookId(1);
        assertEquals(2,publisherBooks.size());
        assertEquals(publisherBooks.get(1).isbn.longValue(),1111111111111l);
    }

    @Test
    public void shouldFindPublisherBook() throws SQLException {
        //when
        PublisherBook publisherBook = publisherBookRepository.findById(1);
        //then
        assertEquals(publisherBook.id,1);
        assertEquals(publisherBook.bookId,1);
        assertEquals(publisherBook.publisherId,1);
        assertEquals(publisherBook.pages.intValue(),10);
        assertEquals(1122222222222l,publisherBook.isbn.longValue());
    }

    @Test
    public void shouldUpdatePublisherBook() throws SQLException {
        //given
        PublisherBook publisherBook1 = publisherBookRepository.findById(2);
        publisherBook1.bookId=2;
        publisherBook1.publisherId=2;
        publisherBook1.pages=null;
        publisherBook1.isbn=null;
        //when
        publisherBookRepository.update(publisherBook1);
        //then
        PublisherBook publisherBook2 = publisherBookRepository.findById(2);
        assertEquals(publisherBook1.toString(),publisherBook2.toString());
    }

}
