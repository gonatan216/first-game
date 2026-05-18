import javax.swing.*;
import java.awt.*;

public class Bullet extends GameObject {
    private Image bulletImage;

    public Bullet (int x, int y, int width, int height, int speed) {
        super(x, y, width,  height, speed);
        this.bulletImage = new ImageIcon("wv.png").getImage();
    }

    @Override
    public void move (int direction) {
        y-=speed;
    }

    @Override
    public void draw (Graphics graphics) {
        if (bulletImage != null) {
            graphics.drawImage(bulletImage, x, y, width, height, null);
        }
    }
}
