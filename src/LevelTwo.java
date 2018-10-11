import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class LevelTwo extends Level {

    public LevelTwo(Bomb bomb, ArrayList<Alien> aliens, int numberOfLevel) {
        super ( bomb, aliens, numberOfLevel );
    }

    @Override
    void speedBomb() {




/*            if (!b.isDestroyed()) {

                b.setY(b.getY() + 1);

                if (b.getY() >= GROUND - BOMB_HEIGHT) {
                    b.setDestroyed(true);
                }
            }
        }


   */
    }

    @Override
    void speedAlien() {

        for (Alien als: aliens) {

            als.act ( 3 );


        }

    }
}