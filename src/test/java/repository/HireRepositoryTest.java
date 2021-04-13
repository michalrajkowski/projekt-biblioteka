package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Hire;
import main.java.repository.HireRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class HireRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final HireRepository hireRepository =
            new HireRepository(statement);

    @Test
    public void shouldSaveHire() throws SQLException {
        //given
        Hire hire = new Hire();
        hire.returnDate = "2000-10-10";
        hire.hireDate = "2001-10-10";
        hire.userId = 3;
        hire.bookCopyId = 4;
        //when
        hireRepository.save(hire);
        //then
        Hire actual = hireRepository.findWhereDeliveryDateIsNullAndBy(null,4).get(0);
        hire.id=actual.id;
        assertEquals(hire.toString(),actual.toString());
    }

}
