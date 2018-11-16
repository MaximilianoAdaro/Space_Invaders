import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class StarterPanel extends JFrame implements  Commons {

//    private JPanel fieldsPanel;
//    private JButton highScore;
    private JPanel buttonPanel;
    private JButton startButton;
    private JLabel labelSpaceInvaders;
    List<String> ranking;

    private boolean active=true;

    public StarterPanel(){

        setTitle("Space Invaders");
        setBounds(590, 230, BOARD_WIDTH, BOARD_HEIGHT);

        //buttonPlayAgain
        button();

        //labelSpaceInvaders
        labelSpaceInvaders();

        //panel
        panel();

        buttonPanel.add(startButton);
        buttonPanel.add(labelSpaceInvaders);
        add(buttonPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void labelSpaceInvaders() {
        labelSpaceInvaders= new JLabel("Space Invaders");
        labelSpaceInvaders.setBackground(Color.green);
        labelSpaceInvaders.setForeground(Color.green);
        labelSpaceInvaders.setLayout(null);
        labelSpaceInvaders.setEnabled(true);
        Font auxFont=labelSpaceInvaders.getFont();
        labelSpaceInvaders.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 28));
        labelSpaceInvaders.setBounds(75, 90, 300, 30);
    }


    private void panel() {
        buttonPanel = new JPanel(true);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(0, 0, 0));
    }

    private void button() {
        startButton = new JButton("Start Game");
        startButton.setLayout(null);
        startButton.setBounds(120, 160, 100, 30);
//        startButton.setBackground(new Color(112, 106, 37));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active = false;
                setVisible(false);
            }
        });

    }

    boolean stillActive() {
        return active;
    }


}