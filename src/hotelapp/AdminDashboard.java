package hotelapp;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton roomBtn = new JButton("Manage Rooms");
        JButton viewBtn = new JButton("View Bookings");
        JButton deleteBtn = new JButton("Delete Booking");
        JButton staffBtn = new JButton("Manage Staff");
        JButton logoutBtn = new JButton("Logout");

        roomBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Room management page goes here."));

        viewBtn.addActionListener(e -> new ViewBookings());
        deleteBtn.addActionListener(e -> new ViewBookings());

        staffBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Staff management page goes here."));

        logoutBtn.addActionListener(e -> {
            new LoginPage();
            dispose();
        });

        add(roomBtn);
        add(viewBtn);
        add(deleteBtn);
        add(staffBtn);
        add(logoutBtn);

        setVisible(true);
    }
}