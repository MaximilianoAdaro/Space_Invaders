import java.awt.EventQueue;
import javax.swing.*;

public class SpaceInvaders extends JFrame implements Commons {

    public SpaceInvaders() {

//      Voy a tener que agregar botones (Jpanel antes)

 /*
        JPanel panel= new JPanel();

        JButton boton= new JButton("Nuevo boton");
        boton.setVisible(true);
        boton.setBounds(150,150,100,100);

        panel.add(boton);
        add(panel);
*/
        add(new Board(this));
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void newBoard(){
        EventQueue.invokeLater(() -> {
            setVisible(true);
        });
    }

    public static void main(String[] args) {

        //Pantalla de inicio para el juego
        StarterPanel starterPanel = new StarterPanel();
        while (starterPanel.stillActive()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        SpaceInvaders ex = new SpaceInvaders();
        ex.newBoard();
    }
}