import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class BreakablePlatform extends Platform{
    // timeline to use when pushing the broken platform off screen
    private Timeline timeline;
    // stores if it it broken or not
    private boolean isBroken = false;


    public BreakablePlatform(double x, double y, boolean noToy) {
        super(x, y, noToy);
        // make it broken brown instead
        this.setFill(new ImagePattern(new Image("/assets/breakablePlatform.png")));

        // create the timeline but dont start, allows it to fall down the screen
        timeline = new Timeline(new KeyFrame(Duration.millis(15), e -> moveDown()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void moveDown(){
        if(isBroken){
            this.setY(this.getY() + 3);
        }
    }

    // the action to complete when jumped on
    @Override
    public void jumpedOn(Player player){
        // doesnt make the player jump. instead just breaks
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
