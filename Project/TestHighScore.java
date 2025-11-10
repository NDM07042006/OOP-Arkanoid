import java.io.*;
import java.util.*;

public class TestHighScore {
    public static void main(String[] args) {
        try {
            List<Integer> testScores = Arrays.asList(5000, 3500, 2800, 2100, 1500, 1000, 750, 500, 250, 100);
            String filePath = System.getProperty("user.dir") + "/Data/highScore.dat";
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(testScores);
                System.out.println("Test high scores saved to: " + filePath);
                System.out.println("Scores: " + testScores);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
