package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Contact;
import main.java.repository.ContactRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class ContactRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final ContactRepository contactRepository =
            new ContactRepository(statement);

    @Test
    public void shouldfindByUserId() throws SQLException {
        Contact found = contactRepository.findByUserId(1);
        assertEquals(1,found.id);
    }

    @Test
    public void shouldUpdateContact() throws SQLException {
        //given
        Contact contact = new Contact();
        contact.id = 2;
        contact.email = null;
        contact.phone = null;
        //when
        contactRepository.update(contact);
        //then
        assertEquals(contact.toString(),contactRepository.findById(2).toString());
    }

}
