package com.example.dmineur2;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GraphicView extends Pane {
    private Dmineur model;
    private Rectangle[][] rectangles;
    private NumberView[][] numberViews;

    public GraphicView(Dmineur model) {
        super();
        this.model = model;
//        initGrid();
//        update();
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
                rect = new Rectangle(j * Values.SIZE_CASE + 1,i * Values.SIZE_CASE + 1 ,Values.SIZE_CASE - 2,Values.SIZE_CASE - 2);
                super.getChildren().add(rect);
                rectangles[i][j] = rect;
                numberViews[i][j] = new NumberView(j,i,model.getGrid()[i][j]);
            }
        }
    }

    public void update(){
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {

                if ((int) model.getGrid()[i][j] >= 0 && !model.getReveal()[i][j]){
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

//                if (model.getGrid()[i][j] == 'X'){
//                    rectangles[i][j].setFill(Color.BLACK);
//                }

                if (model.getGrid()[i][j] == 'F'){
                    rectangles[i][j].setFill(Color.RED);
                }


            }
        }
    }

    public void revealNumber(int x,int y){
        if(!model.estUneBombe(x,y) && !Values.GAME_ENDING) {
            if (!model.getReveal()[y][x] && model.getGrid()[y][x] != 'F') {
                if ((x + y) % 2 == 0) {
                    rectangles[y][x].setFill(Color.DARKGREY);
                    rectangles[y][x].setStroke(Color.DARKGREY);
                } else {
                    rectangles[y][x].setFill(Color.GREY);
                    rectangles[y][x].setStroke(Color.GREY);
                }

                super.getChildren().add(numberViews[y][x]);
                model.getReveal()[y][x] = true;
                System.out.println("ok");
            }
        } else {
            Values.GAME_ENDING = true;
            System.out.println("dead");
        }
    }
}
