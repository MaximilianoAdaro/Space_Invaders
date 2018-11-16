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

        //buttonPlayAgain
        button();

        //panel
        panel();

        buttonPanel.add(startButton);
        add(buttonPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);



//        -HighScore-
//
//        highScore = new JButton("View HighScores");
//        fieldsPanel = new JPanel();
//        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, 2));
//
//        fieldsPanel.add(highScore);
//        add(fieldsPanel, BorderLayout.PAGE_START);

    }

    private void panel() {
        buttonPanel = new JPanel(true);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(0, 0, 0));
    }

    private void button() {
        startButton = new JButton("Start Game");
        startButton.setLayout(null);
        startButton.setBounds(120, 90, 100, 30);
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