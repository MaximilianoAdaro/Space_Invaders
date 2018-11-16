import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FinishingPanel extends JFrame implements Commons{

    private JLabel highscoreLabel;
    private Board board;
    private JPanel panel;
    private JPanel panelHighscore;
    private JButton buttonPlayAgain;
    private JButton buttonSetText;
    private JTextField textName;
    private JLabel labelName;
    private JLabel scoreLabel;
    private boolean active=true;

    private String name;
    private int score;
    private static List<String> ranking;
    private JLabel labelRankName;
    private JLabel labelRankPoint;
    private Leaderboard leaderboard;

    public FinishingPanel(Board board){

        this.board=board;
        score= board.getPlayer().getPoints();

        setTitle("Statics");
        setEnabled(true);

        //origino el ranking
        leaderboard=board.getLeaderboard();
//        setRanking();

        //button
        buttonPlayAgain();
        buttonSetText();

        //textField
        textField();

        //label
        label();
        score();

        //panel
        panel();
        panelHighscore();

        //panelHighscore
        panelHighscore.add(buttonPlayAgain);
        labelRanking();

        //panelName
//        panel.add(buttonPlayAgain);
        panel.add(buttonSetText);
        panel.add(textName);
        panel.add(labelName);
        panel.add(scoreLabel);

        //frame
        setBounds(587, 230, BOARD_WIDTH, BOARD_HEIGHT);
        add(panel);


        setVisible(true);
    }

    private void labelRanking() {
        for (int i = 0; i < ranking.size(); i++) {
            String rank= ranking.get(i);
            String[] ranked=rank.split(":");

            String nameRank=ranked[0];
            String pointsRank=ranked[1];

            labelRankName= new JLabel(nameRank);
            labelRankName.setLayout(null);
            labelRankName.setEnabled(true);
            labelRankName.setForeground(Color.white);
            labelRankName.setBounds(80, 50+20*i, 200, 30);
//            labelRankName.setHorizontalAlignment(SwingConstants.CENTER);

            labelRankPoint=new JLabel(pointsRank);
            labelRankPoint.setLayout(null);
            labelRankPoint.setEnabled(true);
            labelRankPoint.setForeground(Color.white);
            labelRankPoint.setBounds(240, 50+20*i, 200, 30);

            panelHighscore.add(labelRankName);
            panelHighscore.add(labelRankPoint);
        }
        highscoreLabel= new JLabel("Highscores");
        highscoreLabel.setBackground(Color.green);
        highscoreLabel.setForeground(Color.green);
        highscoreLabel.setLayout(null);
        highscoreLabel.setEnabled(true);
        Font auxFont=highscoreLabel.getFont();
        highscoreLabel.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 18));
        highscoreLabel.setBounds(120, 10, 150, 30);

        panelHighscore.add(highscoreLabel);
    }

    private void setRanking() {
        for (int i = 0; i < 10; i++) {
            ranking.add(Leaderboard.getRanking().get(i).Serialize());
        }
    }

    private void panelHighscore() {
        panelHighscore= new JPanel(true);
        panelHighscore.setLayout(null);
        panelHighscore.setEnabled(true);
        panelHighscore.setBackground(Color.black);
    }

    private void score() {
        scoreLabel= new JLabel("Your score is: "+board.getPlayer().getPoints()+" points");
        scoreLabel.setLayout(null);
        scoreLabel.setForeground(Color.green);
        scoreLabel.setBackground(Color.green);
        Font auxFont=scoreLabel.getFont();
        scoreLabel.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 18));
        scoreLabel.setBounds(60, 100, 230, 30);

    }

    private void buttonSetText() {
        buttonSetText= new JButton("Yes, that's my name!");
        buttonSetText.setLayout(null);
        buttonSetText.setEnabled(true);
        buttonSetText.setForeground(Color.black);
        buttonSetText.setBounds(100, 200, 150, 30);
        buttonSetText.setBackground(Color.white);
        buttonSetText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active = false;

                buttonPlayAgain.setEnabled(true);

                name=textName.getText();
//                System.out.println(name);

                buttonSetText.setEnabled(false);
                textName.setEnabled(false);

                panel.setVisible(false);

                add(panelHighscore);
            }
        });
        buttonSetText.setVisible(true);
    }

    private void label() {
        labelName= new JLabel("Enter your name: ");
        labelName.setLayout(null);
        labelName.setForeground(Color.green);
        labelName.setBackground(Color.green);
        labelName.setBounds(10,150,100,30);
    }

    private void textField() {
        textName= new JTextField();
        textName.setLayout(null);
        textName.setBounds(130, 150, 100, 30);
    }

    private void panel() {
        panel = new JPanel(true);
        panel.setLayout(null);
        panel.setEnabled(true);
        panel.setBackground(Color.black);

    }

    public void buttonPlayAgain() {
        buttonPlayAgain = new JButton("Play Again");
        buttonPlayAgain.setLayout(null);
        buttonPlayAgain.setEnabled(false);
//        buttonPlayAgain.setBackground(new Color(112, 106, 37));
        buttonPlayAgain.setForeground(Color.black);
        buttonPlayAgain.setBounds(120, 270, 100, 30);
        buttonPlayAgain.setBackground(Color.white);
        buttonPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

                SpaceInvaders spaceInvaders = new SpaceInvaders();
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

    public int getScore() {
        return score;
    }

    public static void setRanking(List<String> ranking) {
        FinishingPanel.ranking = ranking;
    }
}
