import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Drawer {

    private Board board;
    ArrayList<Alien> aliens;
    ArrayList<Shield> shields;
    Ufo ufo;

    Drawer(Board board) {
        this.board = board;
        aliens= board.getAliens();
        shields= board.getShields();
        ufo= board.getUfo();
    }
}