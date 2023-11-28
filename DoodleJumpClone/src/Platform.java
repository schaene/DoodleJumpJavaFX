// The basic green platform. other platforms extend this one

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle {
    //image of the platform
    protected Image platformImage;

    // location of the platform
    double x;
    double y;
    // color of the platform
    Color color;
    // the platform itself
    //Rectangle rectangle;

    //The toy/powerup/spring the platform has
    protected Platform toy;

    public Platform(double x, double y){
        // set up the image and rectangle
        platformImage = new Image("/assets/greenPlatform.png");
        this.setFill(new ImagePattern(platformImage));
        this.x = x;
        this.y = y;
        this.setX(x);
        this.setY(y);
        this.setWidth(platformImage.getWidth());
        this.setHeight(platformImage.getHeight());
        generateToy();

        // determine if a toy will be added to the platform
    }

    public Platform(double x, double y, boolean noToy){
        this(x, y);
        toy = null;

    }

    // returns the velocity to give the character when jumped on
    public int getJumpVelocity(){
        return -15;
    }

    // the action to complete when jumped on
    public void jumpedOn(){

    } 

    private void generateToy(){
        int toyChance = new Random().nextInt(100);
        System.out.println("toy chance: " + toyChance);
        // dont generate a toy 65% chance
        if(toyChance <=65){
            toy = null;
        // Generate a spring 10% chance
        } else if(toyChance <= 75){
            System.out.println("generated spring");
            toy = new Spring(this.getX() + (new Random().nextInt(GameConstants.PlatformWidth) - GameConstants.springWidth / 2), this.getY() - 50);
        }
    }

    public Platform getToy(){
        return toy;
    }
} 
