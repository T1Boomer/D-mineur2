package com.example.dmineur2;

public class Values {

    public static double SIZE_CASE;
    public static double WIDTH_WINDOW;
    public static double HEIGHT_WINDOW;
    public static double WIDTH_GRID;
    public static double HEIGHT_GRID;
    public static int NUMBER_OF_COLUMNS;
    public static int NUMBER_OF_ROWS;
    public static int NUMBER_OF_BOMBS;
    public static int NUMBER_OF_BOXES;
    public static int NUMBER_OF_FLAGS;
    public static boolean GAME_ENDING;
    public static boolean GAME_START;
    public static int NUMBER_OF_BOXES_FOUND;

    public enum Difficulty {
        EASY,MEDIUM,HARD
    }

    public static void setValues(Difficulty difficulty){
       switch (difficulty){

           case EASY:
               SIZE_CASE = 30;
               WIDTH_GRID = 300;
               HEIGHT_GRID = 300;
               NUMBER_OF_BOMBS = 10;
               NUMBER_OF_FLAGS = 10;
              break;

           case MEDIUM:
               SIZE_CASE = 20;
               WIDTH_GRID = 400;
               HEIGHT_GRID = 400;
               NUMBER_OF_BOMBS = 50;
               NUMBER_OF_FLAGS = 50;
               break;

           case HARD:
               SIZE_CASE = 20;
               WIDTH_GRID = 500;
               HEIGHT_GRID = 500;
               NUMBER_OF_BOMBS = 100;
               NUMBER_OF_FLAGS = 100;
               break;
       }
        NUMBER_OF_COLUMNS = (int) (WIDTH_GRID / SIZE_CASE);
        NUMBER_OF_ROWS = (int) (HEIGHT_GRID / SIZE_CASE);
        NUMBER_OF_BOXES = NUMBER_OF_COLUMNS * NUMBER_OF_ROWS;
        WIDTH_WINDOW = WIDTH_GRID + 100;
        HEIGHT_WINDOW = HEIGHT_GRID + 100;
        NUMBER_OF_BOXES_FOUND = 0;
        GAME_START = true;
        GAME_ENDING = false;
    }


}
