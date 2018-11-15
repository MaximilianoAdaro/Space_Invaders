import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input {

    static String hola = null;

    public Input()
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your name: ");
        if(reader.hasNextLine()){
            String s = reader.nextLine();
            hola = s;
            System.out.println(hola);
            reader.close();
        }
    }


    public String returnString()
    {
        return hola;
    }

    public void changeString()
    {
        hola = null;
    }
}


