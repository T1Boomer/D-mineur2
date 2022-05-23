package com.example.dmineur2;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MouseController implements EventHandler<MouseEvent> {

    private Dmineur model;
    private GraphicView view;

    public MouseController(Dmineur model, GraphicView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (!Values.GAME_ENDING) {
            int mouseX = (int) Math.floor(mouseEvent.getX() / Values.SIZE_CASE);
            int mouseY = (int) Math.floor(mouseEvent.getY() / Values.SIZE_CASE);
            switch (mouseEvent.getButton()) {
                case PRIMARY:
                    if (Values.GAME_START){
                        for (int i = 0; i < model.getFlag().length; i++) {
                            for (int j = 0; j < model.getFlag()[i].length; j++) {
                                model.getFlag()[i][j] = false;
                                Values.NUMBER_OF_FLAGS = Values.NUMBER_OF_BOMBS;
                            }
                        }
                        model.setBombs(mouseX,mouseY);
                        model.setNumbers();
                        view.update();
                        Values.GAME_START = false;
                    }
                    view.revealNumber(mouseX, mouseY);
                    break;

                case SECONDARY:
                    model.setFlag(mouseX, mouseY);
//                    mouseX = (int) (mouseX * Values.SIZE_CASE + Values.SIZE_CASE/3);
//                    mouseY = (int) (mouseY * Values.SIZE_CASE + Values.SIZE_CASE/5);
//                    Polygon polygon = new Polygon(mouseX,mouseY
//                            ,mouseX + 6,mouseY + 0
//                            ,mouseX + 16,mouseY + 5
//                            ,mouseX + 6,mouseY + 10
//                            ,mouseX + 6,mouseY + 20
//                            ,mouseX + 0,mouseY + 20);
//                    polygon.setFill(Color.RED);
//                    view.getChildren().add(polygon);
//                    Values.pane.add(polygon);
                    view.update();
                    break;
            }
        }
    }
}