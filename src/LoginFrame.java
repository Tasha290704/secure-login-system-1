import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(UserManager userManager) {
        this.userManager = userManager;
        setupUI();
    }

    private void setupUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username field
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        mainPanel.add(usernameField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        mainPanel.add(passwordField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            new RegisterFrame(userManager);
            dispose();
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userManager.loginUser(username, password)) {
            JOptionPane.showMessageDialog(this,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            showWelcomeFrame(username);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showWelcomeFrame(String username) {
        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(300, 200);
        welcomeFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        JLabel welcomeLabel=new JLabel("Welcome, " + username + "!");
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add registration number
        String regNumber = userManager.getRegNumber(username);
        JLabel regLabel = new JLabel("Your registration Number: " + regNumber);
        regLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(regLabel);

        welcomeFrame.add(panel);
        welcomeFrame.setVisible(true);
    }

    }

