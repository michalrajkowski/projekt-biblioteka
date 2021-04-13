package main.java.repository;

import main.java.model.UserAuthentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAuthenticationRepository extends Repository{

    public UserAuthenticationRepository(Statement statement) {
        super(statement);
    }

    public void save(UserAuthentication userAuthentication) throws SQLException {
        String sql = "INSERT INTO UserAuthentication(nick,login,password) VALUES(" +
                "\"" + userAuthentication.nick + "\",\"" + userAuthentication.login + "\",\"" +
                userAuthentication.password + "\");";
        statement.execute(sql);
    }

    public UserAuthentication findByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM UserAuthentication WHERE login = \"" + login + "\";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.id = rs.getInt("id");
        userAuthentication.nick = rs.getString("nick");
        userAuthentication.login = rs.getString("login");
        userAuthentication.password = rs.getString("password");
        return userAuthentication;
    }

}
