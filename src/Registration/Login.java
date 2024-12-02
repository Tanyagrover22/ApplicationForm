package Registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField textUsername;
    JPasswordField textPassword;
    JButton loginButton, resetButton;
    JLabel messageLabel;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Students";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Tanya@750";

    public Login() {
        super("Login Form");

        // Set background color
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel header = new JLabel("Login Form", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setOpaque(true);
        header.setBackground(new Color(135, 206, 235));
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

        // Message label for showing login status
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setBounds(200, 150, 300, 30);
        messageLabel.setForeground(Color.RED);
        add(messageLabel);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBounds(100, 200, 120, 40);
        loginButton.setBackground(new Color(50, 205, 50)); // Lime green
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        add(loginButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 18));
        resetButton.setBounds(250, 200, 120, 40);
        resetButton.setBackground(new Color(255, 69, 0)); // Red
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);
        add(resetButton);

        // Frame settings
        setLayout(null);
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (textUsername.getText().isEmpty() || textPassword.getPassword().length == 0) {
                messageLabel.setText("Username or Password cannot be empty!");
                return;
            }

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM SignUp WHERE username = ? AND password = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, textUsername.getText());
                pstmt.setString(2, new String(textPassword.getPassword()));
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Welcome to Student Dashboard!");
                    this.setVisible(false);  // Hide login window
                    new StudentDashboard(rs.getString("username"), rs.getString("first_name"),
                            rs.getString("last_name"), rs.getString("email"),
                            rs.getString("phone")); // Pass user data to the dashboard
                } else {
                    messageLabel.setText("Invalid username or password.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == resetButton) {
            textUsername.setText("");
            textPassword.setText("");
            messageLabel.setText("");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
