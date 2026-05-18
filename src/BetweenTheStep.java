import javax.swing.*;
import java.awt.*;

public class BetweenTheStep extends JFrame {
    private int width = 1376;
    private int height = 768;
    private Image backgroundImage;
    private int finalScore;

    public BetweenTheStep(int score) {
        this.finalScore = score;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Game Over");
        this.backgroundImage = new ImageIcon("background for entry screen.png").getImage();

        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(width, height));
        this.setContentPane(mainPanel);

//        JLabel labelGameOver = new JLabel("GAME OVER", SwingConstants.CENTER);
//        labelGameOver.setFont(new Font("Arial", Font.TYPE1_FONT, 80));
//        labelGameOver.setForeground(Color.RED);
//        labelGameOver.setBounds(0, 100, width, 100);
//        mainPanel.add(labelGameOver);

        JLabel labelScore = new JLabel("Your Final Score: " + finalScore, SwingConstants.CENTER);
        labelScore.setFont(new Font("Arial", Font.BOLD, 40));
        labelScore.setForeground(Color.WHITE);
        labelScore.setBounds(width / 2 - 670, 700, width, 50);
        mainPanel.add(labelScore);

        JButton btnPlayAgain = createGlassButton("Play Again");
        btnPlayAgain.setBounds(width / 2 - 150, 600, 300, 80);
        mainPanel.add(btnPlayAgain);

        btnPlayAgain.addActionListener(e -> {
            this.dispose();
            new Window();
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JButton createGlassButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 80));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.setColor(new Color(255, 255, 255, 150));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 35));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, width, height, this);
        }
    }
}
