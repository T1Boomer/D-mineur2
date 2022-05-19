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
        int mouseX = (int) Math.floor(mouseEvent.getX() / Values.SIZE_CASE);
        int mouseY = (int) Math.floor(mouseEvent.getY() / Values.SIZE_CASE);
        switch (mouseEvent.getButton()){
            case PRIMARY:
                System.out.println("mouseX : " + mouseX + "    mouseY : " + mouseY);
                break;

            case SECONDARY:
                model.setFlag(mouseX,mouseY);
                view.update();
                break;
        }
    }
}