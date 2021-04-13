package main.java.service;

import main.java.exception.AuthException;
import main.java.model.Address;
import main.java.model.Contact;
import main.java.model.User;
import main.java.model.UserAuthentication;
import main.java.model.dto.UserDTO;
import main.java.repository.AddressRepository;
import main.java.repository.ContactRepository;
import main.java.repository.UserAuthenticationRepository;
import main.java.repository.UserRepository;

import java.sql.SQLException;

public class AuthService {

    private UserRepository userRepository;
    private UserAuthenticationRepository userAuthenticationRepository;
    private ContactRepository contactRepository;
    private AddressRepository addressRepository;

    public AuthService(UserRepository userRepository
            , UserAuthenticationRepository userAuthenticationRepository
            , ContactRepository contactRepository
            , AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.userAuthenticationRepository = userAuthenticationRepository;
        this.contactRepository=contactRepository;
        this.addressRepository=addressRepository;
    }

    public User login(String login,String password) throws SQLException {
        UserAuthentication ua = userAuthenticationRepository.findByLogin(login);
        if(ua.password.equals(password)){
            return userRepository.findByLogin(login);
        }else
            throw new AuthException("Niepoprawne hasło!");
    }

    public void register(UserDTO userDTO) throws SQLException {
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.login=userDTO.login;
        userAuthentication.password=userDTO.password;
        userAuthentication.nick = userDTO.nick;
        userAuthenticationRepository.save(userAuthentication);
        userAuthentication = userAuthenticationRepository.findByLogin(userDTO.login);

        Contact contact = new Contact();
        contact.phone=userDTO.phone;
        contact.email=userDTO.email;

        contactRepository.save(contact);
        contact.id = contactRepository.getIdOf(contact);

        Address address = new Address();
        address.houseNumber = userDTO.houseNumber;
        address.number = userDTO.number;
        address.street = userDTO.street;
        address.town = userDTO.town;

        addressRepository.save(address);
        address = addressRepository.findByValues(address).get(0);

        User user = new User(null,userDTO.name,userDTO.last_name,userDTO.birthDate,userAuthentication.id
        ,contact.id,address.id,2);

        userRepository.save(user);

    }

}
