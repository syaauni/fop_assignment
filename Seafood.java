import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Random;
import java.io.IOException;

public class Seafood extends JPanel {
    PlayerFish player;
    ArrayList<EnemyFish> enemies;
    Random rand;
    int score;

    public Seafood() {
        player = new PlayerFish(200, 200, 50, 30);
        enemies = new ArrayList<>();
        rand = new Random();
        score = 0;

        spawnFish();

        Timer timer = new Timer(16, e -> gameLoop());
        timer.start();
        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                player.move(e);
            }
        });
    }

    public void gameLoop() {
        for (EnemyFish enemy : enemies) {
            enemy.move();

            if (player.getBounds().intersects(enemy.getBounds())) {
                if (enemy instanceof LargeFish) {
                    System.out.println("Game Over! You collided with a large fish!");
                    System.exit(0);
                } else if (player.size >= enemy.size) {
                    if (enemy instanceof SmallFish) {
                        score += 5;
                    } else if (enemy instanceof MediumFish) {
                        score += 10;
                    }

                    enemies.remove(enemy);
                    player.grow();
                    break;
                } else {
                    System.out.println("Game Over! You were eaten by a larger fish!");
                    System.exit(0);
                }
            }

            if (enemy.x < -enemy.width) {
                enemies.remove(enemy);
                break;
            }
        }

        if (enemies.size() < calculateSpawnCount()) {
            spawnFish();
        }

        repaint();
    }

    public int calculateSpawnCount() {
        return 5 + score / 20;
    }

    public void spawnFish() {
        while (enemies.size() < calculateSpawnCount()) {
            int type = rand.nextInt(3);
            switch (type) {
                case 0 -> enemies.add(new SmallFish(800, rand.nextInt(550)));
                case 1 -> enemies.add(new MediumFish(800, rand.nextInt(550)));
                case 2 -> enemies.add(new LargeFish(800, rand.nextInt(550)));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        for (EnemyFish enemy : enemies) {
            enemy.draw(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fish Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.add(new Seafood());
        frame.setVisible(true);
    }
}

class PlayerFish {
    int x, y, width, height, size;
    final int speed = 20;
    final int maxWidth = 60;
    final int maxHeight = 40;
    BufferedImage image;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public PlayerFish(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.size = width;

        try {
            image = ImageIO.read(getClass().getResource("C:\\Users\\Nariesya auni\\FOP ASSIGNMENT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> y = Math.max(y - speed, 0);
            case KeyEvent.VK_DOWN -> y = Math.min(y + speed, 600 - height);
            case KeyEvent.VK_LEFT -> x = Math.max(x - speed, 0);
            case KeyEvent.VK_RIGHT -> x = Math.min(x + speed, 800 - width);
        }
    }

    public void grow() {
        if (width < maxWidth && height < maxHeight) {
            width += 10;
            height += 5;
            size = width;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillOval(x, y, width, height);
        }
    }
}

abstract class EnemyFish {
    int x, y, width, height, speed, size;
    BufferedImage image;

    public EnemyFish(int x, int y, int width, int height, int speed, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.size = width;

        try {
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        x -= speed;
        if (x < -width) {
            x = 800;
            y = new Random().nextInt(550);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawI mage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.GRAY);
            g.fillOval(x, y, width, height);
        }
    }
}

class SmallFish extends EnemyFish {
    public SmallFish(int x, int y) {
        super(x, y, 30, 20, 5, "/resources/smallFish.png");
    }
}

class MediumFish extends EnemyFish {
    public MediumFish(int x, int y) {
        super(x, y, 60, 40, 3, "/resources/mediumFish.png");
    }
}

class LargeFish extends EnemyFish {
    public LargeFish(int x, int y) {
        super(x, y, 100, 70, 2, "/resources/largeFish.png");

  