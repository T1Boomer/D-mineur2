package com.example.dmineur2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Label difficulty = new Label("Difficulty :");
        Label flags = new Label();
        flags.setFont(Font.font("Math Serif", FontWeight.BOLD,15));
        Rectangle rect = new Rectangle(20,20,Color.TRANSPARENT);
        difficulty.setPrefWidth(60);
        flags.setPrefWidth(100);

        MenuButton diff = new MenuButton("Easy");
        diff.setPrefWidth(80);
        MenuItem easy = new MenuItem("Easy");
        MenuItem medium = new MenuItem("Medium");
        MenuItem hard = new MenuItem("Hard");
        diff.getItems().addAll(easy,medium,hard);

        diff.setOnMouseEntered(actionEvent -> {
            diff.setEffect(new DropShadow());
            diff.setStyle("-fx-background-color: LIGHTGREEN; -fx-border-color: GREY; -fx-border-width: 1; -fx-background-radius: 3;-fx-border-radius: 3px");
        });
        diff.setOnMouseExited(actionEvent -> {
            diff.setEffect(null);
            diff.setStyle("-fx-background-color: LIGHTGREY; -fx-border-color: GREY; -fx-border-width: 1;-fx-border-radius: 3;");

        });


        HBox hboxButtons = new HBox(6);
        hboxButtons.getChildren().addAll(difficulty,diff,rect,flags);
        hboxButtons.setSpacing(20);
        hboxButtons.setAlignment(Pos.CENTER);
        hboxButtons.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(0),new Insets(0))));
        hboxButtons.setBorder(new Border(new BorderStroke(Color.RED,null,new CornerRadii(0),new BorderWidths(10))));
        borderPane.setTop(hboxButtons);
        Dmineur dmineur = new Dmineur();
        GraphicView graphicView = new GraphicView(dmineur);
        graphicView.setOnMouseClicked(new MouseController(dmineur,graphicView));
        borderPane.setCenter(graphicView);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);

        easy.setOnAction(actionEvent -> {
            diff.setText(easy.getText());
            Values.setValues(Values.Difficulty.EASY);
            while(Values.pane.size() > 0){
                Values.pane.remove(Values.pane.size() - 1);
            }
            initGrid(dmineur, graphicView, stage, hboxButtons);
        });

        medium.setOnAction(actionEvent -> {
            diff.setText(medium.getText());
            Values.setValues(Values.Difficulty.MEDIUM);
            while(Values.pane.size() > 0){
                Values.pane.remove(Values.pane.size() - 1);
            }
            initGrid(dmineur, graphicView, stage, hboxButtons);
        });

        hard.setOnAction(actionEvent -> {
            diff.setText(hard.getText());
            Values.setValues(Values.Difficulty.HARD);
            initGrid(dmineur, graphicView, stage, hboxButtons);
        });

        scene.setOnMouseClicked(mouseEvent -> {
            flags.setText(" :  " + Values.NUMBER_OF_FLAGS);
            rect.setFill(Color.RED);
        });

        stage.show();
        stage.setResizable(false);
    }

    private void initGrid(Dmineur dmineur, GraphicView graphicView, Stage stage, HBox hboxButtons) {
        dmineur.setGrid();
        graphicView.initGrid();
        graphicView.update();
        stage.setMinWidth(Values.WIDTH_GRID + hboxButtons.getBorder().getInsets().getRight() + 6);
        stage.setMinHeight(Values.HEIGHT_GRID + hboxButtons.getHeight() + hboxButtons.getBorder().getInsets().getTop() * 4 - 1);
        stage.setMaxWidth(Values.WIDTH_GRID + hboxButtons.getBorder().getInsets().getRight() + 6);
        stage.setMaxHeight(Values.HEIGHT_GRID + hboxButtons.getHeight() + hboxButtons.getBorder().getInsets().getTop() * 4 - 1);
        stage.setWidth(Values.WIDTH_GRID + hboxButtons.getBorder().getInsets().getRight() + 6);
        stage.setHeight(Values.HEIGHT_GRID + hboxButtons.getHeight() + hboxButtons.getBorder().getInsets().getTop() * 4 - 1);
    }
}
