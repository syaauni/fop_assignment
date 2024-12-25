
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class PlayerFish {

    int x;
    int y;
    private int width, height, size;
    private Image image;

    public PlayerFish(int x, int y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.size = 1; // Initial size
        this.image = new ImageIcon(imagePath).getImage();
    }

    /**
     * Handles the movement of the player fish based on keyboard input.
     * Prevents the player fish from moving out of bounds.
     */
    public void move(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> y -= 10;
            case KeyEvent.VK_DOWN -> y += 10;
            case KeyEvent.VK_LEFT -> x -= 10;
            case KeyEvent.VK_RIGHT -> x += 10;
        }

        // Prevent the fish from moving out of screen bounds
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x + width > 800) x = 800 - width;
        if (y + height > 600) y = 600 - height;
    }

    /**
     * Increases the size of the player fish when it eats another fish.
     * Adjusts the width and height accordingly.
     */
    public void grow() {
        size++;
        width += 5; // Increase width
        height += 3; // Increase height
    }

    /**
     * Returns the current bounding rectangle of the player fish for collision detection.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Draws the player fish on the game panel.
     * 
     * @param g The Graphics object for rendering.
     * @param observer The ImageObserver for rendering images.
     */
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(image, x, y, width, height, observer);
    }

    // Getters and setters for encapsulation

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

