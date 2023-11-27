import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    // Launches the Application
    public static void main(String[] args) throws Exception {
        Application.launch();
    }

    // Starts the Magic
    @Override
    public void start(Stage primaryStage) throws Exception {

        Label scoreBoard = new Label("Your Score: ");
        Text text = new Text();
        
        HBox hbox = new HBox(scoreBoard, text);
        GamePane myGamePane = new GamePane();

        Scene scene = new Scene(myGamePane, 700, 1100);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

        myGamePane.requestFocus();
        myGamePane.initGame();
    }
}