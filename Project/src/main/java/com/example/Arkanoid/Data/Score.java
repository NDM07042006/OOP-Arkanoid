package main.java.com.example.Arkanoid.Data;

import main.java.arkanoid.engine.*;

public class Score {
    private int score;

    public Score() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int x) {
        this.score = x;
    }


    private Bricks bricks;

    public Bricks getBricks() {
        return bricks;
    }

    public void setBricks(Bricks bricks) {
        this.bricks = bricks;
    }

    public void updateScore() {
        if (bricks != null) {
            score += bricks.getPoint_given();
        }
    }
}
