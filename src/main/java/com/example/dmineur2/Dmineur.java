package com.example.dmineur2;

import java.util.ArrayList;
import java.util.Random;

public class Dmineur {
    private ArrayList<Position> bombs;
    private char [][] grid;
    private boolean [][] reveal;
    private boolean [][] flag;


    public Dmineur() {
        this.bombs = new ArrayList<>();
        this.grid = new char[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        this.reveal = new boolean[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        this.flag = new boolean[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
    }

    public void setBombs(int mouseX, int mouseY){
        this.bombs = new ArrayList<>();
        int x = new Random().nextInt(Values.NUMBER_OF_COLUMNS);
        int y = new Random().nextInt(Values.NUMBER_OF_ROWS);
        for (int i = 0; i < Values.NUMBER_OF_BOMBS; i++) {
            while (grid[y][x] == 'X' || isZeroMouse(mouseX, mouseY, x, y)){
                x = new Random().nextInt(Values.NUMBER_OF_COLUMNS);
                y = new Random().nextInt(Values.NUMBER_OF_ROWS);
            }
            bombs.add(new Position(x, y));
            grid[y][x] = 'X';
        }
    }

    private boolean isZeroMouse(int mouseX, int mouseY, int x, int y) {
        boolean b = false;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x == mouseX + i && y == mouseY + j){
                    b = true;
                    break;
                }
            }
        }
        return b;
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

    public boolean isZero(int x, int y){
        return x >= 0 && x < Values.NUMBER_OF_COLUMNS && y >= 0 && y < Values.NUMBER_OF_ROWS && (int) grid[y][x] == 0;
    }

    public boolean isReveal(int x,int y){
        if (x >= 0 && x < Values.NUMBER_OF_COLUMNS && y >= 0 && y < Values.NUMBER_OF_ROWS) {
            return reveal[y][x];
        }
        return false;
    }



    public boolean isAny(int x,int y){
        if (x >= 0 && x < Values.NUMBER_OF_COLUMNS && y >= 0 && y < Values.NUMBER_OF_ROWS) {
                return !estUneBombe(x,y);
        }
        return false;
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
        this.flag = new boolean[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        for (int i = 0; i < Values.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Values.NUMBER_OF_COLUMNS; j++) {
                grid[i][j] = '.';
                reveal[i][j] = false;
                flag[i][j] = false;
            }
        }
    }

    public boolean[][] getFlag() {
        return flag;
    }

    public void setFlag(int x, int y){
        if (!Values.GAME_ENDING){
            if (!reveal[y][x] && !flag[y][x]) {
                if (Values.NUMBER_OF_FLAGS != 0) {
                    flag[y][x] = true;
                    Values.NUMBER_OF_FLAGS--;
                }
            } else if (flag[y][x]) {
                flag[y][x] = false;
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