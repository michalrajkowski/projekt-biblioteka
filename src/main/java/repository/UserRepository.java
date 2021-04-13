package main.java.repository;

import main.java.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository extends Repository{

    public UserRepository(Statement statement) {
        super(statement);
    }

    public void save(User user) throws SQLException {
        String sql = "INSERT INTO _User(name,last_name,birth_date,auth_id,contact_id,address_id,role_id) " +
                "VALUES(\"" + user.name + "\",\"" + user.lastName + "\",\"" + user.birthDate + "\"," + user.authId +
                "," + user.contactId + "," + user.addressId + "," + user.roleId + ");";
        statement.execute(sql);
    }

    public User findByNick(String nick) throws SQLException {
        String sql = "SELECT _User.* FROM _User INNER JOIN UserAuthentication ON _User.auth_id = UserAuthentication.id " +
                "WHERE UserAuthentication.nick = \"" + nick + "\";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        User user = getUser(rs);
        return user;
    }

    public User findByLogin(String login) throws SQLException {
        String sql = "SELECT _User.* FROM _User INNER JOIN UserAuthentication ON _User.auth_id = UserAuthentication.id " +
                "WHERE UserAuthentication.login = \"" + login + "\";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        User user = getUser(rs);
        return user;
    }

    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM _USER WHERE id = " + id + ";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        User user = getUser(rs);
        return user;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.id = rs.getInt("id");
        user.name = rs.getString("name");
        user.lastName = rs.getString("last_name");
        user.birthDate = rs.getString("birth_date");
        user.authId = rs.getInt("auth_id");
        user.contactId = rs.getInt("contact_id");
        user.addressId = rs.getInt("address_id");
        user.roleId = rs.getInt("role_id");
        return user;
    }

}
