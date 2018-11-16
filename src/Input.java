import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class Input {
    private static String hola;

    Input() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your name: ");
        if(reader.hasNextLine()){
            hola = reader.nextLine();
            System.out.println(hola);
            reader.close();
        }
    }

    String returnString()
    {
        return hola;
    }

    void changeString()
    {
        hola = null;
    }
}


