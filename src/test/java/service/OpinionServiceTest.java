package test.java.service;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Book;
import main.java.model.Opinion;
import main.java.model.User;
import main.java.repository.OpinionRepository;
import main.java.service.OpinionService;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class OpinionServiceTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final OpinionRepository opinionRepository =
            new OpinionRepository(statement);

    final OpinionService opinionService =
            new OpinionService(opinionRepository);

    @Test
    public void createOpinion() throws SQLException {
        //given
        User user = new User();
        user.id = 1;
        user.name = "Imie";
        user.lastName = "Nazwisko";
        user.birthDate = "1000-10-10";
        user.authId = 999;
        user.contactId = 999;
        user.addressId = 999;
        user.roleId = 999;

        Book book = new Book();
        book.id = 5;
        book.title = "Hobbit";
        book.description = "siema";
        book.releaseDate = "1000-10-10";

        Opinion opinion = new Opinion();
        opinion.text = "Test text";
        opinion.rate = 9;
        opinion.bookId = book.id;
        opinion.userId = user.id;
        //when
        opinionService.createOpinion(9, "Test text", user , book);
        //then
        List<Opinion> opinie = opinionRepository.findByBookId(book.id);
        Opinion actual = opinionService.findOpinionByUserID(opinie, user.id);
        opinion.id = actual.id;
        assertEquals(opinion.toString(),actual.toString());
    }

    @Test
    public void deleteOpinion() throws SQLException {

        User user = new User();
        user.id = 1;
        user.name = "Imie";
        user.lastName = "Nazwisko";
        user.birthDate = "1000-10-10";
        user.authId = 999;
        user.contactId = 999;
        user.addressId = 999;
        user.roleId = 999;

        Book book = new Book();
        book.id = 3;
        book.title = "Hobbit";
        book.description = "siema";
        book.releaseDate = "1000-10-10";

        Opinion opinion_1 = new Opinion();
        opinion_1.text = "Test text";
        opinion_1.rate = 9;
        opinion_1.bookId = 3;
        opinion_1.userId = 1;

        Opinion opinion_2 = new Opinion();
        opinion_2.text = "Test text 2";
        opinion_2.rate = 9;
        opinion_2.bookId = 3;
        opinion_2.userId = 1;

        opinionRepository.save(opinion_1);
        opinionRepository.save(opinion_2);

        List<Opinion> opinie = opinionRepository.findByBookId(book.id);
        Opinion first = opinionService.findOpinionByUserID(opinie, user.id);

        opinionService.deleteOpinion(user, book);

        List<Opinion> opinie2 = opinionRepository.findByBookId(book.id);
        Opinion second = opinionService.findOpinionByUserID(opinie2, user.id);

        assertNotEquals(first.toString(),second.toString());


    }

    @Test
    public void browseOpinions() {
        User user = new User();
        user.id = 4;
        user.name = "Imie";
        user.lastName = "Nazwisko";
        user.birthDate = "1000-10-10";
        user.authId = 999;
        user.contactId = 999;
        user.addressId = 999;
        user.roleId = 999;

        Book book = new Book();
        book.id = 3;
        book.title = "Hobbit";
        book.description = "siema";
        book.releaseDate = "1000-10-10";

        Opinion opinion_1 = new Opinion();
        opinion_1.text = "Test text";
        opinion_1.rate = 9;
        opinion_1.bookId = 3;
        opinion_1.userId = 4;


    }

    @Test
    public void editOpinion() throws SQLException {
        User user = new User();
        user.id = 2;
        user.name = "Imie";
        user.lastName = "Nazwisko";
        user.birthDate = "1000-10-10";
        user.authId = 999;
        user.contactId = 999;
        user.addressId = 999;
        user.roleId = 999;

        Book book = new Book();
        book.id = 4;
        book.title = "Hobbit";
        book.description = "siema";
        book.releaseDate = "1000-10-10";

        Opinion opinion = new Opinion();
        opinion.text = "Test text";
        opinion.rate = 9;
        opinion.bookId = book.id;
        opinion.userId = user.id;

        Opinion edited = new Opinion();
        edited.text = "Edytowany teksts";
        edited.rate = 9;
        edited.bookId = book.id;
        edited.userId = user.id;

        opinionRepository.save(opinion);
        opinionService.editOpinion("Edytowany teksts", user, book);
        List<Opinion> opinie = opinionRepository.findByBookId(book.id);
        Opinion actual = opinionService.findOpinionByUserID(opinie, user.id);
        edited.id = actual.id;
        assertEquals(edited.toString(),actual.toString());

    }

    @Test
    public void findOpinionByUserID() {

    }
}