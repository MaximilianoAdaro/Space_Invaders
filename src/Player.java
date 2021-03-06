import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

class Player extends Sprite implements Commons {

    private final int START_Y = 280;
    private final int START_X = 270;

    private final String playerImg = "src/images/player.png";
    private int width;
    private int lives;
    private int points;

    private String name;

    public Player() {
        
        ImageIcon ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);

        lives = 3;
    }


    public void changeName(String name)
    {
        this.name =  name;
    }

    public String getName()
    {
        return name;
    }


    public void getHit()
    {
        if (lives!= 0){lives = lives-1;}

        if (lives == 0){die();}
    }

    public int getLives(){
        return lives;
    }

    public void addPoints(int amountOfPoints){
        points+=amountOfPoints;
    }

    public int getPoints() {
        return points;
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

    public void setLives(int lives) {
        this.lives = lives;
    }
}