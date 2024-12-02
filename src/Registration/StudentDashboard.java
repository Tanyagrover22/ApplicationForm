package Registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentDashboard extends JFrame implements ActionListener {
    JLabel lblUsername, lblFirstName, lblLastName, lblEmail, lblPhone;
    JTextField textUsername, textFirstName, textLastName, textEmail, textPhone;
    JButton updateButton, deleteButton;
    String username;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Students";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Tanya@750";

    public StudentDashboard(String username, String firstName, String lastName, String email, String phone) {
        super("Student Dashboard");

        this.username = username; // Store the logged-in username

        // Set background color
        getContentPane().setBackground(new Color(240, 248, 255));

        // Header Label
        JLabel header = new JLabel("Student Dashboard", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setOpaque(true);
        header.setBackground(new Color(135, 206, 235));
        header.setForeground(Color.WHITE);
        header.setBounds(0, 10, 600, 50);
        add(header);

        // Display user data (read from the database)
        lblUsername = new JLabel("Username: ");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        lblUsername.setBounds(50, 70, 150, 30);
        add(lblUsername);

        textUsername = new JTextField(username);
        textUsername.setEditable(false);
        textUsername.setBounds(200, 70, 300, 30);
        add(textUsername);

        lblFirstName = new JLabel("First Name: ");
        lblFirstName.setFont(new Font("Arial", Font.PLAIN, 18));
        lblFirstName.setBounds(50, 110, 150, 30);
        add(lblFirstName);

        textFirstName = new JTextField(firstName);
        textFirstName.setBounds(200, 110, 300, 30);
        add(textFirstName);

        lblLastName = new JLabel("Last Name: ");
        lblLastName.setFont(new Font("Arial", Font.PLAIN, 18));
        lblLastName.setBounds(50, 150, 150, 30);
        add(lblLastName);

        textLastName = new JTextField(lastName);
        textLastName.setBounds(200, 150, 300, 30);
        add(textLastName);

        lblEmail = new JLabel("Email: ");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEmail.setBounds(50, 190, 150, 30);
        add(lblEmail);

        textEmail = new JTextField(email);
        textEmail.setBounds(200, 190, 300, 30);
        add(textEmail);

        lblPhone = new JLabel("Phone: ");
        lblPhone.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPhone.setBounds(50, 230, 150, 30);
        add(lblPhone);

        textPhone = new JTextField(phone);
        textPhone.setBounds(200, 230, 300, 30);
        add(textPhone);

        // Buttons for Update and Delete
        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 18));
        updateButton.setBounds(100, 280, 120, 40);
        updateButton.setBackground(new Color(50, 205, 50));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteButton.setBounds(250, 280, 120, 40);
        deleteButton.setBackground(new Color(255, 69, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(this);
        add(deleteButton);

        // Frame settings
        setLayout(null);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "UPDATE SignUp SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE username = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, textFirstName.getText());
                pstmt.setString(2, textLastName.getText());
                pstmt.setString(3, textEmail.getText());
                pstmt.setString(4, textPhone.getText());
                pstmt.setString(5, textUsername.getText());

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "User details updated successfully!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == deleteButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete your account?", "Delete Account", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String sql = "DELETE FROM SignUp WHERE username = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, username);

                    int rowsDeleted = pstmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Account deleted successfully!");
                        System.exit(0); // Close application after deletion
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example: You would pass the user details when calling the dashboard
        new StudentDashboard("johndoe", "John", "Doe", "john@example.com", "1234567890");
    }
}
