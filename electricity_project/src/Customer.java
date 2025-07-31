import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Customer extends JFrame {
    JTextArea billArea;
    JTextField meterField;

    public Customer() {
        setTitle("Customer View - Bill Details");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel meterLabel = new JLabel("Enter Meter No:");
        meterField = new JTextField(15);
        JButton viewButton = new JButton("View Bill");
        billArea = new JTextArea();

        viewButton.addActionListener(e -> viewBill());

        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.add(meterLabel);
        inputPanel.add(meterField);
        inputPanel.add(viewButton);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(billArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void viewBill() {
        String meter = meterField.getText();
        billArea.setText("");

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM bill WHERE meter = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, meter);
            ResultSet rs = pst.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                String month = rs.getString("month");
                int units = rs.getInt("units");
                double total = rs.getDouble("total_bill");
                String status = rs.getString("status");
                billArea.append("Month: " + month + ", Units: " + units + ", Total: Rs. " + total + ", Status: " + status + "\n");
            }

            if (!found) {
                billArea.setText("No bills found for meter number: " + meter);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            billArea.setText("Error fetching bill details.");
        }
    }

    // ✅ Add the main method to make it runnable
    public static void main(String[] args) {
        new Customer();
    }
}
