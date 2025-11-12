package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import main.java.com.example.Arkanoid.Data.Score;
import javafx.scene.Scene;

public class EndScene {
    private Stage stage;
    private boolean isWin;
    private Score scoreData;
    private int level;

    /**
     * Constructor cho EndScene với Score object và level
     * @param stage Stage hiện tại
     * @param win true = thắng (you_win), false = thua (game_over)
     * @param score Score object từ game
     * @param level Level hiện tại
     */
    public EndScene(Stage stage, boolean win, Score score, int level) {
        this.stage = stage;
        this.isWin = win;
        this.scoreData = score;
        this.level = level;
    }
    
    /**
     * Constructor cho EndScene với Score object
     * @param stage Stage hiện tại
     * @param win true = thắng (you_win), false = thua (game_over)
     * @param score Score object từ game
     */
    public EndScene(Stage stage, boolean win, Score score) {
        this(stage, win, score, 1);
    }
    
    /**
     * Constructor cho EndScene
     * @param stage Stage hiện tại
     * @param win true = thắng (you_win), false = thua (game_over)
     */
    public EndScene(Stage stage, boolean win) {
        this(stage, win, null);
    }
    
    // Constructor cũ để backward compatible
    public EndScene(Stage stage) {
        this(stage, false, null); // Mặc định là thua
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.END_SCREEN_PATH));
            Parent root = loader.load();

            EndController endController = loader.getController();
            endController.setStage(stage);
            endController.setWinStatus(isWin); // Set trạng thái thắng/thua
            
            // Truyền Score object nếu có
            if (scoreData != null) {
                endController.setScore(scoreData);
                endController.setGameInfo(
                    scoreData.getScore(), 
                    scoreData.getTopHighScore(), 
                    level
                );
                System.out.println("📊 EndScene - Score: " + scoreData.getScore() + ", HighScore: " + scoreData.getTopHighScore() + ", Level: " + level);
            }

            Scene scene = new Scene(root);
            stage.setTitle(isWin ? "Victory!" : "Game Over!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Khong the tai file EndScreen.fxml");
        }
    }
}
