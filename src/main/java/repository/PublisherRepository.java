package main.java.repository;

import main.java.model.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PublisherRepository extends Repository{

    public PublisherRepository(Statement statement) {
        super(statement);
    }

    public Publisher findById(int id) throws SQLException {
        String sql = "SELECT * FROM Publisher WHERE id = " + id + ";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Publisher publisher = new Publisher();
        publisher.id = rs.getInt("id");
        publisher.name = rs.getString("name");
        return publisher;
    }

    public List<Publisher> findAll() throws SQLException {
        String sql = "SELECT * FROM Publisher;";
        ResultSet rs = statement.executeQuery(sql);
        List<Publisher> publishers = new ArrayList<>();
        while(rs.next()) {
            Publisher publisher = new Publisher();
            publisher.id = rs.getInt("id");
            publisher.name = rs.getString("name");
            publishers.add(publisher);
        }
        return publishers;
    }

    public void save(Publisher publisher) throws SQLException {
        String sql = "INSERT INTO Publisher(name) VALUES(" + publisher.name + ");";
        statement.execute(sql);
    }

}
