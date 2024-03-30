package PegGame.JavaFX;

import PegGame.main.GameState;
import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PegGameController extends Application {

    private static boolean pegClicked = false;
    private static Button pegButtonClicked;
    public static Label gameStateLabel = new Label();
    public static Label possibleMovesLabel = new Label();
    private Button saveAndExitButton = new Button("Save and Exit");
    private TextField filenameTextField = new TextField();
    private VBox saveVBox = new VBox(20);
    private Scene saveScene = new Scene(saveVBox, 300, 150);

    @Override
    public void start(Stage primaryStage) {
        //handling some buttons
        PegGameView.exitButton.setOnAction(event -> primaryStage.close());

        PegGameView.saveButton.setOnAction(event ->{
            primaryStage.setScene(saveScene);
            primaryStage.centerOnScreen();
            handleSaveButton();
        });

        saveAndExitButton.setOnAction(event -> {
            String filename = filenameTextField.getText();
            PegGameModel.saveGameStateToFile(filename); // Call method to save game state to file
            primaryStage.close(); // Close the application after saving
        });

        // Create label and text field for filename input
        Label label = new Label("Filename:");
        TextField textField = new TextField();
        textField.setPromptText("Enter filename");

        HBox inputHBox = new HBox(10);
        inputHBox.setAlignment(Pos.CENTER);
        inputHBox.getChildren().addAll(label, textField);

        // Create buttons for OK and Cancel actions
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        HBox buttonHBox = new HBox(10);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.getChildren().addAll(okButton, cancelButton);

        // Create VBox to hold inputHBox and buttonHBox
        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));
        vBox.getChildren().addAll(inputHBox, buttonHBox);

        // Create scene
        Scene scene = new Scene(vBox, 300, 150);

        // Welcome message
        Label welcomeLabel = new Label("Peg Game\n\nTo play, click on a peg and then click on the destination hole. Keep the following rules in mind:\n\n1. Ensure you are moving a peg.\n2. Move the peg to a hole.\n3. The location between the peg and the hole must be another peg.\n\nAfter each successful move, the middle peg will be removed. Keep moving pegs until only one is left on the board.");
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setTextAlignment(TextAlignment.CENTER);
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button startButton = new Button("Start");
        VBox welcomeMsg = new VBox();
        welcomeMsg.getChildren().addAll(welcomeLabel, startButton);
        welcomeMsg.setSpacing(40);
        welcomeMsg.setAlignment(Pos.CENTER);
        welcomeMsg.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene welcomeScene = new Scene(welcomeMsg);

        // Set action for OK button
        okButton.setOnAction(event -> {
            String filename = textField.getText();
            PegGameModel.initializeButtons(filename);
            primaryStage.setScene(welcomeScene);
            primaryStage.centerOnScreen();
        });

        // Set action for Start button
        startButton.setOnAction(event -> {
            primaryStage.setScene(PegGameView.createGameScene(primaryStage));
            primaryStage.centerOnScreen();
            PegGameModel.gameState = GameState.IN_PROGRESS;
            updateGameStateLabel();
        });

        // Set action for Cancel button
        cancelButton.setOnAction(event -> primaryStage.close());

        // Set stage title and scene
        primaryStage.setTitle("Peg Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static String checkButton(Button button) {
        Shape graphic = (Shape) button.getGraphic(); // Retrieve the graphic of the button

        if (graphic instanceof Circle) { // Check if the graphic is a Circle
            Circle circle = (Circle) graphic;
            if (circle.getFill().equals(javafx.scene.paint.Color.BLACK)) {
                return "Hole";
            } else if (circle.getFill().equals(javafx.scene.paint.Color.WHITE)||circle.getFill().equals(javafx.scene.paint.Color.web("#0072b2"))) {
                return "Peg";
            }
        }

        return "Unknown"; // Return "Unknown" if the button doesn't represent a peg or a hole
    }

    public static void handleButtonClicked(int row, int col) {
        //Stage primaryStage = new Stage();
        Button button = PegGameModel.buttons[row][col];
        String buttonType = checkButton(button);
        if (!pegClicked) {
            if ("Peg".equals(buttonType)) {
                pegClicked = true;
                pegButtonClicked = button;
            }
        } else if (pegClicked) {
            if ("Peg".equals(buttonType)) {
                setGameStateLabel("Invalid move: Middle button is not a hole", Color.RED,15);
                pegClicked = false; // Reset pegClicked flag
            } else if ("Hole".equals(buttonType)) {
                int[] indices = (int[]) pegButtonClicked.getUserData();
                int middleRow = (row + indices[0]) / 2;
                int middleCol = (col + indices[1]) / 2;
                Button middleButton = PegGameModel.buttons[middleRow][middleCol];
                String middleButtonType = checkButton(middleButton);
                if ("Peg".equals(middleButtonType)) {
                    pegButtonClicked.setGraphic(PegGameView.hole());
                    button.setGraphic(PegGameView.peg());
                    middleButton.setGraphic(PegGameView.hole());
                    updateGameStateLabel();
                    pegClicked = false;
                } else {
                    setGameStateLabel("Invalid move: Middle button is not a peg", Color.RED,15);
                    pegClicked = false; // Reset pegClicked flag
                }
            }
        }
    }

    private static void setGameStateLabel(String text, Color color, int fontSize) {
        gameStateLabel.setText(text);
        gameStateLabel.setTextFill(color);
        gameStateLabel.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
    }

    public static void switchToGameOverScene(String message,Color color) {
        // Create the game over scene
        Scene gameOverScene = PegGameView.createGameOverScene();
    
        // Get the primary stage
        Stage primaryStage = (Stage) gameStateLabel.getScene().getWindow();
    
        // Set the game over message
        Label gameOverLabel = (Label) ((VBox) gameOverScene.getRoot()).getChildren().get(0);
        gameOverLabel.setText(message);
        gameOverLabel.setTextFill(color);
    
        // Show the game over scene
        primaryStage.setScene(gameOverScene);
    }

    public static void updateGameStateLabel() {
        gameStateLabel.setText("Game State: " + PegGameModel.gameState.getMessage());
        gameStateLabel.setTextFill(Color.WHITE);
        possibleMovesLabel.setText("Possible Moves: " + PegGameModel.countPossibleMoves()); 
    }

    private void handleSaveButton() {
        // Create label and text field for filename input
        Label filenameLabel = new Label("Enter File Name:");
        filenameTextField.setPromptText("Enter filename");

        HBox filenameInputHBox = new HBox(10);
        filenameInputHBox.setAlignment(Pos.CENTER);
        filenameInputHBox.getChildren().addAll(filenameLabel, filenameTextField);
        saveVBox.setPadding(new Insets(20));
        saveVBox.getChildren().addAll(filenameInputHBox, saveAndExitButton);
        saveVBox.setAlignment(Pos.CENTER);

        //updateGameState();

    }
}