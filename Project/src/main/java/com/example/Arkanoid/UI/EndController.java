package main.java.com.example.Arkanoid.UI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;
import main.java.com.example.Arkanoid.Data.Score;
import main.java.com.example.Arkanoid.Utlis.SceneNavigator;
import main.java.com.example.Arkanoid.Utlis.SoundManager;

public class EndController {
    public Button restartButton;
    private static GameController gameController; // tham chiếu tới game hiện tại
    private Score scoreData; // Score object từ game
    private boolean isHighScore = false;

    public static void setGameController(GameController game) {
        gameController = game;
        System.out.println("d");

    }
    
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
    private VBox highScoreForm;
    
    @FXML
    private VBox scoreDisplay;
    
    @FXML
    private TextField playerNameField;
    
    @FXML
    private Label newScoreLabel;
    
    @FXML
    private Button submitButton;

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
     * Set Score object từ game
     */
    public void setScore(Score score) {
        this.scoreData = score;
        this.isHighScore = score.isHighScore();
        
        // Hiển thị form nhập tên nếu đạt high score
        if (isHighScore) {
            showHighScoreForm();
        }
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
        if (newScoreLabel != null) {
            newScoreLabel.setText("Your Score: " + score);
        }
    }
    
    /**
     * Hiển thị form nhập tên khi đạt high score
     */
    private void showHighScoreForm() {
        if (highScoreForm != null && scoreDisplay != null) {
            highScoreForm.setVisible(true);
            highScoreForm.setManaged(true);
            scoreDisplay.setVisible(false);
            scoreDisplay.setManaged(false);
            
            // Focus vào text field
            javafx.application.Platform.runLater(() -> {
                if (playerNameField != null) {
                    playerNameField.requestFocus();
                }
            });
            
            System.out.println("✅ Showing high score form");
        }
    }
    
    /**
     * Submit high score với tên người chơi
     */
    @FXML
    public void submitHighScore() {
        if (scoreData != null && playerNameField != null) {
            String playerName = playerNameField.getText().trim();
            
            // Validate tên
            if (playerName.isEmpty()) {
                playerName = "Anonymous";
            }
            
            // Giới hạn độ dài tên
            if (playerName.length() > 15) {
                playerName = playerName.substring(0, 15);
            }
            
            // Lưu high score
            scoreData.saveCurrentScore(playerName);
            
            // Play sound
            new Thread(() -> SoundManager.getInstance().playButtonClick()).start();
            
            // Ẩn form và hiển thị score display
            highScoreForm.setVisible(false);
            highScoreForm.setManaged(false);
            scoreDisplay.setVisible(true);
            scoreDisplay.setManaged(true);
            
            // Cập nhật high score label
            if (highScoreLabel != null) {
                highScoreLabel.setText("High Score: " + scoreData.getTopHighScore());
            }
            
            System.out.println("✅ High score saved: " + playerName + " - " + scoreData.getScore());
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
    
    /**
     * Hover sound effect cho buttons
     */
    @FXML
    public void onButtonHover() {
        new Thread(() -> SoundManager.getInstance().playButtonHover()).start();
    }

    @FXML
    public void Restart() {
        new Thread(() -> SoundManager.getInstance().playButtonClick()).start();
        gameController.closeLevel();
        javafx.application.Platform.runLater(() -> {
            SceneNavigator.goToGame(stage, gameController.getLevel());
        });

    }

    @FXML
    public void Menu() {
        new Thread(() -> SoundManager.getInstance().playButtonClick()).start();
        try {
            gameController.closeLevel();
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
        new Thread(() -> SoundManager.getInstance().playButtonClick()).start();
        gameController.closeLevel();
        javafx.application.Platform.runLater(() -> {
            if (gameController.getLevel() < 10)
            SceneNavigator.goToGame(stage, gameController.getLevel()+1);
        });


    }
}