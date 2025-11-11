package main.java.arkanoid.engine.CheckCollision;

import main.java.arkanoid.engine.GameEngine;

public abstract class CheckCollision implements Runnable {
    protected static GameEngine gameEngine;
    /*
     * Set GameEngine để lấy dữ liệu check va chạm
     */
    public static void setCheckCollision(GameEngine engine) {
        gameEngine = engine;
        gameEngine.setCollisionTasks();
    }
    protected abstract void check();

}

