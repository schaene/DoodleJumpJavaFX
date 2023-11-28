import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GamePane extends Pane{
    // the player
    private Player player;
    // an array list of the platforms the player jumps on
    private ArrayList<Platform> platforms = new ArrayList<>();
    // the game timeline for controlling the player
    private Timeline gameUpdaTimeline;
    // add the controls for the game, used to allow for smoother handling
    private Controls gameControls;
    // add the graphing paper background
    private Image backgroundImage = new Image("/assets/background.png");
    // gamePane constructor
    public GamePane(){
        // set the background
        this.setBackground(new Background(new BackgroundImage(backgroundImage, null, null, null, null)));
        // create the player and add it to the pane
        player = new Player(50, 800);
        this.getChildren().add(player);

        //create the starting platforms
        platforms.add(new Platform(50, 900));
        // add all current platforms to the pane
        for (Platform platform : platforms) {
            this.getChildren().add(platform);
        }
        // create the timeline and start it, allowing player to have gravity and jump
        gameUpdaTimeline = new Timeline(new KeyFrame(Duration.millis(15), e -> gameUpdate()));
        gameUpdaTimeline.setCycleCount(Timeline.INDEFINITE);
        gameUpdaTimeline.play();
        // set the players velocity for the initial jump
        player.setYVelocity(-15);

    }

    // initialize the game by setting the player to controllable
    public void initGame(){
        gameControls = new Controls(this.getScene());
        player.setIsControllable(true);
        generatePlatforms(-10);
    }

    // game update function. calls the needed functions to make the game a game
    private void gameUpdate(){
        // apply the current velocity to the player
        player.velocityDo();

        // handle the player going off the sides of the screen
        if((player.getX() + (player.getWidth() / 2)) > this.getScene().getWidth()){
            player.setX(player.getX() - this.getScene().getWidth());
        } else if((player.getX() + (player.getWidth() / 2)) < 0){
            player.setX(player.getX() + this.getScene().getWidth());
        }

        // move platforms down rather than the player if the player is half way up the screen
        moveScreen();

        // handles the controls and applies whatever they may be
        handleControls();
        
        // detects collision with platforms and gives the player the velocity for that platform
        for (Platform platform : platforms) {
            if(player.intersects(platform.getBoundsInLocal())){
                //limits collision to just feet
                if(((player.getY() + player.getHeight()) -10 <= platform.getY()) && (player.getX() + 40 <= platform.getX() + platform.getWidth()) && (player.getX() + player.getWidth() - 40 >= platform.getX())){
                    //limits collision to if the player is actively falling
                    if(player.getYVelocity() > 0){
                        player.setYVelocity(platform.getJumpVelocity());
                    }
                }
            }
        }

        // removes a plaform if it goes below the screen
        boolean makeNewPlatforms = false;
        // used an iterator. normal forEach threw a concurrent modification error
        Iterator<Platform> iterator = platforms.iterator(); 
        while (iterator.hasNext()) {
            Platform platform = iterator.next();

            if (platform.getY() > this.getScene().getHeight()) {
                iterator.remove();
                this.getChildren().remove(platform);
                System.out.println("removed a platform");

                if (platforms.size() < 12) {
                    makeNewPlatforms = true;
                }
            }
        }


        
        if(makeNewPlatforms)
            generatePlatforms(0);
        
        if(player.getYVelocity() < 10){
            player.setYVelocity(player.getYVelocity() + .3);
        }
    }

    private void moveScreen(){
        if(player.getY() - player.getHeight()/2 < this.getScene().getHeight() / 2){
            double offset = (getScene().getHeight() / 2) - player.getY();
            //if the offset is positive, adjsutment is needed
            if(offset > 0){
                //put the player back below the half way line
                player.setY(player.getY() + offset);
                // move the platforms 
                for (Platform platform : platforms) {
                    platform.setY(platform.getY() + offset);
                }
            }
        }
    }

    private void generatePlatforms(int startingPosition){
        System.out.println("generating platforms");
        int numberOfPlatforms = 5 + new Random().nextInt(4);
        for(int i = startingPosition; i < numberOfPlatforms; i++){
            int newX = new Random().nextInt(((int)this.getScene().getWidth())) - GameConstants.PlatformWidth;
            if(newX < 0){
                newX = 0;
            }
            int newY =  (i*-100) - new Random().nextInt(50);
            int typeOfPlatform = new Random().nextInt(2);
            System.out.println("type: " + typeOfPlatform);
            if(typeOfPlatform == 1){
                platforms.add((Platform)new MovingPlatform(newX, newY));
            }else{
                platforms.add(new Platform(newX, newY));
            }
            
        }
        // remove all the children
        for (Platform platform : platforms) {
            this.getChildren().remove(platform);
        }

        // add all current platforms to the pane
        for (Platform platform : platforms) {
            this.getChildren().add(platform);
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
