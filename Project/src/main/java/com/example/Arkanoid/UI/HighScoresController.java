package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.java.com.example.Arkanoid.Data.HighScoreEntry;
import main.java.com.example.Arkanoid.Data.Score;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

import java.util.List;

public class HighScoresController {

    @FXML
    private ListView<String> highScoreListView;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        // Reload high scores mỗi khi set stage (mở screen)
        if (highScoreListView != null) {
            loadHighScores();
        }
    }

    @FXML
    public void initialize() {
        loadHighScores();
    }

    private void loadHighScores() {
        System.out.println("🔍 HighScoresController: loadHighScores() called");

        // Tạo Score object để load high scores
        Score scoreData = new Score();
        List<HighScoreEntry> highScores = scoreData.getHighScoreEntries();

        System.out.println("🔍 Loaded " + highScores.size() + " high score entries");

        // Xóa dữ liệu cũ
        highScoreListView.getItems().clear();

        if (highScores.isEmpty()) {
            System.out.println("⚠️ No high scores found!");
            highScoreListView.getItems().add("No high scores yet!");
            highScoreListView.getItems().add("");
            highScoreListView.getItems().add("Be the first to set a record!");
        } else {
            System.out.println("✅ Displaying " + highScores.size() + " high scores:");
            // Hiển thị top scores với ranking, tên và điểm
            for (int i = 0; i < highScores.size(); i++) {
                HighScoreEntry entry = highScores.get(i);
                String rank = String.format("#%d", i + 1);
                String name = entry.getPlayerName();
                String score = String.format("%,d", entry.getScore());

                // Format: #1  PlayerName ........ 5,000
                String displayText = String.format("%-5s %-15s %10s pts", rank, name, score);
                highScoreListView.getItems().add(displayText);
                System.out.println("   " + displayText);
            }
        }

        // Style cho ListView items
        highScoreListView.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Monospaced'; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-color: #16213e;"
        );

        System.out.println("✅ High scores loaded successfully!");
    }

    @FXML
    public void backToMenu() {
        new Thread(() -> SoundManager.getInstance().playButtonClick()).start();
        // Quay lại menu scene trên cùng stage
        MenuScene menuScene = new MenuScene(stage);
        menuScene.show();
    }
}