import javax.swing.*;

public class Shield extends Sprite {

    int shield = 100;

    private final int START_Y = 200;
    private final String ShieldImg = "src/images/brickwall.png";
    private int width;
    private int lives;
    private int shieldRemaining = 100 ;

    public Shield (int x) {
        this.x = x;

        ImageIcon ii = new ImageIcon(ShieldImg);
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        setY(START_Y);
    }

    public int getWidth(){
        return width;
    }

    public int getRemainingShield() {
        return shieldRemaining;
    }

    public void getHit()
    {
        if (shieldRemaining != 0) {
            shieldRemaining -= 2;
        }

        if (shieldRemaining == 0) {
            die();
        }
    }

    public int getSTART_Y(){
        return START_Y;
    }


    public int getShield() {
        return shieldRemaining;
    }
}
