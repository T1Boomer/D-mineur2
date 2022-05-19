package com.example.dmineur2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Values.setValues(Values.Difficulty.EASY);
        BorderPane borderPane = new BorderPane();
        Label difficulty = new Label("Difficulty :");
        Button easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");
        HBox hboxButtons = new HBox(4);
        hboxButtons.getChildren().addAll(difficulty,easy,medium,hard);
        borderPane.setTop(hboxButtons);
        Dmineur dmineur = new Dmineur();
        GraphicView graphicView = new GraphicView(dmineur);
        graphicView.setOnMouseClicked(new MouseController(dmineur,graphicView));
        borderPane.setCenter(graphicView);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);

        stage.show();
    }
}
