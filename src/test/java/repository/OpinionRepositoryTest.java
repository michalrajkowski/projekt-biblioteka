package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Opinion;
import main.java.repository.OpinionRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class OpinionRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final OpinionRepository opinionRepository =
            new OpinionRepository(statement);

    @Test
    public void shouldSaveOpinion() throws SQLException {
        //given
        Opinion opinion = new Opinion();
        opinion.text = null;
        opinion.rate = 9;
        opinion.bookId = 3;
        opinion.userId = 4;
        //when
        opinionRepository.save(opinion);
        //then
        Opinion actual = opinionRepository.findByBookId(3).get(1);
        opinion.id = actual.id;
        assertEquals(opinion.toString(),actual.toString());
    }

}
