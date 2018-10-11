public abstract class Level {

    Bomb bomb;
    Shot shot;
    Alien alien;
    int numberOfLevel;
    int live;
    Board board;


    public Level(Bomb bomb, Shot shot, Alien alien, int numberOfLevel, int live) {
        this.bomb = bomb;
        this.shot = shot;
        this.alien = alien;
        this.numberOfLevel = numberOfLevel;
        this.live = live;
    }

    abstract void velocidadBomb();

    abstract void velocidadAlien();

    //maximo nivel permitido es cinco

    private void maxLevel(int numberOfLevel){

        if (numberOfLevel == 5 && board.isIngame ()) {

            board.setIngame ( false );
            System.out.println ("You Won The Game");
        }

    }

    public int getNumberOfLevel() {
        return numberOfLevel;
    }
}
