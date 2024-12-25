import javax.swing.*;
import java.awt.*;

public class LoginSignUpForm {
    public static void LoginForm() {
        JFrame frame = new JFrame("Login Form");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Custom JPanel for background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon icon = new ImageIcon("background.jpg"); // Replace with your image path
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField();
        username.setMaximumSize(new Dimension(300, 30));

        JPasswordField password = new JPasswordField();
        password.setMaximumSize(new Dimension(300, 30));

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backgroundPanel.add(title);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(new JLabel("Username:"));
        backgroundPanel.add(username);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(new JLabel("Password:"));
        backgroundPanel.add(password);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(loginButton);

        frame.setContentPane(backgroundPanel); // Set background panel as the content pane
        frame.setVisible(true);
    }

    public static void SignUpForm() {
        JFrame frame = new JFrame("Sign Up Form");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Custom JPanel for background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon icon = new ImageIcon("background.jpg"); // Replace with your image path
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sign Up");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField();
        username.setMaximumSize(new Dimension(300, 30));

        JPasswordField password = new JPasswordField();
        password.setMaximumSize(new Dimension(300, 30));

        JPasswordField confirmPassword = new JPasswordField();
        confirmPassword.setMaximumSize(new Dimension(300, 30));

        JButton signupButton = new JButton("Sign Up");
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backgroundPanel.add(title);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(new JLabel("Username:"));
        backgroundPanel.add(username);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(new JLabel("Password:"));
        backgroundPanel.add(password);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(new JLabel("Confirm Password:"));
        backgroundPanel.add(confirmPassword);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(signupButton);

        frame.setContentPane(backgroundPanel); // Set background panel as the content pane
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Display the login form
        LoginForm();
        // Display the sign-up form
        // SignUpForm(); // Uncomment to test Sign Up form
    }
}
