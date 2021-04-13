package main.java.controller;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Book;
import main.java.model.PublisherBook;
import main.java.model.User;
import main.java.model.dto.BookDTO;
import main.java.model.dto.UserDTO;
import main.java.repository.*;
import main.java.service.AuthService;
import main.java.service.BookService;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Controller {

    public static Controller istance = new Controller();

    Statement statement = LocalStatementBuilder.getInstance().createStatement();

    private AuthService authService;
    private BookService bookService;
    private BookRepository bookRepository;

    public Controller() {
        createServices();
    }

    private void createServices(){
        bookRepository = new BookRepository(statement);
        AuthorRepository authorRepository = new AuthorRepository(statement);
        PublisherBookRepository publisherBookRepository = new PublisherBookRepository(statement);
        ContactRepository contactRepository = new ContactRepository(statement);
        AddressRepository addressRepository = new AddressRepository(statement);
        BookCopyRepository bookCopyRepository=new BookCopyRepository(statement);
        HireRepository hireRepository = new HireRepository(statement);
        StateRepository stateRepository=new StateRepository(statement);
        ReservationRepository reservationRepository = new ReservationRepository(statement);
        OpinionRepository opinionRepository=new OpinionRepository(statement);
        BookTypeRepository bookTypeRepository=new BookTypeRepository(statement);
        authService = new AuthService(new UserRepository(statement),new UserAuthenticationRepository(statement)
                ,contactRepository,addressRepository);
        bookService = new BookService(bookRepository,authorRepository,publisherBookRepository
                ,bookCopyRepository,hireRepository,stateRepository,reservationRepository
                ,opinionRepository,bookTypeRepository);
    }

    public User login(String login,String password) throws SQLException {
        return authService.login(login,password);
    }

    public List<BookDTO> findBooks(String title) throws SQLException {
        return bookService.getBooks(title);
    }

    public void addNewBook(BookDTO book) throws SQLException {
        bookService.addNewBook(book);
    }

    public void addNewUser(UserDTO userDTO) throws SQLException {

        authService.register(userDTO);

    }

    public Book getByDTO(BookDTO bookDTO) throws SQLException {
        return bookRepository.selectThatBeginWith(bookDTO.title,bookDTO.description).get(0);
    }

    public void addPublisherBook(PublisherBook publisherBook) throws SQLException {
        bookService.addPublisherBook(publisherBook);
    }

    public void removeBook(Book book) throws SQLException {
        bookService.deleteBook(book);
    }

}
