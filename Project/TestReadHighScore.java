import java.io.*;
import java.util.*;

class HighScoreEntry implements Serializable, Comparable<HighScoreEntry> {
    private static final long serialVersionUID = 1L;
    private String playerName;
    private int score;
    
    public HighScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public int getScore() {
        return score;
    }
    
    @Override
    public int compareTo(HighScoreEntry other) {
        return Integer.compare(other.score, this.score);
    }
}

public class TestReadHighScore {
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/Data/highScore.dat";
        System.out.println("📁 Reading from: " + filePath);
        System.out.println("📁 File exists: " + new File(filePath).exists());
        System.out.println("📁 File size: " + new File(filePath).length() + " bytes");
        System.out.println();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            @SuppressWarnings("unchecked")
            List<HighScoreEntry> entries = (List<HighScoreEntry>) ois.readObject();
            
            System.out.println("✅ Successfully loaded " + entries.size() + " high scores!\n");
            System.out.println("🏆 HIGH SCORES:\n");
            
            for (int i = 0; i < entries.size(); i++) {
                HighScoreEntry e = entries.get(i);
                System.out.printf("#%-2d  %-15s %,10d pts\n", i+1, e.getPlayerName(), e.getScore());
            }
        } catch (Exception e) {
            System.err.println("❌ Error reading high scores:");
            e.printStackTrace();
        }
    }
}
