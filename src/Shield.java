import javax.swing.*;

public class Shield extends Sprite
{

    int shield = 100;

    private final int START_Y = 200;
    private final String ShieldImg = "src/images/Brickwall.png";
    private int width;
    private int lives;
    private int shieldRemaining = 100 ;
    private boolean visible = true;

    public Shield (int x)
    {
        this.x = x;

        ImageIcon ii = new ImageIcon(ShieldImg);
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        setY(START_Y);
    }

    public void getHit()
    {
        if (shieldRemaining != 0)
        {
            shieldRemaining =  shieldRemaining - 2;
        }

        if (shieldRemaining == 0)
        {
            setDying(true);
        }
    }

    public int getSTART_Y(){
        return START_Y;
    }
    public boolean isVisible()
    {
        return visible;
    }


    public int getShield()
    {return shieldRemaining;}
}
