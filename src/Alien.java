import javax.swing.ImageIcon;

public class Alien extends Sprite {  //vamos a tener que implementar Commons

    private Bomb bomb;
    private final String alienImg = "src/images/alien.png";
    private String typeOfAlien;


    public Alien(int x, int y) {

        this.x = x;
        this.y = y;

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


}