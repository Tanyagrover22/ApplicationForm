package Registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.*;

public class Signup extends JFrame implements ActionListener {
    // Declare UI components
    JTextField textUsername, textFirstName, textLastName, textEmail, textPhone;
    JPasswordField textPassword;
    JButton signupButton, resetButton;

    // JDBC Connection Details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Students";
    private static final String DB_USER = "root"; // Replace with your username
    private static final String DB_PASSWORD = "Tanya@750"; // Replace with your password

    public Signup() {
        super("Signup Form");

        // Set background color
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue

        // Header Label (centered)
        JLabel header = new JLabel("Signup Form", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setOpaque(true);
        header.setBackground(new Color(135, 206, 235)); // Sky blue background
        header.setForeground(Color.WHITE);
        header.setBounds(0, 10, 600, 50);
        add(header);

        // Username
        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        labelUsername.setBounds(50, 70, 150, 30);
        add(labelUsername);

        textUsername = new JTextField();
        textUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        textUsername.setBounds(200, 70, 300, 30);
        add(textUsername);

        // Password
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        labelPassword.setBounds(50, 110, 150, 30);
        add(labelPassword);

        textPassword = new JPasswordField();
        textPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        textPassword.setBounds(200, 110, 300, 30);
        add(textPassword);

        // First Name
        JLabel labelFirstName = new JLabel("First Name:");
        labelFirstName.setFont(new Font("Arial", Font.PLAIN, 18));
        labelFirstName.setBounds(50, 150, 150, 30);
        add(labelFirstName);

        textFirstName = new JTextField();
        textFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
        textFirstName.setBounds(200, 150, 300, 30);
        add(textFirstName);

        // Last Name
        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setFont(new Font("Arial", Font.PLAIN, 18));
        labelLastName.setBounds(50, 190, 150, 30);
        add(labelLastName);

        textLastName = new JTextField();
        textLastName.setFont(new Font("Arial", Font.PLAIN, 16));
        textLastName.setBounds(200, 190, 300, 30);
        add(textLastName);

        // Email
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        labelEmail.setBounds(50, 230, 150, 30);
        add(labelEmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        textEmail.setBounds(200, 230, 300, 30);
        add(textEmail);

        // Phone Number
        JLabel labelPhone = new JLabel("Phone:");
        labelPhone.setFont(new Font("Arial", Font.PLAIN, 18));
        labelPhone.setBounds(50, 270, 150, 30);
        add(labelPhone);

        textPhone = new JTextField();
        textPhone.setFont(new Font("Arial", Font.PLAIN, 16));
        textPhone.setBounds(200, 270, 300, 30);
        add(textPhone);

        // Buttons
        signupButton = new JButton("Signup");
        signupButton.setFont(new Font("Arial", Font.BOLD, 18));
        signupButton.setBounds(100, 320, 120, 40);
        signupButton.setBackground(new Color(50, 205, 50)); // Lime green
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener(this);
        add(signupButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 18));
        resetButton.setBounds(250, 320, 120, 40);
        resetButton.setBackground(new Color(255, 69, 0)); // Red
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);
        add(resetButton);

        // Frame settings
        setLayout(null);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            // Validate input fields
            if (textUsername.getText().isEmpty() || textPassword.getPassword().length == 0 ||
                    textFirstName.getText().isEmpty() || textLastName.getText().isEmpty() ||
                    textEmail.getText().isEmpty() || textPhone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Validate email format using regex
            String email = textEmail.getText();
            String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (!email.matches(emailPattern)) {
                JOptionPane.showMessageDialog(this, "Invalid email format!");
                return;
            }

            // Validate phone number format (simple numeric check for 10 digits)
            String phone = textPhone.getText();
            if (phone.length() != 10 || !phone.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this, "Invalid phone number!");
                return;
            }

            // If all validations pass, proceed to insert data into the database
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Insert data into the database
                String sql = "INSERT INTO SignUp (username, password, first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, textUsername.getText());
                pstmt.setString(2, new String(textPassword.getPassword())); // Get password as string
                pstmt.setString(3, textFirstName.getText());
                pstmt.setString(4, textLastName.getText());
                pstmt.setString(5, textEmail.getText());
                pstmt.setString(6, textPhone.getText());
                pstmt.executeUpdate();

                // Display entered data
                JOptionPane.showMessageDialog(this, "Signup Successful!\n" +
                        "Username: " + textUsername.getText() + "\n" +
                        "First Name: " + textFirstName.getText() + "\n" +
                        "Last Name: " + textLastName.getText() + "\n" +
                        "Email: " + textEmail.getText() + "\n" +
                        "Phone: " + textPhone.getText());

                // Optionally, close this window and open the login page
                this.setVisible(false);
                new Login();  // Opens the Login form

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == resetButton) {
            // Reset form fields
            textUsername.setText("");
            textPassword.setText("");
            textFirstName.setText("");
            textLastName.setText("");
            textEmail.setText("");
            textPhone.setText("");
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
