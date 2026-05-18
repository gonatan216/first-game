import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameManager extends JPanel {
    private int width;
    private int height;

    private Image imageBackground;

    private Tank tank;
    private ArrayList<Missile> missiles;
    private ArrayList<Bullet> bullets;
    private ArrayList<SuperMissiles> superMissiles;
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private ArrayList<Image> explosionImages = new ArrayList<>();
    private ArrayList<Prize> prizes = new ArrayList<>();
    private ArrayList<Atomic> atomics = new ArrayList<>();

    private long lastShotTime = 0;
    private final long SHOT_DELAY = 200;

    private boolean isRunning = true;

    private long startTime;
    private double speedMultiplier = 1.0;
    private boolean stage2Started = false;
    private int timeForNextLevel = 15;

    private JLabel firstTitle;

    private int scoreCount;
    private JLabel scoreLabel;

    private int amountOfExplosions;
    private JLabel titleAmountOfExplosion;

    private int amountOfBullet;
    private JLabel titleAmountOfBullet;

    private int amountSuperMissiles;
    private JLabel titleAmountOfSuperMissiles;

    private int amountOfRegularMissiles;
    private JLabel titleAmountOfRegularMissiles;

    private int amountTotalMissiles;
    private JLabel titleAmountOfTotalMissiles;

    private JLabel livesLabel;

    private int amountOfLevelCurrent;
    private JLabel titleAmountOfLevels;




    public GameManager (int width, int height) {
        this.width = width;
        this.height = height;

        this.startTime = System.currentTimeMillis();

        tank = new Tank(width / 2, height - 400, 200,  200, 30, 3, width);

        missiles = new ArrayList<>();
        bullets = new ArrayList<>();
        superMissiles = new ArrayList<>();

        this.imageBackground = new ImageIcon("new background.gif").getImage();

        KeyboardControl input = new KeyboardControl(tank, this);
        this.addKeyListener(input);
        this.setFocusable(true);

        new Thread(() -> {
            while (isRunning) {

                updateGameLogic();
                repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 1; i <= 10; i++) {
            Image img = new ImageIcon("boom" + i + ".png").getImage();
            explosionImages.add(img);
        }

        SoundManager.playBackgroundMusic("Sound For Background2.WAV");


        this.setLayout(null);

        this.firstTitle = new JLabel("Progress Board");
        this.firstTitle.setFont(new Font("Arial", Font.BOLD, 40));
        this.firstTitle.setForeground(Color.WHITE);
        this.firstTitle.setBounds(60, height - 1050, 350, 50);
        this.add(this.firstTitle);


        this.scoreCount = 0;
        this.scoreLabel = new JLabel("Final Score: 0");
        this.scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        this.scoreLabel.setForeground(Color.RED);
        this.scoreLabel.setBounds(80, height - 950, 350, 50);
        this.add(this.scoreLabel);

        this.amountOfExplosions = 0;
        this.titleAmountOfExplosion = new JLabel("Amount Explosions: 0");
        this.titleAmountOfExplosion.setFont(new Font("Arial", Font.BOLD, 30));
        this.titleAmountOfExplosion.setForeground(Color.WHITE);
        this.titleAmountOfExplosion.setBounds(80, height - 880, 350, 50);
        this.add(this.titleAmountOfExplosion);

        this.amountOfBullet = 0;
        this.titleAmountOfBullet = new JLabel("Amount Bullet: 0");
        this.titleAmountOfBullet.setFont(new Font("Arial", Font.BOLD, 30));
        this.titleAmountOfBullet.setForeground(Color.WHITE);
        this.titleAmountOfBullet.setBounds(80, height - 810, 350, 50);
        this.add(this.titleAmountOfBullet);

        this.amountSuperMissiles = 0;
        this.titleAmountOfSuperMissiles = new JLabel("Amount Super Missiles: 0");
        this.titleAmountOfSuperMissiles.setForeground(Color.WHITE);
        this.titleAmountOfSuperMissiles.setFont(new Font("Arial", Font.BOLD, 30));
        this.titleAmountOfSuperMissiles.setBounds(80, height - 740, 400, 50);
        this.add(this.titleAmountOfSuperMissiles);

        this.amountOfRegularMissiles = 0;
        this.titleAmountOfRegularMissiles = new JLabel("Amount Regular missiles: 0");
        this.titleAmountOfRegularMissiles.setForeground(Color.WHITE);
        this.titleAmountOfRegularMissiles.setFont(new Font("Arial", Font.BOLD, 30));
        this.titleAmountOfRegularMissiles.setBounds(80, height - 670, 450, 50);
        this.add(this.titleAmountOfRegularMissiles);

        this.amountTotalMissiles = 0;
        this.titleAmountOfTotalMissiles = new JLabel("Total Missiles: 0");
        this.titleAmountOfTotalMissiles.setForeground(Color.WHITE);
        this.titleAmountOfTotalMissiles.setFont(new Font("Arial", Font.BOLD, 30));
        this.titleAmountOfTotalMissiles.setBounds(80, height - 600, 450, 50);
        this.add(this.titleAmountOfTotalMissiles);

        this.livesLabel = new JLabel("Lives: " + tank.getLives());
        this.livesLabel.setFont(new Font("Arial", Font.BOLD, 30));
        this.livesLabel.setForeground(Color.GREEN);
        this.livesLabel.setBounds(80, height - 530, 350, 50);
        this.add(this.livesLabel);

        this.amountOfLevelCurrent = 1;
        this.titleAmountOfLevels = new JLabel("Level: " + amountOfLevelCurrent);
        this.titleAmountOfLevels.setForeground(Color.RED);
        this.titleAmountOfLevels.setFont(new Font("Arial", Font.BOLD, 25));
        this.titleAmountOfLevels.setBounds(80, height - 450, 300, 50);
        this.add(titleAmountOfLevels);


        startGame();
    }



    public void startGame(){
        new Thread(() -> {

            while (true) {

                try {
                    Thread.sleep(1000);
                    int random = (int) (Math.random() * (this.getWidth() - 100));

                    double change = Math.random();

                    if (change < 0.3) {

                        synchronized (superMissiles) {

                            int currentSpeed = (int) (10 * speedMultiplier);
                            superMissiles.add(new SuperMissiles(random, -50, 100, 100, currentSpeed, 10));

                            this.amountTotalMissiles ++;
                            this.titleAmountOfTotalMissiles.setText("Total Missiles: " + this.amountTotalMissiles);
                        }

                    } else {

                        synchronized (missiles) {
                            int currentSpeed = (int) (5 * speedMultiplier);
                            missiles.add(new Missile(random, -50, 100, 100, currentSpeed));

                            this.amountTotalMissiles ++;
                            this.titleAmountOfTotalMissiles.setText("Total Missiles: " + this.amountTotalMissiles);
                        }
                    }

                    double prizeRandom = Math.random();
                    if (prizeRandom < 0.1) {
                        synchronized (prizes){
                            int randomForPrize = (int) (Math.random() * (this.getWidth() -100));
                            int currentSpeed = (int) (7 * speedMultiplier);
                            prizes.add(new Prize(randomForPrize, -50, 70, 70, currentSpeed));
                        }
                    }

                    double atomicRandom = Math.random();
                    if (atomicRandom < 0.1 / 2){
                        synchronized (atomics){
                            int randomAtomic =(int) (Math.random() * (this.getWidth() -100));
                            int currentSpeed = (int) (4 * speedMultiplier);
                            atomics.add(new Atomic(randomAtomic, -300, 400, 400, currentSpeed));
                        }
                    }

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }

    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(imageBackground, 0, 0, this.width, this.height, null );

        tank.draw(graphics);

        synchronized (missiles) {
            for (int i = 0; i < missiles.size(); i++) {
                missiles.get(i).draw(graphics);
            }
        }

        synchronized (bullets) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(graphics);
            }
        }

        synchronized (superMissiles) {
            for (int i = 0; i < superMissiles.size(); i++) {
                superMissiles.get(i).draw(graphics);
            }
        }

        synchronized (prizes) {
            for (int i = 0; i < prizes.size(); i++) {
                prizes.get(i).draw(graphics);
            }
        }

        synchronized (atomics){
            for (int i = 0; i < atomics.size(); i++) {
                atomics.get(i).draw(graphics);
            }
        }

        synchronized (explosions) {

            for (int i = 0; i < explosions.size(); i++) {
                Explosion exp = explosions.get(i);

                exp.draw(graphics);

                if (exp.isFinished()) {
                    explosions.remove(i);
                    i--;
                }
            }
        }
    }


    public void updateGameLogic () {
        long elapsedSecond = (System.currentTimeMillis() - startTime) / 1000;

        if (elapsedSecond >= timeForNextLevel && !stage2Started) {
            this.speedMultiplier = 2.0 * 0.75;
            this.stage2Started = true;

            this.amountOfLevelCurrent++;
            this.titleAmountOfLevels.setText("Level: " + amountOfLevelCurrent);

            this.firstTitle.setForeground(Color.YELLOW);
            this.scoreLabel.setForeground(Color.YELLOW);
            this.titleAmountOfExplosion.setForeground(Color.YELLOW);
            this.titleAmountOfBullet.setForeground(Color.YELLOW);
            this.titleAmountOfSuperMissiles.setForeground(Color.YELLOW);
            this.titleAmountOfRegularMissiles.setForeground(Color.YELLOW);
            this.titleAmountOfTotalMissiles.setForeground(Color.YELLOW);
            this.livesLabel.setForeground(Color.YELLOW);
            this.titleAmountOfLevels.setForeground(Color.RED);
        }

        if (tank.getLives() <= 0) {
            isRunning = false;
            new BetweenTheStep(this.scoreCount);

            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();

            return;
        }

        tank.update();
        checkCollisions();

        synchronized (missiles) {
            for (int i = 0; i < missiles.size(); i++) {
                missiles.get(i).move(0);

                if (missiles.get(i).getY() > height) {
                    tank.reduceLives();

                    this.livesLabel.setText("Lives: " + tank.getLives());

                    missiles.remove(i);
                    i--;
                }
            }
        }

        synchronized (bullets) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).move(0);

                if (bullets.get(i).getY() < 0) {
                    bullets.remove(i);
                    i--;
                }
            }
        }

        synchronized (superMissiles) {
            for (int i = 0; i < superMissiles.size(); i++) {
                superMissiles.get(i).move(0);

                if (superMissiles.get(i).getY() > height) {

                    tank.reduceLives();
                    this.livesLabel.setText("Lives: " + tank.getLives());

                    superMissiles.remove(i);
                    i--;
                }
            }
        }

        synchronized (prizes) {
            for (int i = 0; i < prizes.size(); i++) {
                prizes.get(i).move(0);

                if (prizes.get(i).getY() > height){

                    prizes.remove(i);
                    i--;
                }
            }
        }

        synchronized (atomics){
            for (int i = 0; i < atomics.size(); i++) {
                atomics.get(i).move(0);

                if (atomics.get(i).getY() > height){
                    atomics.remove(i);
                    i--;
                }
            }
        }

        repaint();
    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastShotTime >= SHOT_DELAY) {

            Bullet newBullet = new Bullet(tank.getX() + (tank.getWidth() / 2 ) - 60, tank.getY(), 130, 130, 30);
            this.bullets.add(newBullet);

            this.amountOfBullet++;
            this.titleAmountOfBullet.setText("Amount Bullet: " + this.amountOfBullet);

            lastShotTime = currentTime;

            SoundManager.playShoot();
        }
    }

    public void checkCollisions() {
        synchronized (bullets) {
            synchronized (missiles) {
                synchronized (superMissiles) {
                    synchronized (prizes){
                        synchronized (atomics) {


                            for (int i = bullets.size() - 1; i >= 0; i--) {
                                for (int j = missiles.size() - 1; j >= 0; j--) {

                                    if (bullets.get(i).getBounds().intersects(missiles.get(j).getBounds())) {

                                        this.scoreCount+= 10;
                                        this.scoreLabel.setText("Final Score: " + this.scoreCount);

                                        this.amountOfExplosions ++;
                                        this.titleAmountOfExplosion.setText("Amount Explosion: " + this.amountOfExplosions);

                                        Explosion boom = new Explosion(missiles.get(j).getX(), missiles.get(j).getY(), explosionImages);
                                        explosions.add(boom);

                                        bullets.remove(i);
                                        missiles.remove(j);

                                        SoundManager.playExplosionForRegularMissiles();
                                        break;
                                    }
                                }
                            }

                            for (int i = bullets.size() -1; i >= 0; i--) {
                                for (int j = superMissiles.size() -1; j >= 0 ; j--) {

                                    if (bullets.get(i).getBounds().intersects(superMissiles.get(j).getBounds())){

                                        this.scoreCount += 50;
                                        this.scoreLabel.setText("Final score: " + this.scoreCount);

                                        this.amountSuperMissiles += 50;
                                        this.titleAmountOfSuperMissiles.setText("Amount Super Missiles: " + this.amountSuperMissiles);

                                        this.amountOfExplosions ++;
                                        this.titleAmountOfExplosion.setText("Amount Explosion: " + this.amountOfExplosions);

                                        Explosion boom = new Explosion(superMissiles.get(j).getX(), superMissiles.get(j).getY(), explosionImages);
                                        explosions.add(boom);

                                        bullets.remove(i);
                                        superMissiles.remove(j);

                                        SoundManager.playExplosionForSuperMissiles();
                                        break;
                                    }
                                }
                            }

                            for (int i = bullets.size() - 1; i >= 0; i--) {
                                for (int j = prizes.size() - 1; j >= 0; j--) {

                                    if (bullets.get(i).getBounds().intersects(prizes.get(j).getBounds())) {

                                        this.scoreCount += 100;
                                        this.scoreLabel.setText("Final Score: " + this.scoreCount);

                                        tank.addLives();
                                        this.livesLabel.setText("Lives: " + tank.getLives());

                                        this.amountOfExplosions++;
                                        this.titleAmountOfExplosion.setText("Amount Explosion: " + this.amountOfExplosions);

                                        Explosion boom = new Explosion(prizes.get(j).getX(), prizes.get(j).getY(), explosionImages);
                                        explosions.add(boom);

                                        bullets.remove(i);
                                        prizes.remove(j);

                                        SoundManager.playExplosionForPrize();
                                        break;
                                    }
                                }
                            }

                            for (int i = bullets.size() -1; i >= 0 ; i--) {
                                for (int j = atomics.size() -1; j >= 0; j--) {


                                    if (bullets.get(i).getBounds().intersects(atomics.get(j).getBounds())){
                                        bullets.remove(i);
                                        atomics.get(j).takeDamage();

                                        if (atomics.get(j).isDead()){

                                            this.scoreCount += 1000;
                                            this.scoreLabel.setText("Final Score: " + this.scoreCount);

                                            Explosion boom = new Explosion(atomics.get(j).getX(), atomics.get(j).getY(), explosionImages);
                                            explosions.add(boom);

                                            atomics.remove(j);

                                            SoundManager.playExplosionForAtomic();
                                        }
                                        break;
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }







}
