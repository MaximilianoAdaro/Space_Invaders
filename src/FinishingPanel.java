import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishingPanel extends JFrame implements Commons{

    private JPanel buttonPanel;
    private JButton buttonPlayAgain;
    private boolean active=true;

    public FinishingPanel(Board board){

        setTitle("Statics");
        setEnabled(true);

        //button
        buttonPlayAgain= new JButton("Play Again");
        buttonPlayAgain.setEnabled(true);
        buttonPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new Board();
                active=false;
                setVisible(false);
                SpaceInvaders spaceInvaders= new SpaceInvaders();
                spaceInvaders.newBoard();
            }
        });
        buttonPlayAgain.setBackground(new Color(48, 112, 66));
        buttonPlayAgain.setForeground(Color.white);
        buttonPlayAgain.setBounds(100, 100, 100, 100);
        buttonPlayAgain.setVisible(true);

        //panel
        buttonPanel = new JPanel(true);
        buttonPanel.setEnabled(true);
        buttonPanel.add(buttonPlayAgain);
        buttonPanel.setBackground(new Color(0, 0, 0));

        //frame
        setBounds(590, 230, BOARD_WIDTH, BOARD_HEIGHT);
        add(buttonPanel);

        setVisible(true);
    }


    boolean isStillActive() {
        return active;
    }
}
