import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {

    private final int START_Y = 280;
    private final int START_X = 270;

    private final String playerImg = "src/images/player.png";
    private int width;
    private int lives;
    private int shield = 4;
    private int shieldRemaining = 100 ;

    public Player() {

        ImageIcon ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);

        lives = 3;
    }


    public void getHit()
    {
        if (shield >=1 && shieldRemaining != 0)
        {
            shieldRemaining = shieldRemaining - 2;
        }

        if (shieldRemaining == 0 && shield != 0)
        {
            shield = shield -1;
            shieldRemaining = 100;
        }


        if (shield ==0 && shieldRemaining ==0)
        {
            lives = lives-1;
            if (lives == 0)
            {
                setDying(true);
            }
        }
        /*lives = lives-1;
        if (lives == 0)
        {
            setDying(true);
        }*/
    }

    public int getShield()
    {
        return shield;
    }

    public int getShieldRemaining()
    {
        return shieldRemaining;
    }

    public int getLives(){
        return lives;
    }


    public void act() {

        x += dx;
        // x es posicion
        //dx es velocidad

        if (x <= 2) {
            x = 2;
        }

        if (x >= BOARD_WIDTH - 2 * width) {
            x = BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }
}