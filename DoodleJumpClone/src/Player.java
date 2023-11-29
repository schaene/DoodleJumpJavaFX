// The player object

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle{
    // the velocities
    private double xVelocity, yVelocity;
    private boolean isControllable = false;
    // the image of the player
    private Image image;

    // Player constructor
    public Player(double x, double y){
        // intantiates to player
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.image = new Image("/assets/playerRight.png");
        this.setX(x);
        this.setY(y);
        this.setWidth(image.getWidth());
        this.setHeight(image.getHeight());
        // add the image
        this.setFill(new ImagePattern(image));
        // make sure it stays in front
        this.setViewOrder(-2);
    }

    // setters
    public void setXVelocity(double xVelocity){
        this.xVelocity = xVelocity;
    }
    public void setYVelocity(double yVelocity){
        this.yVelocity = yVelocity;
    }

    public void setIsControllable(boolean isControllable){
        this.isControllable = isControllable;
    }

    // getters
    public boolean getIsControllable(){
        return isControllable;
    }
    public double getYVelocity(){
        return yVelocity;
    }
    public double getXVelocity(){
        return xVelocity;
    }

    // change the player icon from left to right while moving that direction
    public void setFacing(boolean isLeft){
        if(isLeft){
            image = new Image("/assets/playerLeft.png");
        }
        else{
            image = new Image("/assets/playerRight.png");
        }
        this.setFill(new ImagePattern(image));
    }

    // move the player according to the velocity, and allow rollover from the sides
    public void velocityDo(){
        this.setX(getX() + xVelocity);
        this.setY(getY() + yVelocity);

        // handle the player going off the sides of the screen
        if((this.getX() + (this.getWidth() / 2)) > GameConstants.GameWidth){
            this.setX(this.getX() - GameConstants.GameWidth);
        } else if((this.getX() + (this.getWidth() / 2)) < 0){
            this.setX(this.getX() + GameConstants.GameWidth);
        }
    } 
}