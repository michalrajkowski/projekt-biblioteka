package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Address;
import main.java.repository.AddressRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class AddressRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final AddressRepository addressRepository =
            new AddressRepository(statement);

    @Test
    public void shouldSaveAddress() throws SQLException {
        //given
        Address address = new Address();
        address.town = "Garmish-Partenkirchen";
        address.street = "GoldenStrasse";
        address.number = 17;
        address.houseNumber = "34";
        //when
        addressRepository.save(address);
        //then
        Address returnedAddress = addressRepository.findByValues(address).get(0);
        address.id = returnedAddress.id;
        assertEquals(returnedAddress.toString(), address.toString());
    }

    @Test
    public void shouldFindByUserId() throws SQLException {
        Address address = addressRepository.findByUserId(1);
        assertEquals("Warszawa",address.town);
        assertEquals("Lipowa",address.street);
        assertEquals(java.util.Optional.of(24),java.util.Optional.of(address.number));
        assertEquals("16",address.houseNumber);
    }

}
