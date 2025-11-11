package main.java.arkanoid.engine;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Map {
    public static int row;
    public static int column;
    public int[][] lv;
    /*
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

     */

    private int[][] lv1 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 2, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 3, 3, 3, 3, 3, 3, 3, 3, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 2, 2, 0, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

    } ;

    private int[][] lv5 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 2, 2, 2, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 2, 2, 4, 4, 4, 2, 2, 1, 0, 0, 0},
            {0, 0, 1, 2, 4, 4, 3, 3, 3, 4, 4, 2, 1, 0, 0},
            {0, 0, 2, 4, 3, 3, 3, 3, 3, 3, 3, 4, 2, 0, 0},
            {0, 2, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 2, 0},
            {0, 2, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 2, 0},
            {0, 0, 2, 4, 3, 3, 3, 3, 3, 3, 3, 4, 2, 0, 0},
            {0, 0, 1, 2, 4, 4, 3, 3, 3, 4, 4, 2, 1, 0, 0},
            {0, 0, 0, 1, 2, 2, 4, 4, 4, 2, 2, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 2, 2, 2, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

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

    private int[][] lv9 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {5, 5, 5, 5, 5, 5, 4, 4, 4, 5, 5, 5, 5, 5, 5},
            {5, 2, 2, 2, 2, 5, 4, 4, 4, 5, 3, 3, 3, 3, 5},
            {5, 2, 2, 2, 2, 5, 0, 0, 0, 5, 3, 3, 3, 3, 5},
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {5, 0, 0, 0, 0, 5, 1, 1, 1, 5, 0, 0, 0, 0, 5},
            {5, 0, 0, 0, 0, 5, 1, 1, 1, 5, 0, 0, 0, 0, 5},
            {5, 4, 4, 4, 4, 5, 5, 5, 5, 5, 4, 4, 4, 4, 5},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private int[][] lv10 = {
            {4, 0, 3, 0, 2, 0, 1, 0, 4, 0, 3, 0, 2, 0, 1},
            {5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5},
            {0, 4, 0, 3, 0, 2, 0, 1, 0, 4, 0, 3, 0, 2, 0},
            {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0},
            {1, 0, 4, 0, 3, 0, 2, 0, 1, 0, 4, 3, 2, 0, 1},
            {5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5},
            {0, 1, 0, 4, 0, 3, 0, 2, 0, 1, 0, 4, 0, 3, 0},
            {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0},
            {2, 0, 1, 0, 4, 0, 3, 0, 2, 0, 1, 0, 4, 0, 3},
            {5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5},
            {0, 2, 0, 1, 0, 4, 0, 3, 0, 2, 0, 1, 0, 4, 0},
            {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };


    //            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},




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
        if (type_of_map == 5) {
            this.row = 15;
            this.column = 15;
            this.lv = lv5;
        }


        if (type_of_map == 10) {
            this.row = 15;
            this.column = 15;
            this.lv = lv10;

        }



        if (type_of_map == 9) {
            this.row = 15;
            this.column = 15;
            this.lv = lv9;
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
                        Bricks GreenBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "main/resources/com/Arkanoid/bricks.png",
                                112, 16,
                                screenWidth, screenHeight,
                                row, column, 2
                        );
                        brickGroup.add(GreenBrick);
                        GreenBrick.setPoint_given(50);

                        break;
                    case 2:
                        Bricks YellowBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "main/resources/com/Arkanoid/bricks.png",
                                112, 32,
                                screenWidth, screenHeight,
                                row, column, 3
                        );
                        brickGroup.add(YellowBrick);
                        YellowBrick.setPoint_given(60);
                        break;
                    case 3:
                        Bricks OrangeBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "main/resources/com/Arkanoid/bricks.png",
                                112, 48,
                                screenWidth, screenHeight,
                                row, column, 4
                        );
                        brickGroup.add(OrangeBrick);
                        OrangeBrick.setPoint_given(70);
                        break;
                    case 4:
                        Bricks RedBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "main/resources/com/Arkanoid/bricks.png",
                                112, 64,
                                screenWidth, screenHeight,
                                row, column, 6
                        );
                        brickGroup.add(RedBrick);
                        RedBrick.setPoint_given(80);
                        break;
                    case 9:
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
                    case 5:
                        Bricks HardBrick = new Bricks(
                                i * screenWidth / column, // X position
                                t * screenWidth / column/ 2,   // Y position
                                "main/resources/com/Arkanoid/bricks.png",
                                112, 64+16,
                                screenWidth, screenHeight,
                                row, column, Integer.MAX_VALUE
                        );
                        brickGroup.add(HardBrick);
                        HardBrick.setPoint_given(80);
                        break;




                }
            }
        }
    }

}
