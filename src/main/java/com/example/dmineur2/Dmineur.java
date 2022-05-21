package com.example.dmineur2;

import java.util.ArrayList;
import java.util.Random;

public class Dmineur {
    private ArrayList<Position> bombs;
    private char [][] grid;
    private boolean [][] reveal;


    public Dmineur() {
        this.bombs = new ArrayList<>();
        this.grid = new char[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        this.reveal = new boolean[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
//        setGrid();
//        setBombs();
//        setNumbers();
    }

    public void setBombs(){
        this.bombs = new ArrayList<>();
        int x = new Random().nextInt(Values.NUMBER_OF_COLUMNS);
        int y = new Random().nextInt(Values.NUMBER_OF_ROWS);
        for (int i = 0; i < Values.NUMBER_OF_BOMBS; i++) {
            while (grid[y][x] != '.'){
                x = new Random().nextInt(Values.NUMBER_OF_COLUMNS);
                y = new Random().nextInt(Values.NUMBER_OF_ROWS);
            }
            bombs.add(new Position(x, y));
            grid[y][x] = 'X';
        }
    }

    public void setNumbers(){
        int numberBombs;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (!estUneBombe(x,y)) {
                    numberBombs = 0;
                    //haut gauche
                    if (y != 0 && x != 0) {
                        if (estUneBombe(x - 1, y - 1)) {
                            numberBombs++;
                        }
                    }

                    //haut milieu
                    if (y != 0) {
                        if (estUneBombe(x, y - 1)) {
                            numberBombs++;
                        }
                    }


                    //haut droite
                    if (y != 0 && x != Values.NUMBER_OF_COLUMNS - 1) {
                        if (estUneBombe(x + 1, y - 1)) {
                            numberBombs++;
                        }
                    }

                    //milieu gauche
                    if (x != 0) {
                        if (estUneBombe(x - 1, y)) {
                            numberBombs++;
                        }
                    }

                    //milieu droite
                    if (x != Values.NUMBER_OF_COLUMNS - 1) {
                        if (estUneBombe(x + 1, y)) {
                            numberBombs++;
                        }
                    }

                    //bas gauche
                    if (y != Values.NUMBER_OF_ROWS - 1 && x != 0) {
                        if (estUneBombe(x - 1, y + 1)) {
                            numberBombs++;
                        }
                    }

                    //bas milieu
                    if (y != Values.NUMBER_OF_ROWS - 1) {
                        if (estUneBombe(x, y + 1)) {
                            numberBombs++;
                        }
                    }

                    //bas droite
                    if (y != Values.NUMBER_OF_ROWS - 1 && x != Values.NUMBER_OF_COLUMNS - 1) {
                        if (estUneBombe(x + 1, y + 1)) {
                            numberBombs++;
                        }
                    }
                    grid[y][x] = (char) numberBombs;
                }
            }
        }
    }

    public boolean[][] getReveal() {
        return reveal;
    }

    public boolean estUneBombe(int x, int y) {
        return grid[y][x] == 'X';
    }

    public ArrayList<Position> getBombs() {
        return bombs;
    }


    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(){
        this.grid = new char[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        this.reveal = new boolean[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        for (int i = 0; i < Values.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Values.NUMBER_OF_COLUMNS; j++) {
                grid[i][j] = '.';
                reveal[i][j] = false;
            }
        }
    }

    public void setFlag(int x,int y){
        if (!Values.GAME_ENDING){
            if (grid[y][x] >= 0 && !reveal[y][x]) {
                if (Values.NUMBER_OF_FLAGS != 0) {
                    grid[y][x] = 'F';
                    Values.NUMBER_OF_FLAGS--;
                }
            } else if (grid[y][x] == 'F') {
                grid[y][x] = '.';
                Values.NUMBER_OF_FLAGS++;
            }
        }
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < Values.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Values.NUMBER_OF_COLUMNS; j++) {
                result += grid[i][j];
            }
            result += "\n";
        }
        return result;
    }

}