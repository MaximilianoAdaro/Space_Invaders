import java.awt.Image;

public class Sprite { //muestra en la pantalla

    private boolean visible;
    private Image image;
    int x;
    int y;
    private boolean dying;
    int dx;
    protected int dy; //velocidad de la bomba cayendo en Y

    Sprite() {
        visible = true;
    }

    void die() {
        visible = false;
    }

    boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    void setImage(Image image) {
        this.image = image;
    }

    Image getImage() {
        return image;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getY() {
        return y;
    }

    int getX() {
        return x;
    }

    void setDying(){
        this.dying = true;
    }

    boolean isDying(){
        return this.dying;
    }

}
