import javax.swing.*;
import java.awt.*;

public class IntroScreen extends JFrame {
    private int currentGif = 1;
    private Image currentImage;
    private JButton buttonForSkip;
    private JButton buttonForStartPlay;
    private boolean isRunningIntro = true;

    public IntroScreen() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        BackgroundPanel mainPanel = new BackgroundPanel();
        this.setContentPane(mainPanel);

        this.setLayout(null);

        this.buttonForSkip = new JButton("Skip");
        this.buttonForSkip.setFont(new Font("Arial", Font.BOLD, 15));
        this.buttonForSkip.setForeground(Color.BLACK);
        this.buttonForSkip.setBounds(1370, 870, 150, 30);
        this.buttonForSkip.setBackground(Color.cyan);
        this.buttonForSkip.setContentAreaFilled(false);
        this.buttonForSkip.setOpaque(true);
        this.buttonForSkip.setBorderPainted(false);
        this.buttonForSkip.setFocusPainted(false);
        this.add(buttonForSkip);

        this.buttonForSkip.addActionListener(e -> {
            this.dispose();
            new EntryScreen();
        });

        this.buttonForStartPlay = new JButton("Start Game");
        this.buttonForStartPlay.setFont(new Font("Arial", Font.BOLD, 15));
        this.buttonForStartPlay.setForeground(Color.BLACK);
        this.buttonForStartPlay.setBounds(1370, 820, 150, 30);
        this.buttonForStartPlay.setBackground(Color.cyan);
        this.buttonForStartPlay.setContentAreaFilled(false);
        this.buttonForStartPlay.setOpaque(true);
        this.buttonForStartPlay.setBorderPainted(false);
        this.buttonForStartPlay.setFocusPainted(false);
        this.add(buttonForStartPlay);

        this.buttonForStartPlay.addActionListener(e -> {
            this.isRunningIntro = false;
            this.dispose();
            new Window();
        });

        this.setVisible(true);

        updateGif();


        new Thread(() -> {
            try {

                while (currentGif <= 4 && isRunningIntro) {
                    Thread.sleep(3000);

                    if (!isRunningIntro){
                        break;
                    }

                    currentGif++;
                    if (currentGif <= 4) {
                        updateGif();
                        repaint();
                    }
                }

                if (isRunningIntro) {
                    this.dispose();
                    new EntryScreen();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateGif() {
        this.currentImage = new ImageIcon("Gif" + currentGif + ".gif").getImage();
    }

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (currentImage != null) {
                g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
