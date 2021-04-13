package main.java.ui;

import main.java.controller.Controller;
import main.java.exception.AuthException;
import main.java.model.User;

import javax.swing.*;
import java.sql.SQLException;

public class LoginView {

    JFrame frame;
    JPanel panel;

    JLabel userLabel;
    JTextField userText;
    JLabel passwordLabel;
    JPasswordField passwordText;
    JButton loginButton;
    JButton registerButton;

    Controller cc = Controller.istance;

    public LoginView() {
        createWindow();
        placeComponents();
        setListeners();
        frame.setVisible(true);
    }

    private void createWindow(){
        frame = new JFrame("Logowanie");
        frame.setSize(300, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel = new JPanel();
        frame.add(panel);
    }

    private void placeComponents(){
        panel.setLayout(null);

        userLabel = new JLabel("Login");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Hasło");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        loginButton = new JButton("zaloguj");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        registerButton = new JButton("zarejestruj");
        registerButton.setBounds(180, 80, 100, 25);
        panel.add(registerButton);

    }

    private void setListeners(){

        loginButton.addActionListener(e -> {
            try {
                User user = cc.login(userText.getText(),passwordText.getText());
                frame.dispose();
                new MainPageView(user);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(frame,"Niepoprawny login!");
            } catch (AuthException e2){
                JOptionPane.showMessageDialog(frame,e2.getMessage());
            }
        });
        registerButton.addActionListener(e -> {
            new RegisterView();
        });

    }

}
