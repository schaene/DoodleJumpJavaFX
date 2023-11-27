// The player object

import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class Player {
    double x, y, xVelocity, yVelocity;
    Image image;

    Timeline timeline;

    public Player(double x, double y){
        this.x = x;
        this.y = y;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.image = new Image("/assets/playerLeft.png");
    }

    
}
