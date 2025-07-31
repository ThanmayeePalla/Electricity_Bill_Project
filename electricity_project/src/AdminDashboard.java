// AdminDashboard.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminDashboard extends JFrame {
    JTextField meterField, unitsField, monthField;

    public AdminDashboard() {
        setTitle("Admin Dashboard - Generate Bill");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel meterLabel = new JLabel("Meter No:");
        JLabel unitsLabel = new JLabel("Units Consumed:");
        JLabel monthLabel = new JLabel("Month:");

        meterField = new JTextField(15);
        unitsField = new JTextField(15);
        monthField = new JTextField(15);
        JButton generateButton = new JButton("Generate Bill");

        generateButton.addActionListener(e -> generateBill());

        setLayout(new GridLayout(4, 2));
        add(meterLabel); add(meterField);
        add(unitsLabel); add(unitsField);
        add(monthLabel); add(monthField);
        add(new JLabel()); add(generateButton);

        setVisible(true);
    }

    private void generateBill() {
        String meter = meterField.getText();
        int units = Integer.parseInt(unitsField.getText());
        String month = monthField.getText();

        double total = Bill.calculateBill(units);

        try (Connection con = DBConnection.getConnection()) {
            String insertQuery = "INSERT INTO bill (meter, month, units, total_bill, status) VALUES (?, ?, ?, ?, 'Not Paid')";
            PreparedStatement pst = con.prepareStatement(insertQuery);
            pst.setString(1, meter);
            pst.setString(2, month);
            pst.setInt(3, units);
            pst.setDouble(4, total);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Bill generated: Rs. " + total);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating bill");
        }
    }
}