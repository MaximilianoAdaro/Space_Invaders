import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {

    List<Score> ranking;

    String filename = "C:\\Users\\Numa\\Desktop\\Space_Invaders\\Leaderboard.txt";


    String playerid;
    int playerscore;
    int scoreSize = 5;

    public Leaderboard(){
        ranking = new ArrayList<>(scoreSize);

        BufferedReader reader;
        FileReader file;


        try{
            file = new FileReader(filename);
            reader = new BufferedReader(file);
            String line;

            while((line = reader.readLine() ) != null)
            {
                String[] parts = line.split(":");

                String name = parts[0];
                int points = Integer.parseInt(parts [1]);

                Score score = new Score(name,points);

                ranking.add(score);
            }
            ranking.sort(new SortByRollNo());

            reader.close();
        }




        catch (FileNotFoundException e) {
            System.out.println("Couldn't find file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isHigher(Score score)
    {
        Score last = ranking.get(ranking.size()-1);
        int number = last.getPoints();
        return (score.getPoints()> number);
    }

    public List<Score> getRanking(){
        return ranking;
    }


    public void addScore(Score score){
        if (isHigher(score)){

            ranking.add(ranking.size()-1,score);

            ranking.sort(new SortByRollNo());

        }

        save(ranking);
    }


    public void save(List<Score> scores) {
        FileWriter file;
        BufferedWriter buffer;

        try
        {
            file = new FileWriter(filename);
            buffer = new BufferedWriter(file);

            for (int i = 0; i < ranking.size(); i++) {
                buffer.write(ranking.get(i).Serialize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

class SortByRollNo implements Comparator<Score> {

    public int compare(Score a, Score b) {
        return b.getPoints() - a.getPoints();
    }
}
