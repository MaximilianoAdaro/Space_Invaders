import java.awt.EventQueue;
import javax.swing.JFrame;

public class SpaceInvaders extends JFrame implements Commons {

    private SpaceInvaders() {

//      Voy a tener que agregar botones (Jpanel antes)

        add(new Board());
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SpaceInvaders ex = new SpaceInvaders();
            ex.setVisible(true);
        });
    }
}