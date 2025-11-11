package main.java.com.example.Arkanoid.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.arkanoid.engine.Define;

/**
 * SpriteLoader - Load background images cho từng level
 * Hỗ trợ nhiều format: .png, .jpg, .gif
 * Mỗi level có 1 file ảnh riêng: background_1.png/jpg/gif, background_2.png/jpg/gif, ...
 */
public class SpriteLoader {
    
    // Các định dạng file được hỗ trợ
    private static final String[] SUPPORTED_FORMATS = {".png", ".jpg", ".jpeg", ".gif"};
    
    /**
     * Lấy background cho level cụ thể (1-10)
     * Tự động tìm file với các format: png, jpg, jpeg, gif
     */
    public static ImageView getBackgroundForLevel(int level, double fitWidth, double fitHeight) {
        if (level < 1 || level > 10) {
            System.err.println("⚠️ Invalid level: " + level + ". Using level 1 instead.");
            level = 1;
        }
        
        try {
            // Tạo đường dẫn file: background_1.png, background_2.png, ...
            String basePath = Define.BACKGROUND_BASE_PATH + level;
            Image backgroundImage = null;
            String loadedPath = null;
            
            // Thử load với từng format
            for (String format : SUPPORTED_FORMATS) {
                String imagePath = basePath + format;
                try {
                    backgroundImage = new Image(imagePath);
                    
                    // Kiểm tra xem image có load thành công không
                    if (!backgroundImage.isError() && backgroundImage.getWidth() > 0) {
                        loadedPath = imagePath;
                        break;
                    }
                } catch (Exception e) {
                    // Thử format tiếp theo
                    continue;
                }
            }
            
            // Nếu không tìm thấy file nào, fallback về background_1
            if (backgroundImage == null || backgroundImage.isError()) {
                System.err.println("❌ No background found for level " + level + ", trying fallback...");
                
                for (String format : SUPPORTED_FORMATS) {
                    String fallbackPath = Define.BACKGROUND_BASE_PATH + "1" + format;
                    try {
                        backgroundImage = new Image(fallbackPath);
                        if (!backgroundImage.isError() && backgroundImage.getWidth() > 0) {
                            loadedPath = fallbackPath + " (fallback)";
                            break;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            
            // Nếu vẫn không load được, tạo placeholder trắng
            if (backgroundImage == null || backgroundImage.isError()) {
                System.err.println("❌ Failed to load any background image!");
                return new ImageView();
            }
            
            // Tạo ImageView
            ImageView imageView = new ImageView(backgroundImage);
            imageView.setPreserveRatio(false);
            imageView.setFitWidth(fitWidth);
            imageView.setFitHeight(fitHeight);
            
            System.out.println("✅ SpriteLoader: Loaded " + loadedPath + 
                             " (size: " + (int)backgroundImage.getWidth() + "x" + (int)backgroundImage.getHeight() + ")");
            
            return imageView;
            
        } catch (Exception e) {
            System.err.println("❌ Error loading background for level " + level + ": " + e.getMessage());
            e.printStackTrace();
            return new ImageView();
        }
    }
}