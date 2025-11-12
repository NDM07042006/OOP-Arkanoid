package main.java.com.example.Arkanoid.UI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;

public class EndController {
    public Button restartButton;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private ImageView backgroundImage;
    
    @FXML
    private Label scoreLabel;
    
    @FXML
    private Label highScoreLabel;
    
    @FXML
    private Label levelLabel;

    @FXML
    private Stage stage;
    
    private boolean isWin = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Set trạng thái thắng/thua và load background tương ứng
     * @param win true = thắng, false = thua
     */
    public void setWinStatus(boolean win) {
        this.isWin = win;
        loadBackground();
    }
    
    /**
     * Set thông tin game (score, highscore, level)
     */
    public void setGameInfo(int score, int highScore, int level) {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + score);
        }
        if (highScoreLabel != null) {
            highScoreLabel.setText("High Score: " + highScore);
        }
        if (levelLabel != null) {
            levelLabel.setText("Level: " + level);
        }
    }
    
    /**
     * Load background image dựa trên trạng thái thắng/thua
     */
    private void loadBackground() {
        try {
            String imagePath = isWin ? "/com/Arkanoid/images/you_win.gif" : "/com/Arkanoid/images/game_over.gif";
            Image bgImage = new Image(getClass().getResourceAsStream(imagePath));
            
            if (backgroundImage != null) {
                backgroundImage.setImage(bgImage);
                System.out.println("✅ Loaded " + (isWin ? "YOU WIN" : "GAME OVER") + " background");
            }
        } catch (Exception e) {
            System.err.println("❌ Cannot load background image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void Restart() {

    }

    @FXML
    public void Menu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Define.MENU_GAME_PATH));
            Parent root = loader.load();

            MenuController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Arkanoid Menu");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("khong the tai MenuGame.fxml");
        }
    }

    @FXML
    public void NextLevel() {

    }
}