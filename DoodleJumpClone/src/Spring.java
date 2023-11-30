import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/* Schaene Trible
 * 11/29/2023
 * CSCI-3331-001
 */

public class Spring extends Platform{

    public Spring(double x, double y) {
        super(x, y);
        platformImage = new Image("/assets/spring.png");
        jumpSound = new File("./src/assets/SFX/springshoes.wav");
        this.setFill(new ImagePattern(platformImage));
        this.setWidth(platformImage.getWidth());
        this.setHeight(platformImage.getHeight());
        this.setViewOrder(-1);
    }

    // returns the velocity to give the character when jumped on
    @Override
    public int getJumpVelocity(){
        return -25;
    }

    // the action to complete when jumped on
    @Override
    protected void jumpedOn(Player player){
        super.jumpedOn(player);
        this.setFill(new ImagePattern(new Image("/assets/spring2.png")));
    } 
    
}
