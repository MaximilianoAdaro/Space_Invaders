class Power {

    private Board board;

    private boolean isImmunityPlayer;
    private boolean isFreezeAlien;
    private boolean isDoubleShot;

    Power(Board board) {
        this.board = board;
        isDoubleShot=false;
        isFreezeAlien=false;
        isImmunityPlayer=false;
    }

    void immunityPlayer() {
//        No se le baja la vida al jugador
    }

    void freezeAlien() {
//        Se congelan los Aliens
    }

    void doubleShot() {
//        Doble tiro del jugador
    }

    boolean isImmunityPlayer() {
        return isImmunityPlayer;
    }

    boolean isFreezeAlien() {
        return isFreezeAlien;
    }

    boolean isDoubleShot() {
        return isDoubleShot;
    }

    public void setImmunityPlayer(boolean immunityPlayer) {
        isImmunityPlayer = immunityPlayer;
    }

    public void setFreezeAlien(boolean freezeAlien) {
        isFreezeAlien = freezeAlien;
    }

    public void setDoubleShot(boolean doubleShot) {
        isDoubleShot = doubleShot;
    }
}
