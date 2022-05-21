package com.example.dmineur2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.ButtonBarSkin;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Label difficulty = new Label("Difficulty :");
        difficulty.setPrefWidth(70);

        MenuButton diff = new MenuButton("Easy");
        MenuItem easy = new MenuItem("Easy");
        MenuItem medium = new MenuItem("Medium");
        MenuItem hard = new MenuItem("Hard");
        diff.getItems().addAll(easy,medium,hard);



        HBox hboxButtons = new HBox(6);
        hboxButtons.getChildren().addAll(difficulty,diff);
        hboxButtons.setSpacing(10);
        hboxButtons.setAlignment(Pos.CENTER);
        hboxButtons.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY,new CornerRadii(0),new Insets(0))));
        hboxButtons.setBorder(new Border(new BorderStroke(Color.RED,null,new CornerRadii(0),new BorderWidths(10))));
        borderPane.setTop(hboxButtons);
        Dmineur dmineur = new Dmineur();
        GraphicView graphicView = new GraphicView(dmineur);
        graphicView.setOnMouseClicked(new MouseController(dmineur,graphicView));
        borderPane.setCenter(graphicView);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
//        System.out.println(dmineur);

        easy.setOnAction(actionEvent -> {
            diff.setText(easy.getText());
            Values.setValues(Values.Difficulty.EASY);
            initGrid(dmineur, graphicView, stage, hboxButtons);
        });

        medium.setOnAction(actionEvent -> {
            diff.setText(medium.getText());
            Values.setValues(Values.Difficulty.MEDIUM);
            initGrid(dmineur, graphicView, stage, hboxButtons);
        });

        hard.setOnAction(actionEvent -> {
            diff.setText(hard.getText());
            Values.setValues(Values.Difficulty.HARD);
            initGrid(dmineur, graphicView, stage, hboxButtons);
        });
        stage.show();
    }

    private void initGrid(Dmineur dmineur, GraphicView graphicView, Stage stage, HBox hboxButtons) {
        dmineur.setGrid();
        dmineur.setBombs();
        dmineur.setNumbers();
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
