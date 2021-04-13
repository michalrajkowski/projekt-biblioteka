package main.java.ui;

import main.java.controller.Controller;
import main.java.model.dto.BookDTO;


import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddBookView {

    JFrame frame;
    JPanel panel;

    JLabel titleLabel;
    JTextField titleText;
    JLabel descriptionLabel;
    JTextField descriptionText;
    JLabel releaseDateLabel;
    JFormattedTextField releaseDateText;
    JButton addBookButton;
    JTextField authorNameText;
    JLabel authorNameLabel;
    JTextField authorLastNameText;
    JLabel authorLastNameLabel;

    Controller cc = Controller.istance;

    public AddBookView() {
        createWindow();
        placeComponents();
        setListeners();
        frame.setVisible(true);
    }

    private void createWindow(){
        frame = new JFrame("Dodaj nowa ksiazke");
        frame.setSize(300, 230);
        frame.setResizable(false);

        panel = new JPanel();
        frame.add(panel);
    }

    private void placeComponents(){
        panel.setLayout(null);

        titleLabel = new JLabel("Tytul");
        titleLabel.setBounds(10, 10, 80, 25);
        panel.add(titleLabel);

        titleText = new JTextField(20);
        titleText.setBounds(100, 10, 160, 25);
        panel.add(titleText);

        descriptionLabel = new JLabel("Opis");
        descriptionLabel.setBounds(10, 40, 80, 25);
        panel.add(descriptionLabel);

        descriptionText = new JTextField(20);
        descriptionText.setBounds(100, 40, 160, 25);
        panel.add(descriptionText);

        releaseDateLabel = new JLabel("Data wydaia");
        releaseDateLabel.setBounds(10, 70, 80, 25);
        panel.add(releaseDateLabel);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter dateFormatter  = new DateFormatter(df);
        releaseDateText = new JFormattedTextField(df);
        releaseDateText.setBounds(100,70,160,25);
        releaseDateText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_MINUS)))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid");
                    e.consume();
                }
            }
        });
        panel.add(releaseDateText);

        authorNameLabel = new JLabel("Imie autora");
        authorNameLabel.setBounds(10, 100, 80, 25);
        panel.add(authorNameLabel);

        authorNameText = new JTextField(20);
        authorNameText.setBounds(100, 100, 160, 25);
        panel.add(authorNameText);

        authorLastNameLabel = new JLabel("Nazwisko autora");
        authorLastNameLabel.setBounds(10, 130, 80, 25);
        panel.add(authorLastNameLabel);

        authorLastNameText = new JTextField(20);
        authorLastNameText.setBounds(100, 130, 160, 25);
        panel.add(authorLastNameText);

        addBookButton = new JButton("Dodaj");
        addBookButton.setBounds(10, 160, 80, 25);
        panel.add(addBookButton);

    }

    private void setListeners(){
        addBookButton.addActionListener(e -> {
            BookDTO book = new BookDTO();
            book.title = titleText.getText();
            book.description = descriptionText.getText();
            book.releaseDate = releaseDateText.getText();
            book.authorName = authorNameText.getText();
            book.authorLastName = authorLastNameText.getText();
            try {
                cc.addNewBook(book);
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(frame,"Wystapil blad przy zapisie ksiazki");
            }
        });
    }

}
