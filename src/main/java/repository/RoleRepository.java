package main.java.repository;

import main.java.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository{

    public RoleRepository(Statement statement) {
        super(statement);
    }

    public List<Role> findAll() throws SQLException {
        String sql = "SELECT * FROM Role";
        ResultSet rs = statement.executeQuery(sql);
        List<Role> roles = getRoles(rs);
        return roles;
    }

    private List<Role> getRoles(ResultSet rs) throws SQLException {
        List<Role> roles = new ArrayList<>();
        while(rs.next()){
            Role role = getRole(rs);
            roles.add(role);
        }
        return roles;
    }

    private Role getRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.id = rs.getInt("id");
        role.roleName = rs.getString("role_name");
        return role;
    }

    public Role findByUserId(int userId) throws SQLException {
        String sql = "SELECT Role.id,Role.role_name FROM _User " +
                "INNER JOIN Role ON _User.role_id = Role.id " +
                "WHERE _User.id =" + userId + ";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Role role = getRole(rs);
        return role;
    }

}
