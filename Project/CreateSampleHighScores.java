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

public class CreateSampleHighScores {
    public static void main(String[] args) {
        try {
            List<HighScoreEntry> entries = new ArrayList<>();
            entries.add(new HighScoreEntry("ALICE", 5000));
            entries.add(new HighScoreEntry("BOB", 3500));
            entries.add(new HighScoreEntry("CHARLIE", 2800));
            entries.add(new HighScoreEntry("DIANA", 2100));
            entries.add(new HighScoreEntry("EVE", 1500));
            entries.add(new HighScoreEntry("FRANK", 1000));
            entries.add(new HighScoreEntry("GRACE", 750));
            entries.add(new HighScoreEntry("HENRY", 500));
            entries.add(new HighScoreEntry("IVY", 250));
            entries.add(new HighScoreEntry("JACK", 100));
            
            String filePath = System.getProperty("user.dir") + "/Data/highScore.dat";
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(entries);
                System.out.println("✅ Sample high scores created successfully!");
                System.out.println("📁 File: " + filePath);
                System.out.println("\n�� High Scores:");
                for (int i = 0; i < entries.size(); i++) {
                    HighScoreEntry e = entries.get(i);
                    System.out.printf("#%d  %-15s %,10d pts\n", i+1, e.getPlayerName(), e.getScore());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
