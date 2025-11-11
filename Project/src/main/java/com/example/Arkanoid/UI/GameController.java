package main.java.com.example.Arkanoid.UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.arkanoid.engine.Bricks;
import main.java.arkanoid.engine.Map;

public class GameController {
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private ImageView pauseButton;

    @FXML
    private Stage stage;
    
    private int levelNumber = 1; // Default level
    private ImageView backgroundView;
    private boolean needsBackgroundLoad = false; // Flag để track khi cần load background
    private Map currentMap; // Lưu map hiện tại

    @FXML
    public void initialize() {
        System.out.println("🔧 GameController.initialize() called");
        System.out.println("   - anchorPane: " + (anchorPane != null ? "OK" : "NULL"));
        System.out.println("   - needsBackgroundLoad: " + needsBackgroundLoad);
        System.out.println("   - levelNumber: " + levelNumber);
        
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
        System.out.println("   - anchorPane: " + (anchorPane != null ? "OK" : "NULL"));
        
        // Nếu anchorPane đã sẵn sàng, load ngay
        if (anchorPane != null) {
            System.out.println("   - Loading background immediately...");
            loadBackgroundForLevel(levelNumber);
            System.out.println("   - Loading map immediately...");
            loadMapForLevel(levelNumber);
        } else {
            // Nếu chưa, đợi initialize() gọi
            System.out.println("   - Waiting for initialize() to load background...");
            needsBackgroundLoad = true;
        }
    }
    
    private void loadMapForLevel(int level) {
        try {
            if (anchorPane == null) {
                System.err.println("⚠️ AnchorPane not initialized yet, skipping map load");
                return;
            }
            
            // Xóa map cũ nếu có
            if (currentMap != null && Map.brickGroup != null) {
                for (Bricks brick : Map.brickGroup) {
                    anchorPane.getChildren().remove(brick.getNode());
                }
                Map.brickGroup.clear();
            }
            
            // Tạo map mới dựa vào level number
            // Chỉ có level 1, 2, 5, 9, 10 có sẵn trong Map.java
            int mapType = level;
            if (level > 10) {
                mapType = 10; // Default về level 10 nếu quá
            } else if (level == 3 || level == 4 || level == 6 || level == 7 || level == 8) {
                mapType = 1; // Các level chưa có map thì dùng level 1
            }
            
            currentMap = new Map(mapType);
            
            // Load map với kích thước của anchorPane
            double width = anchorPane.getPrefWidth() > 0 ? anchorPane.getPrefWidth() : 600;
            double height = anchorPane.getPrefHeight() > 0 ? anchorPane.getPrefHeight() : 400;
            
            currentMap.loadMap((int)width, (int)height);
            
            // Thêm offset để dịch map xuống một chút
            int offsetY = 70;
            
            // Thêm các bricks vào anchorPane
            for (Bricks brick : Map.brickGroup) {
                brick.setPos_Y(brick.getPos_Y() + offsetY);
                anchorPane.getChildren().add(brick.getNode());
            }
            
            System.out.println("✅ GameController: Map loaded for Level " + level + " (using map type " + mapType + ")");
            System.out.println("   - Total bricks: " + Map.brickGroup.size());
            
        } catch (Exception e) {
            System.err.println("❌ Error loading map for level " + level);
            e.printStackTrace();
        }
    }
    
    private void loadBackgroundForLevel(int level) {
        try {
            // Kiểm tra anchorPane đã được khởi tạo chưa
            if (anchorPane == null) {
                System.err.println("⚠️ AnchorPane not initialized yet, skipping background load");
                return;
            }
            
            // Sử dụng SpriteLoader để load background
            // Ban đầu dùng kích thước mặc định hoặc prefSize
            double initialWidth = anchorPane.getPrefWidth() > 0 ? anchorPane.getPrefWidth() : 600;
            double initialHeight = anchorPane.getPrefHeight() > 0 ? anchorPane.getPrefHeight() : 400;
            
            ImageView newBackground = SpriteLoader.getBackgroundForLevel(level, initialWidth, initialHeight);
            
            // Xóa background cũ nếu có
            if (backgroundView != null) {
                anchorPane.getChildren().remove(backgroundView);
            }
            
            // Bind kích thước background với AnchorPane
            newBackground.fitWidthProperty().bind(anchorPane.widthProperty());
            newBackground.fitHeightProperty().bind(anchorPane.heightProperty());
            
            // Set vị trí background ở góc trên bên trái
            AnchorPane.setTopAnchor(newBackground, 0.0);
            AnchorPane.setLeftAnchor(newBackground, 0.0);
            AnchorPane.setRightAnchor(newBackground, 0.0);
            AnchorPane.setBottomAnchor(newBackground, 0.0);
            
            // Thêm background mới ở vị trí đầu tiên (phía sau tất cả)
            backgroundView = newBackground;
            anchorPane.getChildren().add(0, backgroundView);
            
            System.out.println("✅ GameController: Background loaded and bound to AnchorPane for Level " + level);
            System.out.println("   - AnchorPane size: " + anchorPane.getWidth() + "x" + anchorPane.getHeight());
            System.out.println("   - AnchorPane prefSize: " + anchorPane.getPrefWidth() + "x" + anchorPane.getPrefHeight());
            
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
        // Tạo một stage mới cho pause
        Stage pauseStage = new Stage();
        pauseStage.initStyle(javafx.stage.StageStyle.TRANSPARENT); // Phải set TRƯỚC initOwner
        pauseStage.initOwner(stage);
        pauseStage.initModality(Modality.WINDOW_MODAL);
        
        PauseScene pauseScene = new PauseScene(pauseStage);
        pauseScene.show();
    }
}
