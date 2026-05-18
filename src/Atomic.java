import javax.swing.*;
import java.awt.*;

public class Atomic extends GameObject{
    private Image imageForAtomic;
    private int countBulletHurtMe = 5;

    public Atomic (int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);

        this.imageForAtomic = new ImageIcon("space2ForAtomic.png").getImage();
    }

    public void move (int direction) {
        y += speed;
    }

    public void draw (Graphics graphics) {
        if (imageForAtomic != null) {
            graphics.drawImage(imageForAtomic, x, y, width, height, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getCountBulletHurtMe() {
        return countBulletHurtMe;
    }

    public void takeDamage() {
        this.countBulletHurtMe--;
    }

    public boolean isDead() {
        return this.countBulletHurtMe <= 0;
    }
}
