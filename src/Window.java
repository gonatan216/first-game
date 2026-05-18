
import javax.swing.*;

public class Window extends JFrame {
    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;

    public Window () {
        this.setTitle("Welcome to my first game!!!");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        GameManager gameManager = new GameManager(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(gameManager);

        this.setVisible(true);
        gameManager.requestFocusInWindow();
    }
}
