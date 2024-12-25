import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LoginSignUpForm {
    private static HashMap<String, String> users = new HashMap<>(); // Store usernames and hashed passwords

    public static void showMainMenu() {
        JFrame frame = new JFrame("Fish Eat Fish Game");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/background.jpg"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        loginButton.addActionListener(e -> {
            frame.dispose();
            showLoginForm(() -> SwingUtilities.invokeLater(LoginSignUpForm::showMainMenu));
        });

        signUpButton.addActionListener(e -> {
            frame.dispose();
            showSignUpForm(() -> SwingUtilities.invokeLater(LoginSignUpForm::showMainMenu));
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        backgroundPanel.add(buttonPanel);
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    public static void showLoginForm(Runnable onBackToMainMenu) {
        JFrame frame = new JFrame("Login Form");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/background.jpg"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Times New Roman", Font.BOLD, 40)); // Larger font
        title.setForeground(Color.WHITE); // White text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setForeground(Color.WHITE); // White text
        contentPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE); // White text
        contentPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(username) && users.get(username).equals(hashPassword(password))) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose();
                // Logic for the next step after login can be added here
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password!");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            onBackToMainMenu.run();
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        contentPanel.add(buttonPanel, gbc);

        backgroundPanel.add(contentPanel);
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    public static void showSignUpForm(Runnable onBackToMainMenu) {
        JFrame frame = new JFrame("Sign Up Form");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/background.jpg"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Sign Up");
        title.setFont(new Font("Times New Roman", Font.BOLD, 40)); // Larger font
        title.setForeground(Color.WHITE); // White text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setForeground(Color.WHITE); // White text
        contentPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE); // White text
        contentPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        confirmPasswordLabel.setForeground(Color.WHITE); // White text
        contentPanel.add(confirmPasswordLabel, gbc);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        signUpButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!");
            } else if (users.containsKey(username)) {
                JOptionPane.showMessageDialog(frame, "Username already exists!");
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match!");
            } else {
                users.put(username, hashPassword(password));
                JOptionPane.showMessageDialog(frame, "Sign Up successful!");
                frame.dispose();
                onBackToMainMenu.run();
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            onBackToMainMenu.run();
        });

        buttonPanel.add(signUpButton);
        buttonPanel.add(backButton);
        contentPanel.add(buttonPanel, gbc);

        backgroundPanel.add(contentPanel);
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    private static String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginSignUpForm::showMainMenu);
    }
}
