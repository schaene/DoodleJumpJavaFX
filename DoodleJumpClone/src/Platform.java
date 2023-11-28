// The basic green platform. other platforms extend this one

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle {
    //image of the platform
    private Image platformImage;

    // location of the platform
    double x;
    double y;
    // color of the platform
    Color color;
    // the platform itself
    Rectangle rectangle;

    public Platform(double x, double y){
        platformImage = new Image("/assets/greenPlatform.png");
        this.setFill(new ImagePattern(platformImage));
        this.x = x;
        this.y = y;
        this.setX(x);
        this.setY(y);
        this.setWidth(platformImage.getWidth());
        this.setHeight(platformImage.getHeight());
    }

    // returns the velocity to give the character when jumped on
    public int getJumpVelocity(){
        return -15;
    }

    // the action to complete when jumped on
    public void jumpedOn(){

    } 
} 
