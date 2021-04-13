package main.java.service;

import main.java.model.*;
import main.java.repository.ReservationRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(BookCopy bookCopy, User user) throws SQLException {


        deleteReservation(user, bookCopy);

        Reservation reservation = new Reservation();
        reservation.date = LocalDate.now().toString();
        reservation.userId = user.id;
        reservation.bookCopyId = bookCopy.id;

        reservationRepository.save(reservation);
    }

    public void deleteReservation(User user, BookCopy bookCopy) throws SQLException {
        Reservation oldReservation = reservationRepository.findWithValues(user.id, bookCopy.id);
        if (oldReservation != null){
            reservationRepository.remove(oldReservation);
        }
    }

    public List<Reservation> browseReservationUser(User user) throws SQLException {
        return reservationRepository.findWithValues(user);
    }
    public List<Reservation> browseReservationBookCopy(BookCopy bookCopy) throws SQLException {
        return reservationRepository.findWithValues(bookCopy);
    }
}
