import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
    // add stuff for game over screen
    Rectangle gameOverRectangle;
    Rectangle playAgainButton;
    Text scoreLabel;
    int score;
    int highScore;
    // stores if the game was init or gameover
    private boolean init = false;
    private boolean isGameOver = false;

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

        // show the title
        Image title = new Image("/assets/title.png");
        Rectangle titleRectangle = new Rectangle(title.getWidth(), title.getHeight(), new ImagePattern(title));
        titleRectangle.setX(75);
        titleRectangle.setY(175);
        this.getChildren().add(titleRectangle);

        // show the play button
        Image playButtonImage = new Image("/assets/playButton.png");
        Rectangle playButton = new Rectangle(playButtonImage.getWidth(), playButtonImage.getHeight(), new ImagePattern(playButtonImage));
        playButton.setX(300);
        playButton.setY(500);
        this.getChildren().add(playButton);

        // add click event on play button to init the game
        playButton.setOnMouseClicked(e -> {
            this.initGame();
            
        });
        // change color when hovered
        playButton.setOnMouseEntered(e -> {
            playButton.setFill(new ImagePattern(new Image("/assets/playHover.png")));
        });
        playButton.setOnMouseExited(e -> {
            playButton.setFill(new ImagePattern(playButtonImage));
        });

    }

    // initialize the game by setting the player to controllable
    public void initGame(){
        this.getChildren().clear();
        isGameOver = false;
        score = 0;
        player = new Player(GameConstants.GameWidth/ 2, 800);
        this.getChildren().add(player);
        //create the starting platform
        platforms.add(new Platform(GameConstants.GameWidth/ 2, 900, true));
        // set the players velocity for the initial jump
        player.setYVelocity(-20);
        init = true;
        gameControls = new Controls(this.getScene());
        player.setIsControllable(true);
        generatePlatforms(-10, true);

        // add gameover rectangle
        Image gameOverImage = new Image("/assets/race-over.png");
        gameOverRectangle = new Rectangle(gameOverImage.getWidth() * 2, gameOverImage.getHeight() * 2, new ImagePattern(gameOverImage));
        gameOverRectangle.setX(75);
        gameOverRectangle.setY(GameConstants.GameHeight + 400);
        this.getChildren().add(gameOverRectangle);

        // add playAgainButton rectangle
        Image playAgainImage = new Image("/assets/play-again.png");
        playAgainButton = new Rectangle(playAgainImage.getWidth(), playAgainImage.getHeight(), new ImagePattern(playAgainImage));
        playAgainButton.setX(75);
        playAgainButton.setY(GameConstants.GameHeight + 700);
        this.getChildren().add(playAgainButton);
        // make the button start game over
        playAgainButton.setOnMouseClicked(e ->{
            this.initGame();
        });

        // add score label for game and gameover
        scoreLabel = new Text(50, -20, "Score: " + score);
        scoreLabel.setFill(Color.BLACK);
        scoreLabel.setScaleX(2);
        scoreLabel.setScaleY(2);
        this.getChildren().add(scoreLabel);
        scoreLabel.setViewOrder(-3);
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

        //only do if init'd. game over will stop these
        if(init){
            // move platforms down rather than the player if the player is half way up the screen
            moveScreen();
            // handles the controls and applies whatever they may be
            handleControls();
            //if player is under the map, its game over
            if(player.getY() + player.getHeight() > GameConstants.GameHeight && !isGameOver){
                gameOver();
            }
        } else if(isGameOver){
            // move everything off the screen til the "race over" banner is shown
            if(player.getY() - player.getHeight() < this.getScene().getHeight() && gameOverRectangle.getY() > 100){
            double offset = (getScene().getHeight()) - player.getY();
            //if the offset is positive, adjsutment is needed
            if(offset < 0){
                //put the player back below the half way line
                player.setY(player.getY() + offset);
                gameOverRectangle.setY(gameOverRectangle.getY() + offset);
                playAgainButton.setY(playAgainButton.getY() + offset);
                scoreLabel.setY(scoreLabel.getY() + offset);

                // move the platforms 
                for (Platform platform : platforms) {
                    platform.setY(platform.getY() + offset);
                }
            }
        }
        }

        checkIfShouldGeneratePlatforms();
        
        // detects collision with platforms and gives the player the velocity for that platform
        playerJump();

        
    }

    //do if gameover
    private void gameOver(){
        scoreLabel = new Text(200, GameConstants.GameHeight + 600, "");
        scoreLabel.setFill(Color.RED);
        scoreLabel.setScaleX(5);
        scoreLabel.setScaleY(5);
        this.getChildren().add(scoreLabel);

        System.out.println("gameover called");
        if(score > highScore){
            highScore = score;
        }
        scoreLabel.setText("Score: " + score + "\nHigh Score: " + highScore);
        isGameOver = true;
        init = false;

    }

    // moves the platforms if need be
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
    //checks if it *should* generate platforms
    private void checkIfShouldGeneratePlatforms(){
        // removes a platform if it goes below the screen
        boolean makeNewPlatforms = false;
        // used an iterator. normal forEach threw a concurrent modification error
        Iterator<Platform> iterator = platforms.iterator(); 
        while (iterator.hasNext()) {
            Platform platform = iterator.next();

            if (platform.getY() > this.getScene().getHeight()) {
                iterator.remove();
                this.getChildren().remove(platform);
                System.out.println("removed a platform");
                score++;
                scoreLabel.setText("Score: " + score);
                //add one platform to ensure the game is playable
                generatePlatforms(0, false);
                // if less than 12 platforms, make a new random amount of platforms
                if (platforms.size() < 12) {
                    makeNewPlatforms = true;
                }
            }
        }
        //makes the new platforms
        if(makeNewPlatforms)
            generatePlatforms(0, true);
        
        if(player.getYVelocity() < 10){
            player.setYVelocity(player.getYVelocity() + .3);
        }
    }

    // generates a randomsubset of platforms, potentially with springs
    private void generatePlatforms(int startingPosition, boolean addRandom){
        System.out.println("generating platforms");
        if(addRandom){
            int numberOfPlatforms = 5 + new Random().nextInt(2);
            for(int i = startingPosition; i < numberOfPlatforms; i++){
                int newX = new Random().nextInt(((int)this.getScene().getWidth())) - GameConstants.PlatformWidth;
                if(newX < 0){
                    newX = 0;
                }
                int newY =  (i*-100) - new Random().nextInt(25);
                int typeOfPlatform = new Random().nextInt(3);
                System.out.println("type: " + typeOfPlatform);
                // generate a moving blue platform
                if(typeOfPlatform == 1){
                    platforms.add((Platform)new MovingPlatform(newX, newY));
                // generate a disapearing white platform
                } else if(typeOfPlatform == 2){
                    platforms.add((Platform)new DisapearingPlatform(newX, newY));
                // generate a non-moving green platform
                }
                else{
                    platforms.add(new Platform(newX, newY));
                }
                // if the platform has a toy on it, such as a spring, add it to the list as well
                if(platforms.get(platforms.size() - 1).getToy() != null){
                    platforms.add(platforms.get(platforms.size() - 1).getToy());
                }
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
    // makes the player jump if landed on something with velocity to give
    private void playerJump(){
        for (Platform platform : platforms) {
            if(player.intersects(platform.getBoundsInLocal())){
                //limits collision to just feet
                if(((player.getY() + player.getHeight()) -16 <= platform.getY()) && (player.getX() + 40 <= platform.getX() + platform.getWidth()) && (player.getX() + player.getWidth() - 40 >= platform.getX())){
                    //limits collision to if the player is actively falling and the platform is visible
                    if(player.getYVelocity() > 0 && platform.isVisible()){
                        platform.jumpedOn();
                        player.setYVelocity(platform.getJumpVelocity());
                    }
                }
            }
        }
    }
    
}
