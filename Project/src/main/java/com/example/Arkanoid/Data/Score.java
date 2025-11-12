package main.java.com.example.Arkanoid.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.control.TextInputDialog;
import main.java.arkanoid.engine.*;

public class Score {
    private int score;
    private List<HighScoreEntry> highScoreEntries = new ArrayList<>();
    private static final String FILE_PATH = System.getProperty("user.dir") + "/Data/highScore.dat";
    private static final int MAX_HIGH_SCORES = 10; // Top 10 high scores

    public Score() {
        score = 0;
        highScoreEntries = loadHighScore();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int x) {
        this.score = x;
    }

    public List<HighScoreEntry> getHighScoreEntries() {
        return highScoreEntries;
    }

    public void addScore(int points) {
        score += points;
    }

    // Gọi method này khi game kết thúc
    public void saveCurrentScore(String playerName) {
        checkAndSaveHighScore(playerName);
    }

    // Kiểm tra xem điểm hiện tại có đủ vào top high scores không
    public boolean isHighScore() {
        if (highScoreEntries.isEmpty() || highScoreEntries.size() < MAX_HIGH_SCORES) {
            return score > 0; // Có điểm và chưa đủ 10 entries
        }
        // Kiểm tra xem điểm có cao hơn điểm thấp nhất trong top 10 không
        return score > highScoreEntries.get(highScoreEntries.size() - 1).getScore();
    }

    private void checkAndSaveHighScore(String playerName) {
        if (isHighScore()) {
            HighScoreEntry newEntry = new HighScoreEntry(playerName, score);
            highScoreEntries.add(newEntry);

            // Sort giảm dần (điểm cao nhất trước)
            Collections.sort(highScoreEntries);

            // Giới hạn chỉ giữ top MAX_HIGH_SCORES
            if (highScoreEntries.size() > MAX_HIGH_SCORES) {
                highScoreEntries = new ArrayList<>(highScoreEntries.subList(0, MAX_HIGH_SCORES));
            }

            saveHighScore(highScoreEntries);
            System.out.println("High score added: " + newEntry);
        }
    }

    private void saveHighScore(List<HighScoreEntry> entries) {
        try {
            // Tạo thư mục Data nếu chưa tồn tại
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            
            System.out.println("💾 Saving high scores to: " + FILE_PATH);
            System.out.println("💾 Saving " + entries.size() + " entries:");
            for (HighScoreEntry entry : entries) {
                System.out.println("   - " + entry.getPlayerName() + ": " + entry.getScore());
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(entries);
                System.out.println("✅ High scores saved successfully!");
            }
        } catch (Exception e) {
            System.err.println("❌ Error saving high scores:");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<HighScoreEntry> loadHighScore() {
        System.out.println("📂 Loading high scores from: " + FILE_PATH);
        System.out.println("📂 user.dir = " + System.getProperty("user.dir"));
        
        File file = new File(FILE_PATH);
        System.out.println("📂 File exists: " + file.exists());
        System.out.println("📂 File absolute path: " + file.getAbsolutePath());
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            List<HighScoreEntry> loaded = (List<HighScoreEntry>) ois.readObject();
            System.out.println("✅ High scores loaded: " + loaded.size() + " entries");
            for (HighScoreEntry entry : loaded) {
                System.out.println("   - " + entry.getPlayerName() + ": " + entry.getScore());
            }
            return loaded;
        } catch (Exception e) {
            System.out.println("⚠️ No existing high scores found, starting fresh.");
            System.out.println("⚠️ Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Method để lấy high score cao nhất
    public int getTopHighScore() {
        if (highScoreEntries.isEmpty()) {
            return 0;
        }
        return highScoreEntries.get(0).getScore();
    }

    // Method để reset điểm hiện tại (khi bắt đầu game mới)
    public void resetScore() {
        score = 0;
    }

    // Method để prompt người chơi nhập tên
    public static String promptForPlayerName() {
        TextInputDialog dialog = new TextInputDialog("Player");
        dialog.setTitle("New High Score!");
        dialog.setHeaderText("Congratulations! You achieved a high score!");
        dialog.setContentText("Enter your name:");

        return dialog.showAndWait().orElse("Anonymous");
    }
}
