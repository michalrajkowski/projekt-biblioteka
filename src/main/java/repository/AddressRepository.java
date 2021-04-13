package main.java.repository;

import main.java.model.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository extends Repository{

    public AddressRepository(Statement statement) {
        super(statement);
    }

    public void save(Address address) throws SQLException {
        String sql = "INSERT INTO Address(town,street,_number,house_number) VALUES(" +
                "\"" + address.town + "\",";
        StringBuilder sb = new StringBuilder(sql);
        if(address.street != null)
            sb.append("\"" + address.street + "\",");
        else
            sb.append("NULL,");
        sb.append(address.number + ",");
        sb.append("\"" + address.houseNumber + "\");");
        sql = sb.toString();
        statement.execute(sql);
    }

    public Address findById(int id) throws SQLException {
        String sql = "SELECT * FROM ADDRESS WHERE id = " + id +";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        Address address = getAddress(rs);
        return address;
    }

    public List<Address> findByValues(Address address) throws SQLException {
        String sql = "SELECT * FROM Address WHERE town LIKE\"" + address.town +"%\" AND ";
        StringBuilder sb = new StringBuilder(sql);
        if(address.street != null)
            sb.append("street LIKE \"" + address.street + "%\" AND ");
        if(address.number != null)
            sb.append("_number =" + address.number + " AND ");
        sb.append("house_number LIKE \"" + address.houseNumber + "%\"");
        sql = sb.toString();
        ResultSet rs = statement.executeQuery(sql);
        List<Address> addresses = getAddresses(rs);
        return addresses;
    }

    public Address findByUserId(int userId) throws SQLException {
        String sql = "SELECT Address.id, Address.town, Address.street, Address._number, Address.house_number " +
                "FROM _User INNER JOIN ADDRESS ON _User.address_id = Address.id WHERE " +
                "_User.id = " + userId + ";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        return getAddress(rs);
    }

    private Address getAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.id = rs.getInt("id");
        address.town = rs.getString("town");
        address.number = rs.getInt("_number");
        address.street = rs.getString("street");
        address.houseNumber = rs.getString("house_number");
        return address;
    }

    private List<Address> getAddresses(ResultSet rs) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        while(rs.next()){
            Address address1 = getAddress(rs);
            addresses.add(address1);
        }
        return addresses;
    }


}
