package com.example.dmineur2;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NumberView extends Text {
   private char number;

    public NumberView(int x, int y, char text) {
        super(x * Values.SIZE_CASE + Values.SIZE_CASE / 4, (y + 1) * Values.SIZE_CASE - Values.SIZE_CASE / 4, String.valueOf(text));
        super.setText(String.valueOf((int) text));
        number = text;
        update();
    }


    public void update(){
        super.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,Values.SIZE_CASE * 0.80));
        switch ((int) number){
            case 1:
                super.setFill(Color.BLUE);
                break;
            case 2:
                super.setFill(Color.DARKGREEN);
                break;
            case 3:
                super.setFill(Color.RED);
                break;
            case 4:
                super.setFill(Color.YELLOW);
                break;
            case 5:
                super.setFill(Color.ORANGE);
                break;
            case 6:
                super.setFill(Color.PURPLE);
                break;
            case 7:
                super.setFill(Color.PINK);
                break;
            case 8:
                super.setFill(Color.BLACK);
                break;
            default:
                super.setFill(Color.TRANSPARENT);
        }
    }
}
