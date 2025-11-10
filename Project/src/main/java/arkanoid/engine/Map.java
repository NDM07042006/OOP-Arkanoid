package main.java.arkanoid.engine;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Map {
    public static int row;
    public static int column;
    public int[][] lv;
    private int[][] lv1 = {
            {1, 0, 1, 1, 1, 1, 0, 0, 0, 1},
            {0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 2, 2, 0, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

    } ;
    private int[][] lv2 = {
            {1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 2},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2},
            {0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

    } ;


    public static ArrayList<Bricks> brickGroup = new ArrayList<>();
    private Scene secne;




    public Map(int type_of_map) {
        if (type_of_map == 1) {
            this.row = 10;
            this.column = 10;
            this.lv = lv1;
        }
        if (type_of_map == 2) {
            this.row = 15;
            this.column = 15;
            this.lv = lv2;
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
                switch (lv[t][i]) {
                    case 0:
                        break;
                    case 1:
                        Bricks normalBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "main/resources/com/Arkanoid/bricks.png",
                                112, 0,
                                screenWidth, screenHeight,
                                row, column, 2
                        );
                        brickGroup.add(normalBrick);
                        normalBrick.setPoint_given(50);

                        break;
                    case 2:
                        Bricks hardBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "main/resources/com/Arkanoid/bricks.png",
                                112, 16,
                                screenWidth, screenHeight,
                                row, column, Integer.MAX_VALUE
                        );
                        brickGroup.add(hardBrick);
                        break;
                    case 3:
                        Bricks PowerUpBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "main/resources/com/Arkanoid/bricks.png",
                                112, 32,
                                screenWidth, screenHeight,
                                row, column, 2
                        );
                        PowerUpBrick.setGivePowerUp(true);
                        PowerUpBrick.setPowerUp_Type(1);
                        PowerUpBrick.setPoint_given(100);
                        brickGroup.add(PowerUpBrick);



                }
            }
        }
    }

}
