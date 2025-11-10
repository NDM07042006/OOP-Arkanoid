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

public class GenerateTestData {
    public static void main(String[] args) {
        try {
            List<HighScoreEntry> entries = new ArrayList<>();
            
            // Tạo 10 high scores mẫu với tên thú vị
            entries.add(new HighScoreEntry("MRNAM", 9999));
            entries.add(new HighScoreEntry("DRAGON", 8500));
            entries.add(new HighScoreEntry("PHOENIX", 7200));
            entries.add(new HighScoreEntry("TIGER", 6100));
            entries.add(new HighScoreEntry("EAGLE", 5000));
            entries.add(new HighScoreEntry("SHARK", 4200));
            entries.add(new HighScoreEntry("WOLF", 3500));
            entries.add(new HighScoreEntry("BEAR", 2800));
            entries.add(new HighScoreEntry("LION", 1900));
            entries.add(new HighScoreEntry("HAWK", 1000));
            
            String filePath = System.getProperty("user.dir") + "/Data/highScore.dat";
            
            // Tạo thư mục nếu chưa tồn tại
            new File(filePath).getParentFile().mkdirs();
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(entries);
                System.out.println("✅ TEST HIGH SCORES CREATED SUCCESSFULLY!");
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("📁 File: " + filePath);
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("\n🏆 HIGH SCORE LEADERBOARD:\n");
                for (int i = 0; i < entries.size(); i++) {
                    HighScoreEntry e = entries.get(i);
                    String medal = "";
                    if (i == 0) medal = "🥇";
                    else if (i == 1) medal = "🥈";
                    else if (i == 2) medal = "🥉";
                    else medal = "  ";
                    
                    System.out.printf("%s #%-2d  %-15s %,10d pts\n", 
                        medal, i+1, e.getPlayerName(), e.getScore());
                }
                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("✨ Run the game and check High Scores! ✨");
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            }
        } catch (Exception e) {
            System.err.println("❌ Error creating test data:");
            e.printStackTrace();
        }
    }
}
