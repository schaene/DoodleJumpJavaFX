import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

        primaryStage.setTitle("Doodle Jump!");
        primaryStage.getIcons().add(new Image("/assets/icon.png"));
        Image exitImage = new Image("/assets/closeButton.png");
        Rectangle exitButton = new Rectangle(50, 50, new ImagePattern(exitImage));
        exitButton.setOnMouseClicked(e -> {
            System.exit(0);
        });

        
        HBox hbox = new HBox(exitButton);
        hbox.setAlignment(Pos.TOP_RIGHT);
        GamePane myGamePane = new GamePane();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(myGamePane);
        borderPane.setTop(hbox);

        Scene scene = new Scene(borderPane, GameConstants.GameWidth, GameConstants.GameHeight);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

        myGamePane.requestFocus();
    }
}