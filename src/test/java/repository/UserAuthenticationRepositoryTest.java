package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.UserAuthentication;
import main.java.repository.UserAuthenticationRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class UserAuthenticationRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final UserAuthenticationRepository userAuthenticationRepository =
            new UserAuthenticationRepository(statement);

    @Test
    public void shouldSaveUserAuthentication() throws SQLException {
        //given
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.nick = "piesek";
        userAuthentication.login = "leszek";
        userAuthentication.password = "buda";
        //when
        userAuthenticationRepository.save(userAuthentication);
        //then
        UserAuthentication actual = userAuthenticationRepository.findByLogin("leszek");
        userAuthentication.id = actual.id;
        assertEquals(userAuthentication.toString(),actual.toString());
    }


}
