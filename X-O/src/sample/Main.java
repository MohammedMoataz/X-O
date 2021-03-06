package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Image image = new Image("X-O.jpg");
        stage.getIcons().add(image);

        stage.setTitle("X-O Game");
        stage.setResizable(false);
        stage.setX(1000);
        stage.setY(100);

        stage.setScene(scene);
        stage.show();
    }

    // Ibrahimovic

    public static void main(String[] args) {
        launch(args);
    }
}
