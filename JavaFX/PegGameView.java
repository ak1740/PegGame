package PegGame.JavaFX;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PegGameView {

    public static Button exitButton = new Button("Exit");
    public static Button saveButton = new Button("Save");

    public static Scene createGameScene(Stage primaryStage) {
        // Create a VBox to hold the HBoxes of buttons
        VBox vbox = new VBox();
        vbox.setBackground(new Background(new BackgroundFill(Color.web("#6a452c"), CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setSpacing(15); // Reduced spacing between labels and buttons
        vbox.setAlignment(Pos.CENTER);
    
        // Create label to display game state
        PegGameController.updateGameStateLabel();
        PegGameController.gameStateLabel.setAlignment(Pos.TOP_LEFT);
        PegGameController.gameStateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        PegGameController.gameStateLabel.setTextFill(Color.WHITE);
        vbox.getChildren().add(PegGameController.gameStateLabel);
    
        // Create label to display possible moves
        PegGameController.possibleMovesLabel.setAlignment(Pos.TOP_LEFT);
        PegGameController.possibleMovesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        PegGameController.possibleMovesLabel.setTextFill(Color.WHITE);
        vbox.getChildren().add(PegGameController.possibleMovesLabel);
    
        // Add buttons to the VBox
        for (Button[] buttonRow : PegGameModel.buttons) {
            HBox hbox = new HBox();
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().addAll(buttonRow);
            vbox.getChildren().add(hbox);
        }
    
        // Create HBox for save and exit buttons
        HBox saveExitBox = new HBox();
        saveExitBox.getChildren().addAll(saveButton, exitButton);
        saveExitBox.setAlignment(Pos.CENTER);
        saveExitBox.setSpacing(40);
    
        // Create VBox to hold the main content and save/exit buttons
        VBox mainContent = new VBox();
        mainContent.getChildren().addAll(vbox, saveExitBox);
        mainContent.setSpacing(10);
        mainContent.setBackground(new Background(new BackgroundFill(Color.web("#6a452c"), CornerRadii.EMPTY, Insets.EMPTY)));
    
        // Create the game scene
        Scene gameScene = new Scene(mainContent);
        
        // Set action for buttons
        for (int i = 0; i < PegGameModel.buttons.length; i++) {
            for (int j = 0; j < PegGameModel.buttons[i].length; j++) {
                int row = i;
                int col = j;
                PegGameModel.buttons[i][j].setOnAction(event -> {
                    PegGameController.handleButtonClicked(row, col);
                    PegGameModel.updateGameState(primaryStage);
                });
            }
        }
    
        return gameScene;
    }

    public static Node peg() {
        Circle circle = new Circle(20, Color.web("#0072b2"));
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);
        return circle;
        
    }

    public static Node hole() {
        Circle circle = new Circle(17, Color.BLACK);
        circle.setStroke(Color.web("#6a452c"));
        circle.setStrokeWidth(7);
        return circle;
    }

    public static Scene createGameOverScene() {
        // Create VBox to hold game over message and exit button
        VBox gameOverBox = new VBox(20);
        gameOverBox.setAlignment(Pos.CENTER);
        gameOverBox.setBackground(new Background(new BackgroundFill(Color.web("#6a452c"), CornerRadii.EMPTY, Insets.EMPTY)));
    
        // Create game over label
        Label gameOverLabel = new Label();
        gameOverLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gameOverLabel.setTextFill(Color.WHITE);
        gameOverBox.getChildren().add(gameOverLabel);
    
        // Create exit button for game over scene
        Button gameOverExitButton = new Button("Exit");
        gameOverExitButton.setOnAction(event -> {
            Stage stage = (Stage) gameOverExitButton.getScene().getWindow();
            stage.close();
        });
        gameOverBox.getChildren().add(gameOverExitButton);
    
        // Create game over scene
        Scene gameOverScene = new Scene(gameOverBox, 300, 150);
        return gameOverScene;
    }

    public static Button createHoleButton() {
        Button button = new Button();
        Circle circle = new Circle(17, Color.BLACK);
        circle.setStroke(Color.web("#6a452c"));
        circle.setStrokeWidth(7);
        button.setGraphic(circle);
        button.setBackground(Background.EMPTY);
        return button;
    }
    // Helper method to create a peg button
    public static Button createPegButton(int row, int col) {
        Button button = new Button();
        Circle circle = new Circle(20, Color.WHITE);
        circle.setStroke(Color.BLACK);
        button.setGraphic(circle);
        circle.setStrokeWidth(3);
        button.setBackground(Background.EMPTY);
        button.setUserData(new int[]{row, col});
        return button;
    }


}