package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Reservation;
import main.java.repository.ReservationRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReservationRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final ReservationRepository reservationRepository =
            new ReservationRepository(statement);

    @Test
    public void shouldFindReservationsBeforeSpecificDate() throws SQLException {
        List<Reservation> reservations = reservationRepository.findWithValues("1350-10-10", null, null);
        assertEquals(2, reservations.size());
    }

    @Test
    public void shouldSaveReservation() throws SQLException {
        //given
        Reservation reservation = new Reservation();
        reservation.date = "2000-10-10";
        reservation.userId = 4;
        reservation.bookCopyId = 5;
        //when
        reservationRepository.save(reservation);
        //then
        Reservation actual = reservationRepository.findWithValues("2000-10-10",4,5).get(0);
        reservation.id = actual.id;
        assertEquals(reservation.toString(),actual.toString());
    }


}
