import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {

    static List<Score> ranking;

    static String filename = "Leaderboard.txt";

    static String playerid;
    static int playerscore;
    static int scoreSize = 5;

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
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isHigher(Score score) {
        if((ranking.size())>0 ) {
            Score last = ranking.get(ranking.size() - 1);
            int number = last.getPoints();
            return (score.getPoints() > number);
        }
        return true;
    }

    public List<Score> getRanking(){
        return ranking;
    }


    public static void addScore(Score score){
        if (isHigher(score)){

            ranking.add(score);

            ranking.sort(new SortByRollNo());

        }

        save(ranking);
    }


    public static void save(List<Score> scores) {
        FileWriter file;
        BufferedWriter buffer;

        try
        {
            file = new FileWriter(filename);
            buffer = new BufferedWriter(file);

            for (int i = 0; i < ranking.size(); i++) {
                buffer.write(ranking.get(i).Serialize()+"\n");
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SortByRollNo implements Comparator<Score>
{
    public int compare(Score a, Score b)
    {
        return b.getPoints() - a.getPoints();
    }
}
