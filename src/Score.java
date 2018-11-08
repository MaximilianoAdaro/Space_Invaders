import java.util.ArrayList;
import java.util.List;

public class Score {
    String name;
    int points;
//    public static List<Score> ranking= new ArrayList<>();



    public Score(String name, int points) {

        this.name = name;
        this.points = points;
        //ranking.add(DesSerialize(Serialize()));
    }

    public String Serialize() {
        return name+":"+points;
    }

    public String getid() {
        return name;

    }

    public int getPoints(){
        return points;
    }

   // public static Score DesSerialize(String s){
       // return new Score(s.split(":")[0], Integer.parseInt(s.split(":")[1]));
    //}

//    public static List<Score> ranking(){
//        return ranking;
//    }
}
