package main.java.arkanoid.engine;

import javafx.scene.Scene;

public class Map {
    private int row;
    private int column;
    private int[][] lv;
    private Bricks[] brickGroup[];
    private Scene secne;

    public Map(int row, int column) {
        this.row = row;
        this.column = column;

        this.lv = new int[row][column];
    }

    public void setSecne(Scene scene) {
        this.secne = scene;
    }



    public void drawMap() {
        for (int t = 0; t < row; t ++) {
            for (int i = 0; i < column; i++) {
                switch(lv[t][i]) {
                    case 0: break;
                    case 1: Bricks brick = new Bricks(t*50,i*25,"/Picture/bricks.png", 112, 0);
                        brick.setSence(secne);
                }
            }
        }
    }
}
