import javax.swing.*;
import java.awt.*;

class Drawer extends Board {

    void drawAliens(Graphics g) {
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    void drawUfo(Graphics g){
        if(ufo.isUfoActive()) {
            if (ufo.isVisible()){
                g.drawImage(ufo.getImage(), ufo.getX(), 1, this);
            }
            if (ufo.isDying()){
                ufo.die();
            }
        }
    }


    void drawShields(Graphics g) {
        for (Shield shield : shields) {
            if (shield.isVisible()) {
                g.drawImage(shield.getImage(), shield.getX(), shield.getY(), this);
            }
        }
    }

    void drawPlayer(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        if (player.isDying()) {
            player.die();
            ImageIcon ii = new ImageIcon(explImg);
            player.setImage(ii.getImage());
            ingame = false;
        }
    }

    void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    void drawBombing(Graphics g) {

        for (Alien a : aliens) {

            Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }
}