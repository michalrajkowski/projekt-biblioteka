package main.java.service;

import main.java.model.*;
import main.java.model.dto.BookDTO;
import main.java.repository.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublisherBookRepository publisherBookRepository;
    private BookCopyRepository bookCopyRepository;
    private HireRepository hireRepository;
    private StateRepository stateRepository;
    private ReservationRepository reservationRepository;
    private OpinionRepository opinionRepository;
    private BookTypeRepository bookTypeRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository
            , PublisherBookRepository publisherBookRepository, BookCopyRepository bookCopyRepository, HireRepository hireRepository
            ,StateRepository stateRepository, ReservationRepository reservationRepository,OpinionRepository opinionRepository
            ,BookTypeRepository bookTypeRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherBookRepository = publisherBookRepository;
        this.bookCopyRepository=bookCopyRepository;
        this.hireRepository=hireRepository;
        this.stateRepository=stateRepository;
        this.reservationRepository = reservationRepository;
        this.opinionRepository=opinionRepository;
        this.bookTypeRepository=bookTypeRepository;
    }

    public List<BookDTO> getBooks(String title) throws SQLException {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookRepository.selectThatBeginWith(title,"");

        for(Book b:books){
            BookDTO bookDTO=new BookDTO();
            Author a = new Author();
            a.name = "nieznany";
            a.lastName = "";
            if(authorRepository.findByBookId(b.id).size()>0)
                a=authorRepository.findByBookId(b.id).get(0);
            PublisherBook pb = new PublisherBook();
            pb.img = "images/notFound.jpg";
            if(publisherBookRepository.findByBookId(b.id).size()>0)
                pb = publisherBookRepository.findByBookId(b.id).get(0);
            bookDTO.title=b.title;
            bookDTO.description=b.description;
            bookDTO.img=pb.img;
            bookDTO.authorName=a.name;
            bookDTO.authorLastName=a.lastName;
            bookDTOS.add(bookDTO);
        }
        return bookDTOS;
    }

    public void addNewBook(BookDTO bookDTO) throws SQLException {
        Book book = new Book();
        book.title = bookDTO.title;
        book.description = bookDTO.description;
        book.releaseDate = bookDTO.releaseDate;

        Author author = new Author();
        author.name = bookDTO.authorName;
        author.lastName = bookDTO.authorLastName;

        List<Author> authors = authorRepository.findByNameAndLastName(author.name,author.lastName);
        if(authors.size()>0)
            author = authors.get(0);
        else {
            authorRepository.save(author);
            author = authorRepository.findByNameAndLastName(author.name,author.lastName).get(0);
        }

        bookRepository.save(book);
        book = bookRepository.selectThatBeginWith(book.title,book.description).get(0);
        authorRepository.giveBookToAuthorById(book.id,author.id);

    }

    public void addPublisherBook(PublisherBook publisherBook) throws SQLException {
        publisherBook.publisherId=1;
        publisherBookRepository.save(publisherBook);
    }

    public void deleteBook(Book book) throws SQLException {
        for(PublisherBook publisherBook:publisherBookRepository.findByBookId(book.id)){
            for(BookCopy bookCopy:bookCopyRepository.findByPublisherBookId(publisherBook.id)) {
                for(Hire hire:hireRepository.selectByBookCopyId(bookCopy.id))
                    hireRepository.removeById(hire.id);
                for(Reservation reservation:reservationRepository.findWithValues(bookCopy))
                    reservationRepository.remove(reservation);
                bookCopyRepository.removeById(bookCopy.id);
            }
            publisherBookRepository.removeById(publisherBook.id);
        }
        for(Opinion opinion:opinionRepository.findByBookId(book.id))
            opinionRepository.remove(opinion);
        for(Author author:authorRepository.findByBookId(book.id))
            authorRepository.removeById(author.id);
        bookTypeRepository.removeByBookId(book.id);
        bookRepository.removeById(book.id);
    }

}
