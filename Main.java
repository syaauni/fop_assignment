import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Fish Eat Fish Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes the window and terminates the entire application.
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for easy positioning

        // Load the background image
        ImageIcon image = new ImageIcon("wallpaper2.jpg"); // Replace with your image path
        Image backgroundImage = image.getImage();

        // Custom panel w`ith a background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout()); // Use BorderLayout for centering

        // Panel for buttons (centered vertically and horizontally)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Stack buttons horizontally
        buttonPanel.setOpaque(false); // Transparent panel to show the background

        // Buttons
        JButton loginButton = SizeButton("Login");
        JButton SignUpButton = SizeButton("Sign Up");
        JButton startGameButton = SizeButton("Start Game");
        JButton historyButton = SizeButton("View History");
        JButton exitButton = SizeButton("Exit");


        loginButton.addActionListener(e -> LoginSignUpForm.LoginForm());
        SignUpButton.addActionListener(e -> LoginSignUpForm.SignUpForm());
        exitButton.addActionListener(e -> System.exit(0));


        // Add buttons to the panel with spacing
        buttonPanel.add(Box.createHorizontalGlue()); // Flexible space on the left
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Add horizontal spacing
        buttonPanel.add(SignUpButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(startGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(historyButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createHorizontalGlue()); // Flexible space on the right

        // Add button panel to the center of the background panel
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the background panel to the frame
        frame.add(backgroundPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static JButton SizeButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50)); // Set button size
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Bigger text for larger button
        button.setBackground(new Color(173, 216, 230)); // Optional: Button color
        button.setForeground(Color.BLACK); // Optional: Text color
        return button;
    }
}
