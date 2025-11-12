package main.java.com.example.Arkanoid.Utlis;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.java.arkanoid.engine.Define;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * SceneCache - Preload và cache scenes để tăng tốc độ chuyển scene
 */
public class SceneCache {
    private static SceneCache instance;
    private Map<String, Scene> sceneCache = new HashMap<>();
    private Map<String, Object> controllerCache = new HashMap<>();

    private SceneCache() {}

    public static SceneCache getInstance() {
        if (instance == null) {
            instance = new SceneCache();
        }
        return instance;
    }

    /**
     * Preload một scene trong background
     */
    public CompletableFuture<Void> preloadScene(String fxmlPath, String sceneName) {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("🔄 Preloading scene: " + sceneName);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = loader.load();
                Object controller = loader.getController();

                Platform.runLater(() -> {
                    Scene scene = new Scene(root);
                    sceneCache.put(sceneName, scene);
                    controllerCache.put(sceneName, controller);
                    System.out.println("✅ Preloaded: " + sceneName);
                });
            } catch (Exception e) {
                System.err.println("❌ Failed to preload " + sceneName + ": " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    /**
     * Preload nhiều scenes cùng lúc
     */
    public void preloadAll() {
        System.out.println("🚀 Starting scene preload...");

        // Preload MenuScene trước (quan trọng!)
        preloadScene(Define.MENU_GAME_PATH, "menu");

        // Preload các scene nhẹ
        preloadScene(Define.LEVELS_PATH, "levels");
        preloadScene(Define.HIGH_SCORES_PATH, "highscores");

        // Preload GameScene - quan trọng nhất!
        preloadScene(Define.GAME_SCENE_PATH, "game");

        System.out.println("✅ Scene preload initiated");
    }

    /**
     * Lấy scene đã cache (instant)
     */
    public Scene getCachedScene(String sceneName) {
        return sceneCache.get(sceneName);
    }

    /**
     * Lấy controller đã cache
     */
    public Object getCachedController(String sceneName) {
        return controllerCache.get(sceneName);
    }

    /**
     * Kiểm tra scene đã được cache chưa
     */
    public boolean isCached(String sceneName) {
        return sceneCache.containsKey(sceneName);
    }

    /**
     * Xóa cache của một scene
     */
    public void clearCache(String sceneName) {
        sceneCache.remove(sceneName);
        controllerCache.remove(sceneName);
    }

    /**
     * Xóa toàn bộ cache
     */
    public void clearAll() {
        sceneCache.clear();
        controllerCache.clear();
    }

    /**
     * Load hoặc lấy từ cache
     */
    public CompletableFuture<Scene> getOrLoadScene(String fxmlPath, String sceneName) {
        return CompletableFuture.supplyAsync(() -> {
            if (isCached(sceneName)) {
                System.out.println("⚡ Using cached scene: " + sceneName);
                return getCachedScene(sceneName);
            } else {
                System.out.println("🔄 Loading scene: " + sceneName);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                    Parent root = loader.load();
                    Object controller = loader.getController();

                    Scene scene = new Scene(root);
                    sceneCache.put(sceneName, scene);
                    controllerCache.put(sceneName, controller);

                    return scene;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }
}