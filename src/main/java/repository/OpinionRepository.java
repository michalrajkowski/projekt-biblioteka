package main.java.repository;

import main.java.model.Opinion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpinionRepository extends Repository{

    public OpinionRepository(Statement statement) {
        super(statement);
    }

    public void save(Opinion opinion) throws SQLException {
        String sql = "INSERT INTO Opinion(rate,text,user_id,book_id) VALUES(" + opinion.rate + ",";
        StringBuilder sb = new StringBuilder(sql);
        if(opinion.text != null)
            sb.append("\"" + opinion.text + "\",");
        else
            sb.append("NULL,");
        sb.append(opinion.userId + "," + opinion.bookId + ");");
        sql = sb.toString();
        statement.execute(sql);
    }

    public List<Opinion> findByBookId(int bookId) throws SQLException {
        String sql = "SELECT * FROM Opinion WHERE book_id = " + bookId + ";";
        ResultSet rs = statement.executeQuery(sql);
        List<Opinion> opinions = new ArrayList<>();
        while(rs.next()){
            Opinion opinion = new Opinion();
            opinion.id = rs.getInt("id");
            opinion.text = rs.getString("text");
            opinion.rate = rs.getInt("rate");
            opinion.userId = rs.getInt("user_id");
            opinion.bookId = rs.getInt("book_id");
            opinions.add(opinion);
        }
        return opinions;
    }

    public void remove(Opinion opinion) throws SQLException {
        String sql = "DELETE FROM Opinion WHERE id = " + opinion.id;
        statement.execute(sql);
    }

}
