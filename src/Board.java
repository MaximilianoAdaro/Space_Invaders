import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

public class Board extends JPanel implements Runnable, Commons {

    private Dimension d;
    private ArrayList<Alien> aliens;
    private Player player;
    private Ufo ufo;

    private Shot shot;
    private ArrayList<Shield> shields;

    private int DELAY = 17;
    public int alienSpeed = 1;
    private int level = 1;

    private final int SHIELD_INIT_X = 40;
//    private final int SHIELD_INIT_Y = 200;

    private final int ALIEN_INIT_X = 150;
    private final int ALIEN_INIT_Y = 5;
    private int direction = -1;

    private int ufoDirection= -1;
    private long ufoTime;
    private long time;
    private long shotTime;

    private int deaths = 0;
    private int successfulShots;

    private Power power;
    private Drawer drawer= new Drawer(this);

    private int shieldsAmount = 4;

    boolean ingame = true;

    final String explImg = "src/images/explosion.png";
    private String message = "Game Over";


    private Thread animator;

    Board() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);

        gameInit();
        setDoubleBuffered(true);
    }

    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }

    private void gameInit() {

        aliens = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 0) {
                    Alien alien = new Alien(ALIEN_INIT_X + 35 * j, ALIEN_INIT_Y + 18 * i, new AlienType(30, "src/images/alienSmall.png"));
                    aliens.add(alien);
                }
                if (i == 1) {
                    Alien alien = new Alien(ALIEN_INIT_X + 35 * j, ALIEN_INIT_Y + 18 * i, new AlienType(20, "src/images/alienMedium.png"));
                    aliens.add(alien);
                }
                if (i >= 2) {
                    Alien alien = new Alien(ALIEN_INIT_X + 35 * j, ALIEN_INIT_Y + 18 * i, new AlienType(10, "src/images/alienBig.png"));
                    aliens.add(alien);
                }
            }
        }

        ufo= new Ufo(1,1,new AlienType((int) (Math.random()*251+15),"src/images/alienBig.png"));

        successfulShots =0;
        player= new Player();
        power= new Power(this);
        newUfoTime();

        shields = new ArrayList<>();

        shot= new Shot();

        for (int i = 0; i < shieldsAmount; i++) {
            Shield shield = new Shield(SHIELD_INIT_X + 40 * (2 * i));
            shields.add(shield);
        }

        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (ingame) {
            time= (System.currentTimeMillis()/1000);

            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }

        gameOver();
    }

    private void animationCycle() {

        System.out.println(time);
        System.out.println(ufoTime);

        message = "Game Over";

        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY && level == 5) {
            message = "Game Won";
            ingame = false;
        }

        // level up
        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY && level < 5) {
            levelUp();
        }

        if (!player.isVisible()) {
            ingame = false;
        }
        // player
        player.act();

        //power
        powerAction();

        // shot
        if (shot.isVisible()) {
            shotAct();
        }

        //ufo
        if (ufo.isUfoActive()) {
            activeUfo();
        } else {
            if (ufoTime == time)
                ufo.setUfoIsActive(true);
        }

        // aliens
        buildAlien();

        // bombs
        alienBombAct();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (ingame) {

            g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            drawAliens(g);
            drawPlayer(g);
            drawShields(g);
            drawShot(g);
            drawBombing(g);
            drawUfo(g);
        }


        Font big = new Font("Times New Roman", Font.PLAIN, 16);
        Font small = new Font("Times New Roman", Font.BOLD, 12);

        g.setColor(Color.red);
        g.setFont(big);
        g.drawString("Lives: " + player.getLives(), 1, 305);

        g.setColor(Color.green);
        g.drawString("Level: " + level, 150, 303);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Score: " + player.getPoints(), 220, 303);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    private void gameOver() {

        Graphics g = this.getGraphics();

        buttons();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(new Color(112, 106, 37));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);
        String score = "Your score was: " + player.getPoints();
        g.drawString(score, (BOARD_WIDTH - metr.stringWidth(score)) / 2 - 2,
                BOARD_WIDTH / 2 + 17);
    }

    private void buttons() {
        JButton buttonPlayAgain= new JButton("Play Again");
        ActionListener actionListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Board();
            }
        };
        buttonPlayAgain.setBackground(new Color(112, 106, 37));
        buttonPlayAgain.setForeground(Color.white);

        buttonPlayAgain.setBounds(BOARD_WIDTH/2,BOARD_HEIGHT/2+300,100,100);
        buttonPlayAgain.addActionListener(actionListener);
        add(buttonPlayAgain);
    }


    private void levelUp() {
        level++;
        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(new Color(112, 106, 37));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Level "+ level, (BOARD_WIDTH - metrics.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);

        if(ufo.isUfoActive()){
            newUfoTime();
            ufo.setUfoIsActive(false);
        }

        deaths = 0;
        DELAY -=2;
        shieldsAmount--;

        try {
            ufoTime += 2;
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            gameInit();
        }
    }

    private void shotAct(){


        int shotX = shot.getX();
        int shotY = shot.getY();

        for (Alien alien : aliens) {

            int alienX = alien.getX();
            int alienY = alien.getY();

            if (alien.isVisible() && shot.isVisible()) {
                if (shotX >= (alienX)
                        && shotX <= (alienX + ALIEN_WIDTH)
                        && shotY >= (alienY)
                        && shotY <= (alienY + ALIEN_HEIGHT)) {
                    ImageIcon ii = new ImageIcon(explImg);
                    alien.setImage(ii.getImage());
                    alien.setDying(true);
                    deaths++;
                    player.addPoints(alien.getPoints());
                    successfulShots++;
                    shot.die();
                }
            }

        }

        int y = shot.getY();
        y -= 4;

        if (y < 0) {
            successfulShots =0;
            shot.die();
        } else {
            shot.setY(y);
        }
    }

    private void activeUfo(){

        if (ufo.getX() <= BORDER_LEFT && ufoDirection == -1) {
            ufoDirection = 1;
        }
        ufo.act(ufoDirection);

        if (ufo.getX() >= BOARD_WIDTH - BORDER_RIGHT) {
            newUfoTime();
            ufo.setUfoIsActive(false);
        }

        int shotX = shot.getX();
        int shotY = shot.getY();

        int ufoX = ufo.getX();
        int ufoY = ufo.getY();
        if (ufo.isUfoActive() && shot.isVisible()) {
            if (shotX >= (ufoX)
                    && shotX <= (ufoX + ALIEN_WIDTH)
                    && shotY >= (ufoY)
                    && shotY <= (ufoY + ALIEN_HEIGHT)) {

                ufo.setDying(true);

                player.addPoints(ufo.getPoints());
                ufo.changePoints();

                successfulShots++;
                shot.die();

            }
        }
    }

    private void powerAction() {
        if(successfulShots>=4){
            shotTime= time+ ((int) (Math.random()*2) +3) ;

            int random= (int)(Math.random()*100);
            if(random<=20){
                power.immunityPlayer();
            } else if(random<90) {
                power.doubleShot();
            } else {
                power.freezeAlien();
            }

            successfulShots=0;
        }

        if(power.isImmunityPlayer()||power.isFreezeAlien()||power.isDoubleShot()){
            successfulShots=0;
        }

        if(shotTime==time){
            power.setDoubleShot(false);
            power.setFreezeAlien(false);
            power.setImmunityPlayer(false);
        }
    }

    private void buildAlien(){
        for (Alien alien : aliens) {

            int x = alien.getX();

            if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {

                direction = -1;
                Iterator i1 = aliens.iterator();

                while (i1.hasNext()) {
                    Alien a2 = (Alien) i1.next();
                    a2.setY(a2.getY() + GO_DOWN);
                }
            }

            if (x <= BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator i2 = aliens.iterator();

                while (i2.hasNext()) {
                    Alien a = (Alien) i2.next();
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }

        Iterator it = aliens.iterator();

        while (it.hasNext()) {
            Alien alien = (Alien) it.next();

            if (alien.isVisible()) {
                int y = alien.getY();
                if (y > GROUND - ALIEN_HEIGHT) {
                    ingame = false;
                    message = "Invasion!";
                }
                if(!power.isFreezeAlien()) {
                    alien.act(direction);
                }
            }
        }
    }

    private void alienBombAct(){
        Random generator = new Random();

        for (Alien alien : aliens) {

            int shots = generator.nextInt(15);
            Bomb b = alien.getBomb();

            if (shots == CHANCE && alien.isVisible() && b.isDestroyed()) {

                b.setDestroyed(false);
                b.setX(alien.getX());
                b.setY(alien.getY());
            }

            int bombX = b.getX();
            int bombY = b.getY();// hitbox bomb
            int playerX = player.getX();
            int playerY = player.getY();


            if (player.isVisible() && !b.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + PLAYER_HEIGHT)) {

                    b.setDestroyed(true);
                    if(!power.isImmunityPlayer()) {
                        player.getHit();
                    }
                }
            }

            //shields
            for (Shield shield : shields) {
                int shieldX = shield.getX();
                int shieldY = shield.getSTART_Y();
                int shotX = shot.getX();
                int shotY = shot.getY();

                if (shield.isVisible() && !b.isDestroyed()) {
                    if (bombX >= (shieldX)
                            && bombX <= (shieldX + SHIELD_WIDTH)
                            && bombY >= (shieldY - SHIELD_HEIGHT)
                            && bombY <= (shieldY + SHIELD_HEIGHT)) {
                        b.setDestroyed(true);
                        shield.getHit();
                    }
                }


                if (shotX >= shieldX && shield.isVisible()
                        && shotX <= (shieldX + shield.getWidth())
                        && shotY <= (shieldY + SHIELD_HEIGHT)
                        && shotY > (shieldY)
                        && shot.isVisible()) {
                    System.out.println(shield.getRemainingShield());
                    shield.getHit();

                    successfulShots =0;
                    shot.die();
                }

            }


            if (!b.isDestroyed()) {

                // velocidad bombas
                b.setY(b.getY() + 1);

                if (b.getY() >= GROUND - BOMB_HEIGHT) {
                    b.setDestroyed(true);
                }
            }
        }
    }

    private void newUfoTime(){
        long difference= (System.currentTimeMillis() / 1000);
        ufoTime= difference + ((int) (Math.random() * 16) +45);
    }


    void drawAliens(Graphics g) {
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    void drawUfo(Graphics g){
        if(ufo.isUfoActive()) {
            if (ufo.isVisible()){
                g.drawImage(ufo.getImage(), ufo.getX(), 1, this);
            }
            if (ufo.isDying()){
                ufo.die();
            }
        }
    }

    void drawShields(Graphics g) {
        for (Shield shield : shields) {
            if (shield.isVisible()) {
                g.drawImage(shield.getImage(), shield.getX(), shield.getY(), this);
            }
        }
    }

    void drawPlayer(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        if (player.isDying()) {
            player.die();
            ImageIcon ii = new ImageIcon(explImg);
            player.setImage(ii.getImage());
            ingame = false;
        }
    }

    void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    void drawBombing(Graphics g) {

        for (Alien a : aliens) {

            Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }

    public Player getPlayer() {
        return player;
    }

    public Ufo getUfo() {
        return ufo;
    }

    public ArrayList<Shield> getShields() {
        return shields;
    }

    //clase que no hay que tocar TAdapter
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (ingame) {
                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                    }
                }

            }
        }
    }
}