package com.example.dmineur2;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class GraphicView extends Pane {
    private Dmineur model;
    private Rectangle[][] rectangles;
    private NumberView[][] numberViews;

    public GraphicView(Dmineur model) {
        super();
        this.model = model;
    }

    public void initGrid(){
        super.setMinHeight(Values.HEIGHT_GRID);
        super.setMinWidth(Values.WIDTH_GRID);
        numberViews = new NumberView[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        rectangles = new Rectangle[Values.NUMBER_OF_ROWS][Values.NUMBER_OF_COLUMNS];
        super.setPrefWidth(Values.WIDTH_GRID);
        super.setPrefHeight(Values.HEIGHT_GRID);
        Rectangle rect;
        for (int i = 0; i < model.getGrid().length; i++) {
            for (int j = 0; j < model.getGrid()[i].length; j++) {
                rect = new Rectangle(j * Values.SIZE_CASE,i * Values.SIZE_CASE,Values.SIZE_CASE ,Values.SIZE_CASE);
                super.getChildren().add(rect);
                Values.pane.add(rect);
                rectangles[i][j] = rect;
                numberViews[i][j] = new NumberView(j,i,model.getGrid()[i][j]);
            }
        }
    }

    public void update(){
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {

                if ((model.getGrid()[i][j] == '.' || (int) model.getGrid()[i][j] >= 0) && !model.getReveal()[i][j] ){
                    if ((i + j) % 2 == 0){
                        rectangles[i][j].setFill(Color.GREEN);
                        rectangles[i][j].setStroke(Color.GREEN);
                        rectangles[i][j].setStrokeWidth(2);
                    } else {
                        rectangles[i][j].setFill(Color.FORESTGREEN);
                        rectangles[i][j].setStroke(Color.FORESTGREEN);
                        rectangles[i][j].setStrokeWidth(2);
                    }
                }

                if (model.getFlag()[i][j]){
                    rectangles[i][j].setFill(Color.RED);
                    rectangles[i][j].setStroke(Color.RED);
                }

                numberViews[i][j].update(model.getGrid()[i][j]);


            }
        }
    }

    public void revealNumber(int x,int y){
        if(!model.estUneBombe(x,y) && !Values.GAME_ENDING) {
            if (!model.getReveal()[y][x] && !model.getFlag()[y][x]) {
                if (model.getGrid()[y][x] == 0){
                    ArrayList<Position> zeros = new ArrayList<>();
                    zeros.add(new Position(x,y));
                    while (zeros.size() > 0) {
                        x = zeros.get(zeros.size() - 1).getX();
                        y = zeros.get(zeros.size() - 1).getY();
                        zeros.remove(zeros.size() - 1);
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (model.isZero(x + j, y + i) && !model.isReveal(x + j,y + i)) {
                                    zeros.add(new Position(x + j,y + i));
                                }
                                if (model.isAny(x + j, y + i)) {
                                    afficherUneCase(x + j, y + i);
                                }
                            }
                        }
                    }
                } else {
                    afficherUneCase(x, y);
                }
            }
        } else {
            if (!model.getFlag()[y][x]) {
                Values.GAME_ENDING = true;
                System.out.println("dead");
            }
        }
    }

    private void afficherUneCase(int x, int y) {
            if ((x + y) % 2 == 0) {
                rectangles[y][x].setFill(Color.DARKGREY);
                rectangles[y][x].setStroke(Color.DARKGREY);
            } else {
                rectangles[y][x].setFill(Color.GREY);
                rectangles[y][x].setStroke(Color.GREY);
            }
            if (!model.getReveal()[y][x]) {
                super.getChildren().add(numberViews[y][x]);
                Values.pane.add(numberViews[y][x]);
                Values.NUMBER_OF_BOXES_FOUND++;
                if (Values.NUMBER_OF_COLUMNS * Values.NUMBER_OF_ROWS - Values.NUMBER_OF_BOXES_FOUND == Values.NUMBER_OF_BOMBS){
                    Values.GAME_ENDING = true;
                    System.out.println("gagné");
                }
                model.getReveal()[y][x] = true;
            }
    }
}
