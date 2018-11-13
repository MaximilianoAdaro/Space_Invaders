import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AlienType {

    private int points;
    private String image;

    public AlienType(int points, String image) {
        this.points=points;
        this.image=image;
    }

    public String getImage(){
        return image;
    }

    public int getPoints() {
        return points;
    }
}
