package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Book;
import main.java.repository.BookRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final BookRepository bookRepository =
            new BookRepository(statement);

    @Test
    public void shouldReturnPanTadeuszBook() throws SQLException {
        //when
        Book book = bookRepository
                .selectThatBeginWith("Pan Tadeusz","fajna książka").get(0);
        //then
        assertEquals(book.title,"Pan Tadeusz");
        assertEquals("fajna książka",book.description);

    }

    @Test
    public void shouldSaveBook() throws SQLException {
        //given
        Book book = new Book();
        book.title = "Hobbit";
        book.description = "siema";
        book.releaseDate = "1000-10-10";
        //when
        bookRepository.save(book);
        //then
        List<Book> books = bookRepository.selectThatBeginWith("Hobbit","siema");
        assertEquals(books.size(),1);
        book.id=books.get(0).id;
        assertEquals(books.get(0).toString(),book.toString());
    }

    @Test
    public void shouldUpdateBook() throws SQLException {
        //given
        Book book1 = bookRepository
                .selectThatBeginWith("Lalka","").get(0);

        book1.title = "Lallka";
        book1.description="zmieniono";
        book1.releaseDate = "2020-11-12";
        //when
        bookRepository.update(book1);
        //then
        assertEquals(bookRepository
                .selectThatBeginWith("Lalka","").size(),0);

        Book book2 = bookRepository
                .selectThatBeginWith("Lallka","").get(0);

        assertEquals(book2.toString(),book1.toString());
    }

    @Test
    public void shouldRemoveBook() throws SQLException {
        //given
        Book book = bookRepository
                .selectThatBeginWith("Wojna","wojna").get(0);
        //when
        bookRepository.removeById(book.id);
        //then
        assertEquals(bookRepository
                .selectThatBeginWith("Wojna","wojna").size(),0);
    }

    @Test
    public void shouldReturnELAYPublisherBooks() throws SQLException {
        //when
        List<Book> books = bookRepository.findBooksByPublisherId(4);
        //then
        assertEquals(books.size(),1);
    }

}
