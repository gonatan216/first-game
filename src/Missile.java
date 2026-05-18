import javax.swing.*;
import java.awt.*;

public class Missile extends GameObject {
    private Image imageOfBomb;

    public Missile (int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
        this.imageOfBomb = new ImageIcon("bull from the sky.png").getImage();

    }

    public void move (int direction) {
        y += speed;
    }

    public void draw (Graphics graphics) {
        if (imageOfBomb != null) {
            graphics.drawImage(this.imageOfBomb, x, y, width, height, null);
        }
    }
}
