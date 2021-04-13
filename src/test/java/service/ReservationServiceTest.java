package test.java.service;

import main.java.factory.LocalStatementBuilder;
import main.java.model.BookCopy;
import main.java.model.Reservation;
import main.java.model.User;
import main.java.repository.ReservationRepository;
import main.java.service.ReservationService;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReservationServiceTest {
    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final ReservationRepository reservationRepository =
            new ReservationRepository(statement);

    final ReservationService reservationService =
            new ReservationService(reservationRepository);
    @Test
    public void createReservation() throws SQLException {
        //given
        User user = new User();
        user.id = 3;
        user.name = "Imie";
        user.lastName = "Nazwisko";
        user.birthDate = "1000-10-10";
        user.authId = 999;
        user.contactId = 999;
        user.addressId = 999;
        user.roleId = 999;

        BookCopy bookCopy1 = new BookCopy();
        bookCopy1.id = 1;
        bookCopy1.publisherBookId = 1;
        bookCopy1.stateId = 1;
        bookCopy1.description = "elo";
        bookCopy1.signature = 9999999999l;

        Reservation reservation = new Reservation();
        reservation.date = LocalDate.now().toString();
        reservation.userId = 3;
        reservation.bookCopyId = 1;
        //when
        reservationService.createReservation(bookCopy1, user);
        //then
        Reservation actual = reservationRepository.findWithValues(user.id, bookCopy1.id);
        reservation.id = actual.id;
        assertEquals(reservation.toString(),actual.toString());
    }

    @Test
    public void deleteReservation() throws SQLException {
        User user = new User();
        user.id = 3;
        user.name = "Imie";
        user.lastName = "Nazwisko";
        user.birthDate = "1000-10-10";
        user.authId = 999;
        user.contactId = 999;
        user.addressId = 999;
        user.roleId = 999;

        BookCopy bookCopy1 = new BookCopy();
        bookCopy1.id = 2;
        bookCopy1.publisherBookId = 1;
        bookCopy1.stateId = 1;
        bookCopy1.description = "elo";
        bookCopy1.signature = 9999999999l;

        Reservation reservation = new Reservation();
        reservation.date = LocalDate.now().toString();;
        reservation.userId = 3;
        reservation.bookCopyId = 2;

        reservationRepository.save(reservation);

        Reservation actual = reservationRepository.findWithValues(user.id, bookCopy1.id);
        reservation.id = actual.id;
        assertEquals(reservation.toString(),actual.toString());

        reservationService.deleteReservation(user, bookCopy1);

        actual = reservationRepository.findWithValues(user.id, bookCopy1.id);
        assertEquals(null, actual);


    }

    @Test
    public void browseReservationUser() {

    }

    @Test
    public void browseReservationBookCopy() {

    }
}