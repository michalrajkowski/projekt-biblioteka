package main.java.repository;

import main.java.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactRepository extends Repository{

    public ContactRepository(Statement statement) {
        super(statement);
    }

    public void save(Contact contact) throws SQLException {
        String sql = "INSERT INTO Contact(email,phone) VALUES(";
        StringBuilder sb = new StringBuilder(sql);
        if(contact.email != null)
            sb.append("\"" + contact.email + "\",");
        else
            sb.append("NULL,");
        if(contact.phone != null)
            sb.append(contact.phone + ");");
        else
            sb.append("NULL);");
        sql=sb.toString();
        statement.execute(sql);
    }

    public void update(Contact contact) throws SQLException {
        StringBuilder sb = new StringBuilder("UPDATE Contact SET ");
        if(contact.email != null)
            sb.append("email = \"" + contact.email + "\"");
        else
            sb.append("email = NULL");
        if(contact.phone != null)
            sb.append(",phone = " + contact.phone);
        else
            sb.append(",phone = NULL");

        sb.append(" WHERE id = " + contact.id);

        statement.execute(sb.toString());
    }

    public Contact findById(int id) throws SQLException {
        String sql = "SELECT * FROM Contact WHERE id = " + id + ";";
        ResultSet rs = statement.executeQuery(sql);
        Contact contact = getContact(rs);
        return contact;
    }

    public Contact findByUserId(int userId) throws SQLException {
        String sql = "SELECT Contact.id,Contact.email,Contact.phone FROM _User" +
                " INNER JOIN Contact ON _User.contact_id = Contact.id" +
                " WHERE _User.id = " + userId + ";";
        ResultSet rs = statement.executeQuery(sql);
        Contact contact = getContact(rs);
        return contact;
    }

    public int getIdOf(Contact contact) throws SQLException {
        String sql = "SELECT id FROM Contact WHERE " +
                "email = " + "\"" + contact.email + "\" AND " +
                "phone = " + contact.phone +";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        return rs.getInt("id");
    }

    private Contact getContact(ResultSet rs) throws SQLException {
        rs.next();
        Contact contact = new Contact();
        contact.id = rs.getInt("id");
        contact.email = rs.getString("email");
        Object phone = rs.getObject("phone");
        if(phone != null)
            contact.phone = Long.parseLong((String) phone);
        else
            phone = null;
        return contact;
    }

}
