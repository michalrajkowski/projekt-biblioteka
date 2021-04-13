package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.User;
import main.java.repository.UserRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final UserRepository userRepository =
            new UserRepository(statement);

    @Test
    public void shouldSaveUser() throws SQLException {
        //given
        User user = new User(null,"Kuba","Kubakowski","2000-10-10",6,6,1,1);
        //when
        userRepository.save(user);
        //then
        User actual = userRepository.findByNick("kubcio");
        user.id=actual.id;
        assertEquals(user.toString(),actual.toString());
        actual = userRepository.findByLogin("kubcio");
        assertEquals(user.toString(),actual.toString());
    }

}
