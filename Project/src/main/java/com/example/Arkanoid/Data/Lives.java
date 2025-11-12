package main.java.com.example.Arkanoid.Data;

import java.util.ArrayList;
import java.util.List;

import main.java.arkanoid.engine.*;
import main.java.com.example.Arkanoid.UI.EndScene;

public class Lives {
    private int lives;
    private List<Ball> balls;

    public Lives() {
        lives = 3;
        balls = new ArrayList<>();
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }



    public void updateLives() {
        if (balls.isEmpty()) {
            lives--;
        }
    }
}