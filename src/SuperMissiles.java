import javax.swing.*;
import java.awt.*;

public class SuperMissiles extends GameObject{
    private Image image;

    public SuperMissiles (int x, int y, int width, int height, int speed, int scoreValue) {
        super(x, y, width, height, speed);
        this.image = new ImageIcon("super missiles.png").getImage();
    }

    public void move (int direction) {
        y += speed;
    }

    public void draw (Graphics graphics) {
        if (image != null) {
            graphics.drawImage(this.image, x, y, width, height, null);
        }
    }
}
