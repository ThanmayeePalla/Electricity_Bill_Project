// LoginPage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> validateLogin());

        setLayout(new GridLayout(3, 2));
        add(userLabel); add(usernameField);
        add(passLabel); add(passwordField);
        add(new JLabel()); add(loginButton);

        setVisible(true);
    }

    private void validateLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT user FROM login WHERE username = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String role = rs.getString("user");
                JOptionPane.showMessageDialog(this, "Login successful as " + role);
                if (role.equalsIgnoreCase("Admin")) {
                    new AdminDashboard();
                } else {
                    new Customer();
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error");
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}