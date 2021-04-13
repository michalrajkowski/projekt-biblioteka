package main.java.ui;

import main.java.controller.Controller;
import main.java.model.Book;
import main.java.model.PublisherBook;

import javax.swing.*;
import java.sql.SQLException;


public class PublisherBookView {

    JFrame frame;
    JPanel panel;

    JButton deleteBook;

    JLabel publisherBookLabel;

    JLabel isbnLabel;
    JTextField isbnText;

    JLabel pageLabel;
    JTextField pageText;

    JLabel imgLabel;
    JTextField imgText;

    JLabel publisherLabel;
    JTextField publisherText;

    Book book;

    JButton add;
    Controller cc = Controller.istance;

    public PublisherBookView(Book book) {
        this.book=book;
        createWindow();
        placeComponents();
        setListeners();
        frame.setVisible(true);
    }

    private void createWindow(){
        frame = new JFrame("Ksiazka");
        frame.setSize(350, 250);
        frame.setResizable(false);
        panel = new JPanel();
        frame.add(panel);
    }

    private void placeComponents(){
        panel.setLayout(null);

        deleteBook = new JButton("usun ksiazke");
        deleteBook.setBounds(10,5,120,25);
        panel.add(deleteBook);

        publisherBookLabel = new JLabel("Dodaj ksiazke wydawnictwa");
        publisherBookLabel.setBounds(10, 30, 200, 25);
        panel.add(publisherBookLabel);

        isbnLabel = new JLabel("Numer ISBN");
        isbnLabel.setBounds(10, 60, 80, 25);
        panel.add(isbnLabel);

        isbnText = new JTextField(81);
        isbnText.setBounds(120, 60, 160, 25);
        panel.add(isbnText);

        pageLabel = new JLabel("Liczba stron");
        pageLabel.setBounds(10, 90, 80, 25);
        panel.add(pageLabel);

        pageText = new JTextField(36);
        pageText.setBounds(120, 90, 160, 25);
        panel.add(pageText);

        imgLabel = new JLabel("Grafika książki");
        imgLabel.setBounds(10, 120, 90, 25);
        panel.add(imgLabel);

        imgText = new JTextField(81);
        imgText.setBounds(120, 120, 160, 25);
        panel.add(imgText);

        publisherLabel = new JLabel("Wydawnictwo");
        publisherLabel.setBounds(10, 150, 90, 25);
        panel.add(publisherLabel);

        publisherText = new JTextField(81);
        publisherText.setBounds(120, 150, 160, 25);
        panel.add(publisherText);

        add = new JButton("dodaj");
        add.setBounds(120, 180, 160, 25);
        panel.add(add);
    }

    private void setListeners(){
        add.addActionListener(e -> {

            PublisherBook pb = new PublisherBook();

            pb.bookId=book.id;
            pb.pages=Integer.parseInt(pageText.getText());
            pb.isbn = Long.parseLong(isbnText.getText());
            pb.img = "images/" + imgText.getText();

            try {
                cc.addPublisherBook(pb);
            } catch (SQLException throwables) {
            }

        });

        deleteBook.addActionListener(e -> {

            try {
                cc.removeBook(book);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

    }

}