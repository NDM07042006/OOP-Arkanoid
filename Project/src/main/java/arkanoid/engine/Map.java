package main.java.arkanoid.engine;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Map {
    public static int row;
    public static int column;
    private int[][] lvEasy = {
            {1, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

    } ;

    private  int[][] lv;
    public static ArrayList<Bricks> brickGroup = new ArrayList<>();
    private Scene secne;




    public Map(int type_of_map) {
        if (type_of_map == 0) {
            this.row = 10;
            this.column = 10;
        }
    }

    public void setSecne(Scene scene) {
        this.secne = scene;
    }

    public ArrayList<Bricks> getBrickGroup() {
        return brickGroup;
    }




    public void loadMap(int screenWidth, int screenHeight) {
        for (int t = 0; t < row; t++) {
            for (int i = 0; i < column; i++) {
                switch (lvEasy[t][i]) {
                    case 0:
                        break;
                    case 1:
                        Bricks normalBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "/com/Arkanoid/img/bricks.png",
                                112, 0,
                                screenWidth, screenHeight,
                                row, column
                        );
                        brickGroup.add(normalBrick);
                        break;
                    case 2:
                        Bricks hardBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "/com/Arkanoid/img/bricks.png",
                                112, 16,
                                screenWidth, screenHeight,
                                row, column
                        );
                        brickGroup.add(hardBrick);
                        break;

                }
            }
        }
    }

}
