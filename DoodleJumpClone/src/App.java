import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {
    // Launches the Application
    public static void main(String[] args) throws Exception {
        Application.launch();
    }

    // Starts the Magic
    @Override
    public void start(Stage primaryStage) throws Exception {
        // set the title and icon
        primaryStage.setTitle("Doodle Jump!");
        primaryStage.getIcons().add(new Image("/assets/icon.png"));
        // set up the exit button
        Image exitImage = new Image("/assets/closeButton.png");
        Rectangle exitButton = new Rectangle(50, 50, new ImagePattern(exitImage));
        exitButton.setViewOrder(-2);
        exitButton.setOnMouseClicked(e -> {
            System.exit(0);
        });

        //put the exit button in the hbox
        HBox hbox = new HBox(exitButton);
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.setBackground(new Background(new BackgroundFill(Color.BLUE.deriveColor(0, 1, 1, 0.3), null, null)));
        //hbox.setViewOrder(1);
        //create the gamepane and borderpane
        GamePane myGamePane = new GamePane();
        BorderPane borderPane = new BorderPane();
        //add the hbox and gamePane to the borderpane
        borderPane.setCenter(myGamePane);
        borderPane.setTop(hbox);
        // add the borderpane to the scene and set the size to the constants
        Scene scene = new Scene(borderPane, GameConstants.GameWidth, GameConstants.GameHeight);
        // set up and show the stage
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        // put focus on the gamePane so it can be controlled
        myGamePane.requestFocus();
    }
}