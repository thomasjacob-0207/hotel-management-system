package hotelapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewBookings extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewBookings() {
        setTitle("All Bookings");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "Booking ID", "Guest Name", "Phone",
            "Room No", "Check-in", "Amount", "Status"
        });

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton deleteBtn = new JButton("Delete Selected Booking");
        add(deleteBtn, BorderLayout.SOUTH);

        deleteBtn.addActionListener(e -> deleteBooking());

        loadBookings();
        setVisible(true);
    }

    private void loadBookings() {
        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed.");
                return;
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bookings");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("booking_id"),
                    rs.getString("guest_name"),
                    rs.getString("phone"),
                    rs.getInt("room_no"),
                    rs.getDate("check_in_date"),
                    rs.getDouble("total_amount"),
                    rs.getString("status")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading bookings.");
        }
    }

    private void deleteBooking() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking first.");
            return;
        }

        int bookingId = (int) model.getValueAt(selectedRow, 0);
        int roomNo = (int) model.getValueAt(selectedRow, 3);

        int choice = JOptionPane.showConfirmDialog(
            this,
            "Delete booking ID " + bookingId + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String deleteSql = "DELETE FROM bookings WHERE booking_id = ?";
        String updateRoomSql =
            "UPDATE rooms SET status = 'Available' WHERE room_no = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed.");
                return;
            }

            PreparedStatement deletePs = con.prepareStatement(deleteSql);
            deletePs.setInt(1, bookingId);
            deletePs.executeUpdate();

            PreparedStatement updatePs = con.prepareStatement(updateRoomSql);
            updatePs.setInt(1, roomNo);
            updatePs.executeUpdate();

            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Booking deleted successfully.");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting booking.");
        }
    }
}