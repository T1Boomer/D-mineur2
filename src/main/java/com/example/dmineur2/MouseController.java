package com.example.dmineur2;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
                        model.setBombs(mouseX,mouseY);
                        model.setNumbers();
                        view.update();
                        Values.GAME_START = false;
                    }
                    view.revealNumber(mouseX, mouseY);
                    break;

                case SECONDARY:
                    model.setFlag(mouseX, mouseY);
                    view.update();
                    break;
            }
        }
    }
}