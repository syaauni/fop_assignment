import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.KeyEvent;

public class Seafood extends JPanel {
    private PlayerFish player;
    private ArrayList<EnemyFish> enemies;
    private Random rand;
    private boolean isPaused;
    private String playerName;
    private ScoringSystem scoringSystem;
    private String selectedFishImage;

    private Image backgroundImage; // To store the background image

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BASE_ENEMY_COUNT = 5;

    public Seafood(String selectedFishImage, String playerName, ScoringSystem scoringSystem) {
        this.playerName = playerName;
        this.scoringSystem = scoringSystem;
        this.selectedFishImage = selectedFishImage; // Store selected fish image for restarts
        player = new PlayerFish(200, 200, 50, 30, selectedFishImage);
        enemies = new ArrayList<>();
        rand = new Random();
        isPaused = false;

        // Load the background image
        try {
            backgroundImage = new ImageIcon("/Users/mannibh/Downloads/fop_assignment/background.jpg").getImage();
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }

        spawnFish();

        Timer timer = new Timer(16, e -> {
            if (!isPaused) {
                gameLoop();
            }
        });
        timer.start();

        setFocusable(true);
        SwingUtilities.invokeLater(this::requestFocusInWindow);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    isPaused = !isPaused; // Pause/unpause
                } else if (!isPaused) {
                    player.move(e); // Move player
                }
            }
        });
    }

    public void gameLoop() {
        Iterator<EnemyFish> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            EnemyFish enemy = iterator.next();
            enemy.move();

            // Check collision
            if (player.getBounds().intersects(enemy.getBounds())) {
                if (enemy.size > player.getSize()) {
                    // Game Over
                    System.out.println("Game Over! You collided with a larger fish.");
                    JOptionPane.showMessageDialog(
                        null,
                        "Game Over! Your score: " + scoringSystem.getScore(),
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    // Save the player's score to the leaderboard
                    Leaderboard.addScore(playerName, scoringSystem.getScore());

                    // Display the leaderboard
                    try {
                        Leaderboard.displayLeaderboard();
                    } catch (Exception ex) {
                        System.out.println("Error displaying leaderboard: " + ex.getMessage());
                    }

                    // Restart or exit
                    int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to restart?",
                        "Restart Game",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (choice == JOptionPane.YES_OPTION) {
                        restartGame();
                    } else {
                        System.exit(0);
                    }
                } else {
                    // Player eats the enemy fish
                    if (enemy instanceof SmallFish) {
                        scoringSystem.scoreUpdate("small");
                    } else if (enemy instanceof MediumFish) {
                        scoringSystem.scoreUpdate("medium");
                    } else if (enemy instanceof LargeFish) {
                        scoringSystem.scoreUpdate("large");
                    }
                    iterator.remove(); // Remove eaten fish
                    player.grow(); // Player grows
                }
            }

            // Remove enemies that move off-screen
            if (enemy.getX() < -enemy.getWidth()) {
                iterator.remove();
            }
        }

        // Spawn more enemies if needed
        if (enemies.size() < BASE_ENEMY_COUNT + scoringSystem.getLevel()) {
            spawnFish();
        }

        repaint(); // Refresh the screen
    }

    public void spawnFish() {
        while (enemies.size() < BASE_ENEMY_COUNT + scoringSystem.getLevel()) {
            int height = Math.max(getHeight(), 100); // Ensure height is reasonable
            int type = rand.nextInt(3); // Random enemy type
            int yPos = rand.nextInt(Math.max(height - 50, 1)); // Generate valid Y position

            switch (type) {
                case 0 -> enemies.add(new SmallFish(SCREEN_WIDTH, yPos));
                case 1 -> enemies.add(new MediumFish(SCREEN_WIDTH, yPos));
                case 2 -> enemies.add(new LargeFish(SCREEN_WIDTH, yPos));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
        } else {
            g.setColor(Color.CYAN); // Fallback color
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        }

        // Player and enemies
        player.draw(g, this);
        for (EnemyFish enemy : enemies) {
            enemy.draw(g, this);
        }

        // Display score, level, and fish size
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + scoringSystem.getScore(), 10, 20);
        g.drawString("Level: " + scoringSystem.getLevel(), 10, 50);
        g.drawString("Fish Size: " + scoringSystem.getSize(), 10, 80);

        // Pause screen
        if (isPaused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("PAUSED", getWidth() / 2 - 50, getHeight() / 2);
        }
    }

    private void restartGame() {
        JOptionPane.showMessageDialog(null, "Restarting the game...");
        enemies.clear();
        player = new PlayerFish(200, 200, 50, 30, selectedFishImage);
        scoringSystem.reset(); // Reset scoring system
        spawnFish(); // Spawn new enemies
        repaint(); // Refresh the screen
    }
}
