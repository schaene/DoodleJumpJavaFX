import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GamePane extends Pane{
    // the player
    Player player;
    // an array list of the platforms the player jumps on
    ArrayList<Platform> platforms = new ArrayList<>();
    // the game timeline for controlling the player
    Timeline gameUpdaTimeline;

    Controls gameControls;

    public Image backgroundImage = new Image("/assets/background.png");

    public GamePane(){

        this.setBackground(new Background(new BackgroundImage(backgroundImage, null, null, null, null)));
        player = new Player(50, 400);

        platforms.add(new Platform(50, 500));

        this.getChildren().add(player);
        for (Platform platform : platforms) {
            this.getChildren().add(platform);
        }

        gameUpdaTimeline = new Timeline(new KeyFrame(Duration.millis(15), e -> gameUpdate()));
        gameUpdaTimeline.setCycleCount(Timeline.INDEFINITE);
        gameUpdaTimeline.play();

        player.setYVelocity(-15);

    }

    public void initGame(){
        gameControls = new Controls(this.getScene());
        player.setIsControllable(true);
    }

    private void gameUpdate(){
        player.velocityDo();
        if((player.getX() + (player.getWidth() / 2)) > this.getScene().getWidth()){
            player.setX(player.getX() - this.getScene().getWidth());
        } else if((player.getX() + (player.getWidth() / 2)) < 0){
            player.setX(player.getX() + this.getScene().getWidth());
        }
        handleControls();
        //System.out.println(player.getY());
        

        for (Platform platform : platforms) {
            if(player.intersects(platform.getBoundsInLocal())){
                player.setYVelocity(platform.getJumpVelocity());
            }
        }
        if(player.getYVelocity() < 10){
            player.setYVelocity(player.getYVelocity() + .3);
        }
    }

    private void handleControls(){
        if(player.getIsControllable()){
            if(gameControls.getButtonStatus()[gameControls.RIGHT] && player.getXVelocity() < 10){
                player.setXVelocity( player.getXVelocity() < 0 ? +.6: (player.getXVelocity() + .6));
                player.setFacing(false);
            }
            else if (gameControls.getButtonStatus()[gameControls.LEFT] && player.getXVelocity() > -10){
                player.setXVelocity( player.getXVelocity() > 0 ? -.6: (player.getXVelocity() - .6));
                player.setFacing(true);
            }
            else if(!gameControls.getButtonStatus()[gameControls.RIGHT] && !gameControls.getButtonStatus()[gameControls.LEFT]){
                //System.out.println("nothing pressed");
                player.setXVelocity(player.getXVelocity() - (player.getXVelocity() / 4));
            }

        }
    }
    
}
