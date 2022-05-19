package com.example.dmineur2;

import java.util.ArrayList;
import java.util.Random;

public class Dmineur {
    private ArrayList<Position> bombs;
    private char [][] grid;

    public Dmineur() {
        this.bombs = new ArrayList<>();
        this.grid = new char[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        setGrid();
        setBombs();
    }

    public void setBombs(){
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

    public ArrayList<Position> getBombs() {
        return bombs;
    }


    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(){
        for (int i = 0; i < Values.NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < Values.NUMBER_OF_COLUMNS; j++) {
                grid[j][i] = '.';
            }
        }
    }

    public void setFlag(int x,int y){
        if (!Values.GAME_ENDING){
            switch (grid[y][x]){
                case '.':
                    if(Values.NUMBER_OF_FLAGS != 0) {
                        grid[y][x] = 'F';
                        Values.NUMBER_OF_FLAGS--;
                    }
                    break;
                case 'F':
                    grid[y][x] = '.';
                    Values.NUMBER_OF_FLAGS++;
                    break;
            }
        }
    }

}