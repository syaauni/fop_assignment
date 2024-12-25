import java.awt.*;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

abstract class EnemyFish {
    int x, y, width, height, size;
    Image image;

    public EnemyFish(int x, int y, int width, int height, String imagePath, int size) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new ImageIcon(imagePath).getImage();
        this.size = size;
    }

    public void move() {
        x -= 5;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(image, x, y, width, height, observer);
    }
    
    public int getX() {
        return x;
    }
    
    public int getWidth(){
        return width;
    }
}

class SmallFish extends EnemyFish {
    public SmallFish(int x, int y) {
        super(x, y, 40, 20, "/Users/mannibh/Downloads/fop_assignment/smallfish.png/", 1);
    }
}

class MediumFish extends EnemyFish {
    public MediumFish(int x, int y) {
        super(x, y, 60, 30, "/Users/mannibh/Downloads/fop_assignment/mediumfish.png", 2);
    }
}

class LargeFish extends EnemyFish {
    public LargeFish(int x, int y) {
        super(x, y, 100, 50, "/Users/mannibh/Downloads/fop_assignment/largefish.png", 3);
    }
}
