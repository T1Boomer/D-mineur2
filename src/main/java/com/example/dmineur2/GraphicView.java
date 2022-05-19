package com.example.dmineur2;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphicView extends GridPane {
    private Dmineur model;
    private Rectangle[][] rectangles;

    public GraphicView(Dmineur model) {
        super();
        this.model = model;
        initGrid();
        update();
    }

    public void initGrid(){
        rectangles = new Rectangle[model.getGrid().length][model.getGrid()[0].length];
        super.setPrefWidth(Values.WIDTH_GRID);
        super.setPrefHeight(Values.HEIGHT_GRID);
        Rectangle rect;
        for (int i = 0; i < model.getGrid().length; i++) {
            for (int j = 0; j < model.getGrid()[i].length; j++) {
                rect = new Rectangle(Values.SIZE_CASE,Values.SIZE_CASE);
                super.add(rect,i,j);
                rectangles[j][i] = rect;
            }
        }
    }

    public void update(){
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {

                if (model.getGrid()[j][i] == '.' || model.getGrid()[j][i] == ','){
                    if ((i + j) % 2 == 0){
                        rectangles[j][i].setFill(Color.GREEN);
                    } else {
                        rectangles[j][i].setFill(Color.FORESTGREEN);
                    }
                }

                if (model.getGrid()[j][i] == 'X'){
                    rectangles[j][i].setFill(Color.BLACK);
                }

                if (model.getGrid()[j][i] == 'F'){
                    rectangles[j][i].setFill(Color.RED);
                }
            }
        }
    }
}
