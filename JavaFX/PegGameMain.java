package PegGame.JavaFX;

import javafx.application.Application;
import javafx.stage.Stage;

public class PegGameMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        PegGameController pegGameController = new PegGameController();
        pegGameController.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}