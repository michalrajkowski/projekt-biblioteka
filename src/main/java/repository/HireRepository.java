package main.java.repository;

import main.java.model.Hire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HireRepository extends Repository{

    public HireRepository(Statement statement) {
        super(statement);
    }

    public void save(Hire hire) throws SQLException {
        String sql = "INSERT INTO Hire(return_date,hire_date,delivery_date,user_id,bookcopy_id) VALUES(" +
                "\"" + hire.returnDate + "\",\"" + hire.hireDate + "\",";
        StringBuilder sb = new StringBuilder(sql);
        if(hire.deliveryDate != null)
            sb.append("\"" + hire.deliveryDate + "\",");
        else
            sb.append("NULL,");
        sb.append(hire.userId + "," + hire.bookCopyId + ");");
        sql = sb.toString();
        statement.execute(sql);
    }

    public void removeById(int id) throws SQLException {
        String sql = "DELETE FROM Hire WHERE id = " + id +";";
        statement.execute(sql);
    }

    public List<Hire> findWhereDeliveryDateIsNullAndBy(Integer userId,Integer bookCopyId) throws SQLException {
        String sql = "SELECT * FROM Hire WHERE delivery_date IS NULL AND ";
        StringBuilder sb = new StringBuilder(sql);
        if(userId != null)
            sb.append("user_id = " + userId + " AND ");
        if(bookCopyId != null)
            sb.append("bookcopy_id = " + bookCopyId + " AND ");
        sb.delete(sb.length()-4,sb.length());
        sql = sb.toString();
        ResultSet rs = statement.executeQuery(sql);
        List<Hire> hires = new ArrayList<>();
        while(rs.next()){
            Hire hire = new Hire();
            hire.id = rs.getInt("id");
            hire.returnDate = rs.getDate("return_date").toString();
            hire.hireDate = rs.getDate("hire_date").toString();
            Date delivery_date = rs.getDate("delivery_date");
            if(delivery_date != null)
                hire.deliveryDate = delivery_date.toString();
            hire.userId = rs.getInt("user_id");
            hire.bookCopyId = rs.getInt("bookcopy_id");
            hires.add(hire);
        }
        return hires;
    }

    public List<Hire> selectByBookCopyId(int id) throws SQLException {
        String sql = "SELECT * FROM Hire WHERE bookcopy_id = " + id +";";
        ResultSet rs = statement.executeQuery(sql);
        List<Hire> hires = new ArrayList<>();
        while(rs.next()){
            Hire hire = new Hire();
            hire.id = rs.getInt("id");
            hire.returnDate = rs.getDate("return_date").toString();
            hire.hireDate = rs.getDate("hire_date").toString();
            Date delivery_date = rs.getDate("delivery_date");
            if(delivery_date != null)
                hire.deliveryDate = delivery_date.toString();
            hire.userId = rs.getInt("user_id");
            hire.bookCopyId = rs.getInt("bookcopy_id");
            hires.add(hire);
        }
        return hires;
    }

}
