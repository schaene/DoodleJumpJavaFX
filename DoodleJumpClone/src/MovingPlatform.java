import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class MovingPlatform extends Platform{

    private Timeline timeline;

    private boolean goingLeft;



    public MovingPlatform(double x, double y) {
        // use the super constructor
        super(x, y);
        // make it blue instead
        this.setFill(new ImagePattern(new Image("/assets/bluePlatform.png")));

        //randomly set goingleft
        goingLeft = new Random().nextBoolean();

        // create the timeline and start it, allowing plstform to move side to side
        timeline = new Timeline(new KeyFrame(Duration.millis(15), e -> moveSides()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveSides(){
        //switch directions if its gone too far
        if(this.getX() <= 0){
            goingLeft = false;
        } else if(this.getX() + this.getWidth() >= 700){
            goingLeft = true;
        }
        if(goingLeft){
            this.setX(this.getX() - 1);
        } else{
            this.setX(this.getX() + 1);
        }
    }
    
}
