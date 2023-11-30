import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

/* Schaene Trible
 * 11/29/2023
 * CSCI-3331-001
 */

public class BreakablePlatform extends Platform{
    // timeline to use when pushing the broken platform off screen
    private Timeline timeline;
    // stores if it it broken or not
    private boolean isBroken = false;


    public BreakablePlatform(double x, double y, boolean noToy) {
        super(x, y, noToy);
        // make it broken brown instead
        this.setFill(new ImagePattern(new Image("/assets/breakablePlatform.png")));
        jumpSound = new File("./src/assets/SFX/break.wav");

        // create the timeline but dont start, allows it to fall down the screen
        timeline = new Timeline(new KeyFrame(Duration.millis(15), e -> moveDown()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    private void moveDown(){
        if(isBroken){
            this.setY(this.getY() + 7);
        }
    }

    // the action to complete when jumped on
    @Override
    public void jumpedOn(Player player){
        try {
            GameConstants.playSound(jumpSound);
        } catch (Exception e){}

        if(!isBroken){
            isBroken = true;
            Image brokenImage = new Image("/assets/brokenPlatform.png");
            this.setFill(new ImagePattern(brokenImage));
            this.setWidth(brokenImage.getWidth());
            this.setHeight(brokenImage.getHeight());
            timeline.play();
        }
        
    } 
    
}
