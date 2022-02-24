import java.io.FileWriter;
import java.io.IOException;

public class puzzle {
    public static void main(String[] args) {




        try {
            FileWriter outputWriter = new FileWriter("output.txt");
            outputWriter.write("solution");
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
