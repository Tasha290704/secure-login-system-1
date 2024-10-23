import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JTextField regNumberField;

    public RegisterFrame(UserManager userManager) {
        this.userManager = userManager;
        setupUI();
    }

    private void setupUI() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        mainPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        mainPanel.add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(15);
        mainPanel.add(confirmPasswordField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        mainPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Reg Number:"), gbc);

        gbc.gridx = 1;
        regNumberField = new JTextField(15);
        mainPanel.add(regNumberField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> register());

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> {
            new LoginFrame(userManager);
            dispose();
        });

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
        setVisible(true);
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String email = emailField.getText();
        String regNumber = regNumberField.getText();

        // Validation
        if (username.isEmpty() || password.isEmpty() ||
                confirmPassword.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid email address",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (regNumber.length() != 8) {  // Basic registration number validation
            JOptionPane.showMessageDialog(this,
                    "Registration number must be at least 8 characters",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (userManager.registerUser(username, password, email, regNumber)) {
            JOptionPane.showMessageDialog(this,
                    "Registration successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            new LoginFrame(userManager);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Username already exists",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (userManager.registerUser(username, password, email, regNumber)) {
            JOptionPane.showMessageDialog(this,
                    "Registration successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            new LoginFrame(userManager);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Username already exists or email already in use contact the Administrator",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

