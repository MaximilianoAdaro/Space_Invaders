import java.util.ArrayList;

public abstract class Level {

    Bomb bomb;
    ArrayList<Alien> aliens = new ArrayList<> (  );
    int numberOfLevel;
   // Board board;


    public Level(Bomb bomb, ArrayList<Alien> aliens, int numberOfLevel) {
        this.bomb = bomb;
        this.aliens = aliens;
        this.numberOfLevel = numberOfLevel;
    }

    abstract void speedBomb();

    abstract void speedAlien();

    public void levelUp(){

        numberOfLevel++;
    }

    //maximo nivel permitido es cinco

    /* private void maxLevel(int numberOfLevel){

        if (numberOfLevel == 5 && board.isIngame ()) {

            board.setIngame ( false );
            System.out.println ("You Won The Game");
        }

    }
*/


    public int getNumberOfLevel() {
        return numberOfLevel;
    }
}
