import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;

public class CharacterSelection {
    private String selectedFish = "/Users/mannibh/Downloads/fop_assignment/fish1.png";

    private Runnable onGameStart;

    public void showCharacterSelection() {
        JFrame frame = new JFrame("Select Your Fish");
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel instructionLabel = new JLabel("Choose Your Fish:");
        instructionLabel.setBounds(250, 10, 200, 30);
        frame.add(instructionLabel);

        // Add a label to show the selected character
        JLabel selectedFishLabel = new JLabel();
        selectedFishLabel.setBounds(250, 200, 140, 140); // Space for selected fish preview
        frame.add(selectedFishLabel);

        // Create buttons with scaled fish images
        JButton fish1Button = createFishButton("/Users/mannibh/Downloads/fop_assignment/fish1.png", 50, 60, selectedFishLabel);
        JButton fish2Button = createFishButton("/Users/mannibh/Downloads/fop_assignment//fish2.png", 200, 60, selectedFishLabel);
        JButton fish3Button = createFishButton("/Users/mannibh/Downloads/fop_assignment/fish3.png", 350, 60, selectedFishLabel);
        JButton fish4Button = createFishButton("/Users/mannibh/Downloads/fop_assignment/fish4.png", 500, 60, selectedFishLabel);

        frame.add(fish1Button);
        frame.add(fish2Button);
        frame.add(fish3Button);
        frame.add(fish4Button);

        // Add a "Start Game" button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.setBounds(250, 350, 120, 30);
        startGameButton.setEnabled(true);
        startGameButton.addActionListener(e -> {
            System.out.println("Selected Fish: " + selectedFish);
            frame.dispose(); // Close the selection screen
            if (onGameStart != null) {
                onGameStart.run();
            }
        });
        frame.add(startGameButton);

        frame.setVisible(true);
    }

    private JButton createFishButton(String path, int x, int y, JLabel previewLabel) {
        JButton button = new JButton();
        button.setIcon(resizeImage(path, 120, 120));
        button.setBounds(x, y, 120, 120);
        button.addActionListener(e -> {
            selectedFish = path;
            previewLabel.setIcon(resizeImage(path, 140, 140)); // Preview
        });
        return button;
    }

    private ImageIcon resizeImage(String path, int width, int height) {
        File file = new File(path);
        System.out.println("Looking for image at: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("Error: File not found - " + path);
            return new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)); // Placeholder image
        }

        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public String getSelectedFish() {
        return selectedFish;
    }

    public void setOnGameStart(Runnable onGameStart) {
        this.onGameStart = onGameStart;
    }

    public void addSelectionListener(java.util.function.Consumer<String> listener) {
        this.onGameStart = () -> listener.accept(selectedFish);
    }
}
