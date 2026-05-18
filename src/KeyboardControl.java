import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
    private Tank tank;
    private GameManager gameManager;

    private boolean zPressed = false;
    private boolean xPressed = false;

    public KeyboardControl(Tank tank, GameManager gameManager) {
        this.tank = tank;
        this.gameManager = gameManager;
    }

    public void keyPressed (KeyEvent event) {

        int keyCode = event.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            tank.setDirection(-1);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            tank.setDirection(1);
        } else if (keyCode == KeyEvent.VK_SPACE ) {
            gameManager.shoot();
        }
        if (keyCode == KeyEvent.VK_Z){
            zPressed = true;
        }
        if (keyCode == KeyEvent.VK_X) {
            xPressed = true;
        }

//        if (zPressed && !xPressed) {
//            tank.addLives();
//        } else if (!zPressed && !xPressed) {
//            tank.reduceLives();
//        }else if (zPressed && xPressed) {
//            tank.zeroLives();
//        }
    }

    public void keyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            tank.setDirection(0);
        }
    }

    public void keyTyped(KeyEvent e) {

    }


}
