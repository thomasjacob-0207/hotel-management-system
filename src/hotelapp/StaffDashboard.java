package hotelapp;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    JTextField txtUsername = new JTextField();
    JPasswordField txtPassword = new JPasswordField();

    public LoginPage() {
        setTitle("Hotel Management System - Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        add(txtUsername);

        add(new JLabel("Password:"));
        add(txtPassword);

        JButton loginBtn = new JButton("Login");
        add(new JLabel());
        add(loginBtn);

        loginBtn.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        String username = txtUsername.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Login successful as Admin!");
            new AdminDashboard();
            dispose();

        } else if (username.equals("staff") && password.equals("staff123")) {
            JOptionPane.showMessageDialog(this, "Login successful as Staff!");
            new StaffDashboard();
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}