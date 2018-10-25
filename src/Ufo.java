public class Ufo extends Alien {

    private boolean ufoIsActive;

    public Ufo(int x, int y, AlienType typeOfAlien) {
        super(x, y, typeOfAlien);
    }

    public void changePoints() {
        points= (int)(Math.random()*251+50);
    }

    public boolean isUfoActive() {
        return ufoIsActive;
    }

    public void setUfoIsActive(boolean ufoIsActive) {
        this.ufoIsActive = ufoIsActive;
    }
}
