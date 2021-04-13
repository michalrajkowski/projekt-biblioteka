package main.java.ui;

import main.java.controller.Controller;
import main.java.exception.AuthException;
import main.java.factory.LocalStatementBuilder;
import main.java.model.Book;
import main.java.model.Opinion;
import main.java.model.User;
import main.java.repository.OpinionRepository;
import main.java.repository.UserRepository;
import main.java.service.OpinionService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OpinionView {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final OpinionRepository opinionRepository =
            new OpinionRepository(statement);

    final OpinionService opinionService =
            new OpinionService(opinionRepository);

    final UserRepository userRep = new UserRepository(statement);

    Book book;
    User user;

    JFrame frame;
   // JPanel panel;
    JPanel test_panel;


    JLabel userLabel;
    JLabel rateLabel;
    JTextArea opinionText;

    JTextArea userText;

    JButton addOpinionButton;

    SpinnerModel model;
    JSpinner spinner;

    Border border;
    Border border_2;

    Controller cc = Controller.istance;

    List<Opinion> opinie;

    public OpinionView(Book _book, User _user) {
        this.book = _book;
        this.user = _user;

        try {
            opinie = opinionService.browseOpinions(book);
        }catch(Exception e){opinie = new ArrayList<Opinion>();
        };


        System.out.print(opinie.size());//todo usun to!!!!


        createWindow();
       // placeComponents();
        setListeners();
        frame.setVisible(true);
    }

    private void createWindow(){
        frame = new JFrame("Opinie");
        frame.setSize(1000, 1000);
       // frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        border = BorderFactory.createRaisedBevelBorder();
        border_2 = BorderFactory.createLineBorder(Color.black);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAutoscrolls(true);
        frame.add(panel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(50, 30, 800, 800);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(800, 800));
        contentPane.add(scrollPane);

        //todo tutaj jest tworzone wszystko
        for(int i = -1; i < opinie.size(); i++) {

            JPanel main_panel = new JPanel();
            main_panel.setLayout(new FlowLayout());
            main_panel.setPreferredSize(new Dimension(800, 180));


            JPanel opinion_rec_panel = new JPanel();
            opinion_rec_panel.setLayout(new FlowLayout());
            opinion_rec_panel.setBackground(Color.white);
            opinion_rec_panel.setPreferredSize(new Dimension(600, 170));
            opinion_rec_panel.setBorder(border);

            if(i == -1){
                userLabel = new JLabel(user.name);
                userLabel.setBounds(10, 10, 160, 25);
                userLabel.setPreferredSize(new Dimension(160, 25));
                opinion_rec_panel.add(userLabel);

                rateLabel = new JLabel("Ocena: ");
                rateLabel.setBounds(170, 10, 60, 25);
                rateLabel.setPreferredSize(new Dimension(60, 25));
                opinion_rec_panel.add(rateLabel);

                model = new SpinnerNumberModel(5, 0, 10, 1.0);
                spinner = new JSpinner(model);
                opinion_rec_panel.add(spinner);

                addOpinionButton = new JButton("Dodaj");
                addOpinionButton.setBounds(480, 10, 100, 25);
                addOpinionButton.setPreferredSize(new Dimension(100, 25));
                opinion_rec_panel.add(addOpinionButton);

                userText = new JTextArea();
                userText.setBounds(10, 40, 580, 125);
                userText.setPreferredSize(new Dimension(580, 125));
                userText.setEditable(true);
                userText.setBorder(border_2);
                userText.setLineWrap(true);
                opinion_rec_panel.add(userText);

            }else{
                Opinion opinia = opinie.get(i);

                User opinion_user;
                try {
                    opinion_user = userRep.findById(opinia.userId);
                }catch(Exception e){
                    opinion_user = new User();
                    opinion_user.name = "Błąd związany z wczytaniem nicku";
                };

                userLabel = new JLabel(opinion_user.name);
                userLabel.setBounds(10, 10, 160, 25);
                userLabel.setPreferredSize(new Dimension(160, 25));
                opinion_rec_panel.add(userLabel);

                rateLabel = new JLabel("Ocena: " + opinia.rate);
                rateLabel.setBounds(170, 10, 80, 25);
                rateLabel.setPreferredSize(new Dimension(80, 25));
                opinion_rec_panel.add(rateLabel);

                opinionText = new JTextArea();
                opinionText.setBounds(10, 40, 580, 125);
                opinionText.setPreferredSize(new Dimension(580, 125));
                opinionText.setEditable(false);
                opinionText.setText(opinia.text);
                opinionText.setBorder(border_2);
                opinionText.setLineWrap(true);
                opinion_rec_panel.add(opinionText);

            }

            main_panel.add(opinion_rec_panel);
            panel.add(main_panel);

        }

        frame.add(contentPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void setListeners(){

        addOpinionButton.addActionListener(e -> {

            try {
                double d_value = (Double) model.getValue();
                int value = (int) d_value;
                String new_text = userText.getText();

                opinionService.createOpinionPlus(value, new_text, user, book);
                frame.dispose();
                new OpinionView(book, user);

            } catch (Exception e1) {

            }

        });

    }

}
