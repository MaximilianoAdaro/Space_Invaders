import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StarterPanel extends JFrame implements  Commons {

//    private JPanel fieldsPanel;
//    private JButton highScore;
    private JPanel buttonPanel;
    private JButton startButton;
    private boolean active=true;

    public StarterPanel(){

        setTitle("Space Invaders");
        setBounds(590, 230, BOARD_WIDTH, BOARD_HEIGHT);

        startButton = new JButton("Start Game");
        buttonPanel = new JPanel(true);

        startButton.setBounds(100, 100, 100, 100);

        buttonPanel.add(startButton);

        buttonPanel.setBackground(new Color(0, 0, 0));
        add(buttonPanel);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active = false;
                setVisible(false);
            }
        });

/*
        -HighScore-

        highScore = new JButton("View HighScores");
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, 2));

        fieldsPanel.add(highScore);
        add(fieldsPanel, BorderLayout.PAGE_START);
*/

    }

    boolean stillActive() {
        return active;
    }


}