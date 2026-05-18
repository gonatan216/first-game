import javax.swing.*;
import java.awt.*;

public class EntryScreen extends JFrame {
    private int width = 1376;
    private int height = 768;
    private Image imageForBackground;

    public EntryScreen() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Tank Game - Menu");

        this.imageForBackground = new ImageIcon("background for entry screen.png").getImage();

        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(width, height));
        this.setContentPane(mainPanel);

        JButton buttonToStart = createGlassButton("Play");
        buttonToStart.setBounds(width / 2 - 150, 400, 300, 80);
        mainPanel.add(buttonToStart);

        JButton buttonToShowRules = createGlassButton("Rules");
        buttonToShowRules.setBounds(width / 2 - 150, 550, 300, 80);
        mainPanel.add(buttonToShowRules);

        this.pack();
        this.setLocationRelativeTo(null);

        buttonToStart.addActionListener(e -> {
            this.dispose();
            new Window();
        });

        buttonToShowRules.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "1. Use Arrows to move left/right\n2. Press Space to shoot\n3. Protect your base!",
                    "Game Rules",
                    JOptionPane.INFORMATION_MESSAGE);
        });

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

        button.setFont(new Font("Arial", Font.BOLD, 40));
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
            if (imageForBackground != null) {
                g.drawImage(imageForBackground, 0, 0, width, height, this);
            }
        }
    }
}
