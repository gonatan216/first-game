import javax.swing.*;
import java.awt.*;

public class Prize extends GameObject{
    private Image imageForPrize;

    public Prize (int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);

        this.imageForPrize = new ImageIcon("prize.png").getImage();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move(int direction) {
        this.y += speed;
    }

    public void draw (Graphics graphics) {
        if (imageForPrize != null) {
            graphics.drawImage(imageForPrize, x, y, width, height, null);
        }
    }

}

