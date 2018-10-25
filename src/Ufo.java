public class Ufo extends Alien {

    private boolean ufoIsActive;

    Ufo(int x, int y, AlienType typeOfAlien) {
        super(x, y, typeOfAlien);
    }

    void changePoints() {
        points= (int)(Math.random()*251+50);
    }

    boolean isUfoActive() {
        return ufoIsActive;
    }

    public void setUfoIsActive(boolean ufoIsActive) {
        this.ufoIsActive = ufoIsActive;
    }
}
