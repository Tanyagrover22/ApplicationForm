package Registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Registration extends JFrame implements ActionListener {
    // Declare UI components
    JTextField textFirstName, textLastName, textAge, textCourse, textSkills, textAddress, textCity, textState;
    JRadioButton rMale, rFemale;
    JComboBox<String> comboLanguage;
    JButton submitButton, resetButton, displayButton;

    // JDBC Connection Details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Students";
    private static final String DB_USER = "root"; // Replace with your username
    private static final String DB_PASSWORD = "Tanya@750"; // Replace with your password

    public Registration() {
        super("Student Registration Form");

        // Set background color
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue

        // Header Label (centered)
        JLabel header = new JLabel("Student Registration Form", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setOpaque(true);
        header.setBackground(new Color(135, 206, 235)); // Sky blue background
        header.setForeground(Color.WHITE);
        header.setBounds(0, 10, 600, 50);
        add(header);

        // First Name
        JLabel labelFirstName = new JLabel("First Name:");
        labelFirstName.setFont(new Font("Arial", Font.PLAIN, 18));
        labelFirstName.setBounds(50, 70, 150, 30);
        add(labelFirstName);

        textFirstName = new JTextField();
        textFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
        textFirstName.setBounds(200, 70, 300, 30);
        add(textFirstName);

        // Last Name
        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setFont(new Font("Arial", Font.PLAIN, 18));
        labelLastName.setBounds(50, 110, 150, 30);
        add(labelLastName);

        textLastName = new JTextField();
        textLastName.setFont(new Font("Arial", Font.PLAIN, 16));
        textLastName.setBounds(200, 110, 300, 30);
        add(textLastName);

        // Age
        JLabel labelAge = new JLabel("Age:");
        labelAge.setFont(new Font("Arial", Font.PLAIN, 18));
        labelAge.setBounds(50, 150, 150, 30);
        add(labelAge);

        textAge = new JTextField();
        textAge.setFont(new Font("Arial", Font.PLAIN, 16));
        textAge.setBounds(200, 150, 300, 30);
        add(textAge);

        // Gender
        JLabel labelGender = new JLabel("Gender:");
        labelGender.setFont(new Font("Arial", Font.PLAIN, 18));
        labelGender.setBounds(50, 190, 150, 30);
        add(labelGender);

        rMale = new JRadioButton("Male");
        rMale.setFont(new Font("Arial", Font.PLAIN, 16));
        rMale.setBounds(200, 190, 100, 30);
        rMale.setBackground(new Color(240, 248, 255)); // Light blue background
        add(rMale);

        rFemale = new JRadioButton("Female");
        rFemale.setFont(new Font("Arial", Font.PLAIN, 16));
        rFemale.setBounds(320, 190, 100, 30);
        rFemale.setBackground(new Color(240, 248, 255)); // Light blue background
        add(rFemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rMale);
        genderGroup.add(rFemale);

        // Course
        JLabel labelCourse = new JLabel("Course:");
        labelCourse.setFont(new Font("Arial", Font.PLAIN, 18));
        labelCourse.setBounds(50, 230, 150, 30);
        add(labelCourse);

        textCourse = new JTextField();
        textCourse.setFont(new Font("Arial", Font.PLAIN, 16));
        textCourse.setBounds(200, 230, 300, 30);
        add(textCourse);

        // Language
        JLabel labelLanguage = new JLabel("Language:");
        labelLanguage.setFont(new Font("Arial", Font.PLAIN, 18));
        labelLanguage.setBounds(50, 270, 150, 30);
        add(labelLanguage);

        comboLanguage = new JComboBox<>(new String[]{"English", "Hindi", "Other"});
        comboLanguage.setFont(new Font("Arial", Font.PLAIN, 16));
        comboLanguage.setBounds(200, 270, 300, 30);
        add(comboLanguage);

        // Skills
        JLabel labelSkills = new JLabel("Skills:");
        labelSkills.setFont(new Font("Arial", Font.PLAIN, 18));
        labelSkills.setBounds(50, 310, 150, 30);
        add(labelSkills);

        textSkills = new JTextField();
        textSkills.setFont(new Font("Arial", Font.PLAIN, 16));
        textSkills.setBounds(200, 310, 300, 30);
        add(textSkills);

        // Address
        JLabel labelAddress = new JLabel("Address:");
        labelAddress.setFont(new Font("Arial", Font.PLAIN, 18));
        labelAddress.setBounds(50, 350, 150, 30);
        add(labelAddress);

        textAddress = new JTextField();
        textAddress.setFont(new Font("Arial", Font.PLAIN, 16));
        textAddress.setBounds(200, 350, 300, 30);
        add(textAddress);

        // City
        JLabel labelCity = new JLabel("City:");
        labelCity.setFont(new Font("Arial", Font.PLAIN, 18));
        labelCity.setBounds(50, 390, 150, 30);
        add(labelCity);

        textCity = new JTextField();
        textCity.setFont(new Font("Arial", Font.PLAIN, 16));
        textCity.setBounds(200, 390, 300, 30);
        add(textCity);

        // State
        JLabel labelState = new JLabel("State:");
        labelState.setFont(new Font("Arial", Font.PLAIN, 18));
        labelState.setBounds(50, 430, 150, 30);
        add(labelState);

        textState = new JTextField();
        textState.setFont(new Font("Arial", Font.PLAIN, 16));
        textState.setBounds(200, 430, 300, 30);
        add(textState);

        // Buttons
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        submitButton.setBounds(100, 480, 120, 40);
        submitButton.setBackground(new Color(50, 205, 50)); // Lime green
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        add(submitButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 18));
        resetButton.setBounds(250, 480, 120, 40);
        resetButton.setBackground(new Color(255, 69, 0)); // Red
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);
        add(resetButton);

        displayButton = new JButton("Display");
        displayButton.setFont(new Font("Arial", Font.BOLD, 18));
        displayButton.setBounds(400, 480, 120, 40);
        displayButton.setBackground(new Color(30, 144, 255)); // Dodger blue
        displayButton.setForeground(Color.WHITE);
        displayButton.addActionListener(this);
        add(displayButton);

        // Frame settings
        setLayout(null);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Get gender from radio buttons
                String gender = rMale.isSelected() ? "Male" : rFemale.isSelected() ? "Female" : "";

                // Insert data into the database
                String sql = "INSERT INTO Student (first_name, last_name, age, gender, course, language, skills, address, city, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, textFirstName.getText());
                pstmt.setString(2, textLastName.getText());
                pstmt.setInt(3, Integer.parseInt(textAge.getText()));
                pstmt.setString(4, gender);
                pstmt.setString(5, textCourse.getText());
                pstmt.setString(6, comboLanguage.getSelectedItem().toString());
                pstmt.setString(7, textSkills.getText());
                pstmt.setString(8, textAddress.getText());
                pstmt.setString(9, textCity.getText());
                pstmt.setString(10, textState.getText());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Submitted Successfully!");

                // Close the current window and open a new signup window
                this.setVisible(false);
                new Signup(); // Opens a new Registration window

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == resetButton) {
            // Reset form fields
            textFirstName.setText("");
            textLastName.setText("");
            textAge.setText("");
            rMale.setSelected(false);
            rFemale.setSelected(false);
            textCourse.setText("");
            comboLanguage.setSelectedIndex(0);
            textSkills.setText("");
            textAddress.setText("");
            textCity.setText("");
            textState.setText("");
        } else if (e.getSource() == displayButton) {
            // Display student data
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM Student";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();

                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    sb.append("ID: ").append(rs.getInt("id"))
                            .append(", Name: ").append(rs.getString("first_name")).append(" ").append(rs.getString("last_name"))
                            .append(", Age: ").append(rs.getInt("age"))
                            .append(", Gender: ").append(rs.getString("gender"))
                            .append(", Course: ").append(rs.getString("course"))
                            .append(", Language: ").append(rs.getString("language"))
                            .append(", Skills: ").append(rs.getString("skills"))
                            .append(", Address: ").append(rs.getString("address"))
                            .append(", City: ").append(rs.getString("city"))
                            .append(", State: ").append(rs.getString("state"))
                            .append("\n");
                }

                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Student Data", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Registration();
    }
}

