package main.java.repository;


import main.java.model.BookCopy;
import main.java.model.Opinion;
import main.java.model.Reservation;
import main.java.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository extends Repository{

    public ReservationRepository(Statement statement) {
        super(statement);
    }

    public void save(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO Reservation(_date,user_id,bookcopy_id) VALUES("+
                "\"" + reservation.date + "\"," + reservation.userId + "," + reservation.bookCopyId + ");";
        statement.execute(sql);
    }

    public void remove(Reservation reservation) throws SQLException {
        String sql = "DELETE FROM Reservation WHERE id = " + reservation.id;
        statement.execute(sql);
    }

    public List<Reservation> findWithValues(String date, Integer userId,Integer bookCopyId) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * FROM Reservation");
        if(date != null || userId != null || bookCopyId != null) {
            sb.append(" WHERE ");
            if (date != null)
                sb.append("_date <= \"" + date + "\" AND ");
            if (userId != null)
                sb.append("user_id = " + userId + " AND ");
            if (bookCopyId != null)
                sb.append("bookcopy_id = " + bookCopyId + " AND ");
            sb.delete(sb.length()-4,sb.length());
        }
        String sql = sb.toString();
        ResultSet rs = statement.executeQuery(sql);
        List<Reservation> reservations = new ArrayList<>();

        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.id = rs.getInt("id");
            reservation.date = rs.getDate("_date").toString();
            reservation.userId = rs.getInt("user_id");
            reservation.bookCopyId = rs.getInt("bookcopy_id");
            reservations.add(reservation);
        }
        return reservations;
    }

    public List<Reservation> findWithValues(User user) throws SQLException {
        Integer userId = user.id;
        StringBuilder sb = new StringBuilder("SELECT * FROM Reservation");
        if(userId != null) {
            sb.append(" WHERE ");
            if (userId != null)
                sb.append("user_id = " + userId + " AND ");
            sb.delete(sb.length()-4,sb.length());
        }
        String sql = sb.toString();
        ResultSet rs = statement.executeQuery(sql);
        List<Reservation> reservations = new ArrayList<>();

        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.id = rs.getInt("id");
            reservation.date = rs.getDate("_date").toString();
            reservation.userId = rs.getInt("user_id");
            reservation.bookCopyId = rs.getInt("bookcopy_id");
            reservations.add(reservation);
        }
        return reservations;
    }

    public List<Reservation> findWithValues(BookCopy bookCopy) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * FROM Reservation");
        Integer bookCopyId = bookCopy.id;
        if(bookCopyId != null) {
            sb.append(" WHERE ");
            if (bookCopyId != null)
                sb.append("bookcopy_id = " + bookCopyId + " AND ");
            sb.delete(sb.length()-4,sb.length());
        }
        String sql = sb.toString();
        ResultSet rs = statement.executeQuery(sql);
        List<Reservation> reservations = new ArrayList<>();

        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.id = rs.getInt("id");
            reservation.date = rs.getDate("_date").toString();
            reservation.userId = rs.getInt("user_id");
            reservation.bookCopyId = rs.getInt("bookcopy_id");
            reservations.add(reservation);
        }
        return reservations;
    }

    public Reservation findWithValues(Integer userId,Integer bookCopyId) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * FROM Reservation");
        if(userId != null || bookCopyId != null) {
            sb.append(" WHERE ");
            if (userId != null)
                sb.append("user_id = " + userId + " AND ");
            if (bookCopyId != null)
                sb.append("bookcopy_id = " + bookCopyId + " AND ");
            sb.delete(sb.length()-4,sb.length());
        }
        String sql = sb.toString();
        ResultSet rs = statement.executeQuery(sql);
        List<Reservation> reservations = new ArrayList<>();

        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.id = rs.getInt("id");
            reservation.date = rs.getDate("_date").toString();
            reservation.userId = rs.getInt("user_id");
            reservation.bookCopyId = rs.getInt("bookcopy_id");
            reservations.add(reservation);
        }
        if(reservations.size() > 0){
            return reservations.get(0);
        }else{
            return null;
        }
    }

}
