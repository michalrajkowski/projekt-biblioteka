package main.java.ui;

import main.java.controller.Controller;
import main.java.model.Book;
import main.java.model.User;
import main.java.model.dto.BookDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.sql.SQLException;
import java.util.List;

public class MainPageView extends JFrame{
    private int width = 350;
    private int height = 1000;
    private JList<BookDTO> listBook;
    DefaultListModel<BookDTO> model;

    int old=0;
    boolean antyRepeat=false;

    JButton searchButton;
    JTextField searchText;

    JButton addNewBook;
    JScrollPane pane;

    JPanel panel1;
    JPanel panel2;

    private User user;

    Controller cc = Controller.istance;

    public MainPageView(User user) {
        this.user=user;
        JSplitPane splitPane = new JSplitPane();
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);
        splitPane.setDividerLocation(200);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(createControlPanel());
        splitPane.setBottomComponent(createMainPanel());
        setListeners();
        setTitle("Strona glowna");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createMainPanel() {
        panel1 = new JPanel(new BorderLayout());
        panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        pane = new JScrollPane(listBook = createListBooks());
        panel1.add(pane,
                BorderLayout.CENTER);
        return panel1;
    }

    private JList<BookDTO> createListBooks() {
        model = new DefaultListModel<>();

        JList<BookDTO> list = new JList<BookDTO>(model);
        list.setCellRenderer(new BookView());
        return list;
    }

    private JPanel createControlPanel(){

        panel2 = new JPanel();
        panel2.setLayout(null);
        searchButton = new JButton("Szukaj");
        searchButton.setBounds(10, 10, 80, 25);
        panel2.add(searchButton);

        searchText = new JTextField(20);
        searchText.setBounds(100, 10, 160, 25);
        panel2.add(searchText);

        if(user.roleId==1)
            createEmployeeComponents(panel2);

        return panel2;
    }

    private void createEmployeeComponents(JPanel panel) {
        addNewBook = new JButton("Dodaj nowa ksiazke");
        addNewBook.setBounds(10, 40, 200, 25);
        panel2.add(addNewBook);

    }

    private void setListeners(){

        searchButton.addActionListener(e -> {
            try {
                model.clear();
                String title = searchText.getText();
                List<BookDTO> bookDTOList = cc.findBooks(searchText.getText());
                for(BookDTO b:bookDTOList){
                    model.addElement(b);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        if(user.roleId == 1) {
            addNewBook.addActionListener(e -> {

                new AddBookView();

            });
        }

        listBook.addListSelectionListener(e -> {

            if(!antyRepeat) {
                if (old == e.getFirstIndex()) {
                    old = e.getLastIndex();
                    if(model.size() > old) {
                        BookDTO bookDTO = model.getElementAt(old);
                        try {
                            Book book = cc.getByDTO(bookDTO);
                            if (user.roleId == 2)
                                new OpinionView(book, user);
                            else
                                new PublisherBookView(book);
                        } catch (SQLException throwables) {
                        }
                    }
                    antyRepeat = true;
                } else {
                    old = e.getFirstIndex();
                    if(model.size() > old) {
                        BookDTO bookDTO = model.getElementAt(old);
                        try {
                            Book book = cc.getByDTO(bookDTO);
                            if (user.roleId == 2)
                                new OpinionView(book, user);
                            else
                                new PublisherBookView(book);
                        } catch (SQLException throwables) {
                        }
                    }
                    antyRepeat = true;
                }
            }else
                antyRepeat = false;

        });

    }

}