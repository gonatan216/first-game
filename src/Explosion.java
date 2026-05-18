import java.awt.*;
import java.util.ArrayList;

public class Explosion {
    private int x;
    private int y;

    private ArrayList<Image> frames;
    private int frameOfPicture;

    private int countForSpeed = 0;
    private int myControl = 6;

    public Explosion (int x, int y,  ArrayList<Image> frames) {
        this.x = x;
        this.y = y;
        this.frameOfPicture = 0;
        this.frames = frames;
    }

    public void draw (Graphics graphics) {
        if (frameOfPicture < frames.size()) {
            graphics.drawImage(frames.get(frameOfPicture), x, y, 300, 300, null);
            countForSpeed++;

            if (countForSpeed >= myControl) {
                frameOfPicture++;
                countForSpeed = 0;
            }
        }
    }

    public boolean isFinished() {
        return frameOfPicture >= frames.size();
    }


}
