package main.java.ui;

import main.java.controller.Controller;
import main.java.exception.AuthException;
import main.java.model.User;
import main.java.model.dto.UserDTO;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RegisterView {

    JFrame frame;
    JPanel panel;

    JLabel nameLabel;
    JTextField nameText;

    JLabel lastNameLabel;
    JTextField lastNameText;

    JFormattedTextField txtDate;
    JLabel dateLabel;

    JLabel nickLabel;
    JTextField nickText;

    JLabel loginLabel;
    JTextField loginText;

    JLabel passwordLabel;
    JTextField passwordText;

    JLabel emailLabel;
    JTextField emailText;

    JLabel phoneLabel;
    JTextField phoneText;

    JLabel townLabel;
    JTextField townText;

    JLabel streetLabel;
    JTextField streetText;

    JLabel numberLabel;
    JTextField numberText;

    JLabel houseNumberLabel;
    JTextField houseNumberText;

    JButton register;

    Controller cc = Controller.istance;

    public RegisterView() {
        createWindow();
        placeComponents();
        setListeners();
        frame.setVisible(true);
    }

    private void createWindow(){
        frame = new JFrame("Rejestracja");
        frame.setSize(300, 440);
        frame.setResizable(false);

        panel = new JPanel();
        frame.add(panel);
    }

    private void placeComponents(){
        panel.setLayout(null);

        nameLabel = new JLabel("imie");
        nameLabel.setBounds(10, 10, 80, 25);
        panel.add(nameLabel);

        nameText = new JTextField(81);
        nameText.setBounds(100, 10, 160, 25);
        panel.add(nameText);

        lastNameLabel = new JLabel("nazwisko");
        lastNameLabel.setBounds(10, 40, 80, 25);
        panel.add(lastNameLabel);

        lastNameText = new JTextField(36);
        lastNameText.setBounds(100, 40, 160, 25);
        panel.add(lastNameText);

        // przy dacie tylko inaczej
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter dateFormatter  = new DateFormatter(df);
        txtDate = new JFormattedTextField(df);
        txtDate.setBounds(100,70,160,25);
        txtDate .addKeyListener(new KeyAdapter() {
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
        panel.add(txtDate);
        // reszta juz normalnie

        dateLabel = new JLabel("data urodz.");
        dateLabel.setBounds(10, 70, 80, 25);
        panel.add(dateLabel);

        nickLabel = new JLabel("nick");
        nickLabel.setBounds(10, 100, 80, 25);
        panel.add(nickLabel);

        nickText = new JTextField(81);
        nickText.setBounds(100, 100, 160, 25);
        panel.add(nickText);

        loginLabel = new JLabel("login");
        loginLabel.setBounds(10, 130, 80, 25);
        panel.add(loginLabel);

        loginText = new JTextField(81);
        loginText.setBounds(100, 130, 160, 25);
        panel.add(loginText);

        passwordLabel = new JLabel("haslo");
        passwordLabel.setBounds(10, 160, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(81);
        passwordText.setBounds(100, 160, 160, 25);
        panel.add(passwordText);

        emailLabel = new JLabel("email");
        emailLabel.setBounds(10, 190, 80, 25);
        panel.add(emailLabel);

        emailText = new JTextField(81);
        emailText.setBounds(100, 190, 160, 25);
        panel.add(emailText);

        phoneLabel = new JLabel("telefon");
        phoneLabel.setBounds(10, 220, 80, 25);
        panel.add(phoneLabel);

        phoneText = new JTextField(81);
        phoneText.setBounds(100, 220, 160, 25);
        panel.add(phoneText);

        townLabel = new JLabel("miasto");
        townLabel.setBounds(10, 250, 80, 25);
        panel.add(townLabel);

        townText = new JTextField(81);
        townText.setBounds(100, 250, 160, 25);
        panel.add(townText);

        streetLabel = new JLabel("ulica");
        streetLabel.setBounds(10, 280, 80, 25);
        panel.add(streetLabel);

        streetText = new JTextField(81);
        streetText.setBounds(100, 280, 160, 25);
        panel.add(streetText);

        numberLabel = new JLabel("numer domu");
        numberLabel.setBounds(10, 310, 80, 25);
        panel.add(numberLabel);

        numberText = new JTextField(81);
        numberText.setBounds(100, 310, 160, 25);
        panel.add(numberText);

        houseNumberLabel = new JLabel("numer miesz.");
        houseNumberLabel.setBounds(10, 340, 80, 25);
        panel.add(houseNumberLabel);

        houseNumberText = new JTextField(81);
        houseNumberText.setBounds(100, 340, 160, 25);
        panel.add(houseNumberText);

        register = new JButton("Zarejestruj");
        register.setBounds(80, 370, 120, 25);
        panel.add(register);


    }

    private void setListeners(){
        register.addActionListener(e -> {
            UserDTO userDTO = new UserDTO();
            userDTO.name = nameText.getText();
            userDTO.last_name = lastNameText.getText();
            userDTO.birthDate = txtDate.getText();
            userDTO.nick = nickText.getText();
            userDTO.login = loginText.getText();
            userDTO.password = passwordText.getText();
            userDTO.town = townText.getText();
            userDTO.street = streetText.getText();
            userDTO.email = emailText.getText();
            userDTO.number = Integer.parseInt(numberText.getText());
            userDTO.houseNumber = houseNumberText.getText();
            userDTO.phone = Long.parseLong(phoneText.getText());

            try {
                cc.addNewUser(userDTO);
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(frame,"niepoprawne dane!");
            }


        });
    }

}
