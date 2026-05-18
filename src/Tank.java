import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject{
    private int lives;
    private Image tankImage;
    private int screenWidth;
    private int rightWay = 0;

    public Tank (int x, int y, int width, int height, int speed, int lives, int screenWidth){
        super(x, y, width, height, speed);
        this.lives = lives;
        this.screenWidth = screenWidth;
        this.tankImage = new ImageIcon("yt.png").getImage();
    }

    public void setDirection(int direction) {
        this.rightWay = direction;
    }

    public void update() {
        if (rightWay != 0) {
            move(rightWay);
        }
    }

    public void move(int direction) {
        x = x + (speed * direction);

        if (x + width < 0) {
            x = screenWidth;
        } else if (x > screenWidth) {
            x = -width;
        }
    }

    @Override
    public void draw (Graphics graphics) {
       if (tankImage != null) {
           graphics.drawImage(tankImage, x, y, width, height, null);
       }

    }

    public int getLives() {
        return lives;
    }


    public void reduceLives () {
        this.lives --;
    }

    public void addLives () {
        this.lives++;
    }

    public void zeroLives () {
        this.lives = 0;
    }
}
