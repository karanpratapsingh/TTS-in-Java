package com.javatts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        ViewFactory viewFactory = new ViewFactory();

        scene.getStylesheets().add(getClass().getResource("main-css.css").toExternalForm());
        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setTitle("TTS in java");
        primaryStage.setScene(scene);
        viewFactory.initMovablePlayer(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
