package com.project.xogame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle("X-O Game");
        stage.setResizable(false);
        stage.setX(1000);
        stage.setY(100);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
module com.project.xogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.xogame to javafx.fxml;
    exports com.project.xogame;
}
 */