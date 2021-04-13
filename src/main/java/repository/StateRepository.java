package main.java.repository;

import main.java.model.State;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StateRepository extends Repository{

    public StateRepository(Statement statement) {
        super(statement);
    }

    public State findById(int id) throws SQLException {
        String sql = "SELECT * FROM State WHERE id = " + id + ";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        State state = new State();
        state.id = rs.getInt("id");
        state.rate = rs.getString("rate");
        return state;
    }

    public List<State> findAll() throws SQLException {
        String sql = "SELECT * FROM State;";
        ResultSet rs = statement.executeQuery(sql);
        List<State> states = new ArrayList<>();
        while(rs.next()) {
            State state = new State();
            state.id = rs.getInt("id");
            state.rate = rs.getString("rate");
            states.add(state);
        }
        return states;
    }

    public void save(State state) throws SQLException {
        String sql = "INSERT INTO State(rate) VALUES(" + state.rate + ");";
        statement.execute(sql);
    }
}
