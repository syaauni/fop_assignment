import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;

public class FishGameProject {
    public static void main(String[] args) {
        // Start background music in a separate thread
        new Thread(() -> playBackgroundMusic("/Users/mannibh/Downloads/fop_assignment/lagu.wav")).start();

        // Prompt player for their name
        final String playerName;
        String inputName = JOptionPane.showInputDialog("Enter your name:");
        if (inputName == null || inputName.trim().isEmpty()) {
            playerName = "Unknown Player";
        } else {
            playerName = inputName;
        }

        // Character Selection
        CharacterSelection characterSelection = new CharacterSelection();
        characterSelection.addSelectionListener(selectedFish -> {
            System.out.println("Selected Fish: " + selectedFish);

            // Start the game
            SwingUtilities.invokeLater(() -> {
                System.out.println("Starting game...");

                // Set up the game JFrame with a background image
                JFrame frame = new JFrame("Fish Eat Fish Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 600);
                frame.setResizable(false);

                // Add background panel
                JPanel backgroundPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        // Load and draw the background image
                        ImageIcon backgroundIcon = new ImageIcon("/Users/mannibh/Downloads/fop_assignmen/background.jpg");
                        g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                    }
                };
                backgroundPanel.setLayout(new BorderLayout());

                // Add game panel (Seafood) to the background
                ScoringSystem scoringSystem = new ScoringSystem(); // Initialize scoring system
                Seafood game = new Seafood(selectedFish, playerName, scoringSystem); // Pass fish, player name, and scoring system
                backgroundPanel.add(game, BorderLayout.CENTER);

                frame.add(backgroundPanel);
                frame.setVisible(true);

                System.out.println("Game initialized successfully!");
            });
        });

        characterSelection.showCharacterSelection();
    }

    public static void playBackgroundMusic(String filePath) {
        try {
            File soundFile = new File(filePath);
            System.out.println("Looking for sound file at: " + soundFile.getAbsolutePath());

            if (!soundFile.exists()) {
                System.out.println("Error: File not found at " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            System.out.println("Playing background music...");
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }
}
