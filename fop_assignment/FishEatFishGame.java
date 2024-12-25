import javax.swing.*;
import java.awt.Font;

public class FishEatFishGame {

    public static void startGame() {
        JFrame frame = new JFrame("Fish Eat Fish Game");
        JLabel label = new JLabel("Welcome to the Fish Eat Fish Gameplay!", SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 24));
        frame.add(label);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
