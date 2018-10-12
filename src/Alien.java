import javax.swing.ImageIcon;

public class Alien extends Sprite {  //vamos a tener que implementar Commons

    private Bomb bomb;
    private String alienImg;

    private AlienType alienType;
    private int points;


    public Alien(int x, int y, AlienType typeOfAlien) {

        this.x = x;
        this.y = y;
        alienType=typeOfAlien;

        alienImg=typeOfAlien.getImage();
        points=typeOfAlien.getPoints();

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(alienImg);
        setImage(ii.getImage());
    }

    public void act(int direction) {

        this.x += direction;
    }

    public Bomb getBomb() {

        return bomb;
    }

    public AlienType getAlienType() {
        return alienType;
    }

    public int getPoints() {
        return points;
    }
}