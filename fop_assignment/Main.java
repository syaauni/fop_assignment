import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu menu = new MainMenu();
            menu.showMenu();
        });
    }
}

class MainMenu {
    public void showMenu() {
        JFrame frame = new JFrame("Fish Eat Fish Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setLayout(new BorderLayout());

        // Load the background image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/wallpaper2.jpg")); // Ensure the image path is correct
        Image backgroundImage = imageIcon.getImage();

        // Custom panel with a background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image, scaled to fit the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Add a spacer to push the buttons lower
        JPanel spacerPanel = new JPanel();
        spacerPanel.setOpaque(false);
        spacerPanel.setPreferredSize(new Dimension(900, 300)); // Adjust to push buttons under the title

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Transparent to show the background

        // Buttons
        JButton loginButton = createStyledButton("Login");
        JButton signUpButton = createStyledButton("Sign Up");

        // Add buttons to panel with spacing
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        buttonPanel.add(signUpButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Add spacer and button panel to the background panel
        backgroundPanel.add(spacerPanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Button actions
        loginButton.addActionListener(e -> {
            frame.dispose();
            LoginSignUpForm.showLoginForm(() -> SwingUtilities.invokeLater(this::showMenu));
        });
        signUpButton.addActionListener(e -> {
            frame.dispose();
            LoginSignUpForm.showSignUpForm(() -> SwingUtilities.invokeLater(this::showMenu));
        });

        // Add the background panel to the frame
        frame.add(backgroundPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25); // Rounded edges
                super.paintComponent(g2);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25); // Rounded border
            }
        };

        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        button.setPreferredSize(new Dimension(250, 60)); // Bigger buttons
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Bigger text
        button.setBackground(new Color(173, 216, 230)); // Light blue background
        button.setForeground(Color.BLACK); // Black text
        button.setFocusPainted(false); // Remove focus painting
        button.setContentAreaFilled(false); // Transparent background to support rounded corners
        return button;
    }
}
