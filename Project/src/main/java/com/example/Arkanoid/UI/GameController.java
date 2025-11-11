package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Define;

public class GameController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Stage stage;

    private int levelNumber = 1; // Default level
    private ImageView backgroundView;
    private boolean needsBackgroundLoad = false; // Flag để track khi cần load background

    @FXML
    public void initialize() {
        // FXML components đã được inject, anchorPane sẵn sàng
        // Nếu level đã được set trước, load background ngay
        if (needsBackgroundLoad && anchorPane != null) {
            loadBackgroundForLevel(levelNumber);
            needsBackgroundLoad = false;
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLevel(int levelNumber) {
        this.levelNumber = levelNumber;
        System.out.println("GameController: Level set to " + levelNumber);

        // Nếu anchorPane đã sẵn sàng, load ngay
        if (anchorPane != null) {
            loadBackgroundForLevel(levelNumber);
        } else {
            // Nếu chưa, đợi initialize() gọi
            needsBackgroundLoad = true;
        }
    }

    private void loadBackgroundForLevel(int level) {
        try {
            // Kiểm tra anchorPane đã được khởi tạo chưa
            if (anchorPane == null) {
                System.err.println("⚠️ AnchorPane not initialized yet, skipping background load");
                return;
            }

            // Load sprite sheet
            Image spriteSheet = new Image(getClass().getResourceAsStream(Define.BACKGROUND));

            if (spriteSheet.isError()) {
                System.err.println("❌ Failed to load background sprite sheet");
                return;
            }

            // Tính toán kích thước của mỗi background trong sprite sheet
            // Giả sử 10 backgrounds được sắp xếp theo grid 2 cột x 5 hàng
            double totalWidth = spriteSheet.getWidth();
            double totalHeight = spriteSheet.getHeight();
            double bgWidth = totalWidth / 2;  // 2 cột
            double bgHeight = totalHeight / 5; // 5 hàng

            // Tính vị trí của background dựa trên level (1-10)
            int row = (level - 1) / 2;  // Hàng (0-4)
            int col = (level - 1) % 2;  // Cột (0-1)

            double x = col * bgWidth;
            double y = row * bgHeight;

            // Tạo viewport để crop phần background tương ứng
            Rectangle2D viewport = new Rectangle2D(x, y, bgWidth, bgHeight);

            // Đợi anchorPane có kích thước trước khi tạo ImageView
            double paneWidth = anchorPane.getWidth();
            double paneHeight = anchorPane.getHeight();

            // Nếu pane chưa có kích thước, dùng giá trị mặc định
            if (paneWidth <= 0) paneWidth = 800;  // Default width
            if (paneHeight <= 0) paneHeight = 600; // Default height

            // Tạo hoặc update ImageView
            if (backgroundView == null) {
                backgroundView = new ImageView(spriteSheet);
                backgroundView.setPreserveRatio(false);
                backgroundView.setFitWidth(paneWidth);
                backgroundView.setFitHeight(paneHeight);

                // Đặt background ở phía sau tất cả các node khác
                anchorPane.getChildren().add(0, backgroundView);
            } else {
                backgroundView.setImage(spriteSheet);
                backgroundView.setFitWidth(paneWidth);
                backgroundView.setFitHeight(paneHeight);
            }

            backgroundView.setViewport(viewport);

            System.out.println("✅ Loaded background for Level " + level +
                    " (viewport: x=" + x + ", y=" + y +
                    ", width=" + bgWidth + ", height=" + bgHeight + ")");

        } catch (Exception e) {
            System.err.println("❌ Error loading background for level " + level);
            e.printStackTrace();
        }
    }

    public int getLevel() {
        return levelNumber;
    }

    @FXML
    public void pause() {
        // Tạo một stage mới cho pause (giống như GameScene)
        Stage pauseStage = new Stage();
        pauseStage.initOwner(stage);
        pauseStage.initModality(Modality.WINDOW_MODAL);

        PauseScene pauseScene = new PauseScene(pauseStage);
        pauseScene.show();
    }
}