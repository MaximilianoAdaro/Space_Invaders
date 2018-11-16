import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishingPanel extends JFrame implements Commons{

    private JPanel panel;
    private JButton buttonPlayAgain;
    private JButton buttonSetText;
    private JTextField textName;
    private JLabel labelName;
    private String name;
    private boolean active=true;

    public FinishingPanel(Board board){

        setTitle("Statics");
        setEnabled(true);

        //button
        buttonPlayAgain();
        buttonSetText();


        //textField
        textField();

        //label
        label();

        //panel
        panel();
        panel.add(buttonPlayAgain);
        panel.add(buttonSetText);
        panel.add(textName);
        panel.add(labelName);

        //frame
        setBounds(587, 230, BOARD_WIDTH, BOARD_HEIGHT);
        add(panel);


        setVisible(true);
    }

    private void buttonSetText() {
        buttonSetText= new JButton("Yes, that's my name!");
        buttonSetText.setLayout(null);
        buttonSetText.setEnabled(true);
        buttonSetText.setForeground(Color.black);
        buttonSetText.setBounds(100, 240, 150, 30);
        buttonSetText.setBackground(Color.white);
        buttonSetText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPlayAgain.setEnabled(true);

                name=textName.getText();
                System.out.println(name);
            }
        });
        buttonSetText.setVisible(true);

    }

    private void label() {
        labelName= new JLabel("Enter your name: ");
        labelName.setLayout(null);
        labelName.setForeground(Color.white);
        labelName.setBounds(10,200,100,30);
    }

    private void textField() {
        textName= new JTextField();
        textName.setLayout(null);
        textName.setBounds(130, 200, 100, 30);
    }

    private void panel() {
        panel = new JPanel(true);
        panel.setLayout(null);
        panel.setEnabled(true);
        panel.setBackground(Color.black);

    }

    public void buttonPlayAgain(){
        buttonPlayAgain= new JButton("Play Again");
        buttonPlayAgain.setLayout(null);
        buttonPlayAgain.setEnabled(false);
//        buttonPlayAgain.setBackground(new Color(112, 106, 37));
        buttonPlayAgain.setForeground(Color.black);
        buttonPlayAgain.setBounds(120, 90, 100, 30);
        buttonPlayAgain.setBackground(Color.white);
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
        buttonPlayAgain.setVisible(true);

    }



    boolean isStillActive() {
        return active;
    }

    public String getName() {
        return name;
    }
}
