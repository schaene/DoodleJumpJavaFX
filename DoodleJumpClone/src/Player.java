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

    public Player(double x, double y){
        // intantiates to player
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.image = new Image("/assets/playerLeft.png");
        this.setX(x);
        this.setY(y);
        this.setWidth(image.getWidth());
        this.setHeight(image.getHeight());
        // add the image
        this.setFill(new ImagePattern(image));
        // make sure it stays in front
        this.setViewOrder(-1);
    }
    public void setXVelocity(double xVelocity){
        this.xVelocity = xVelocity;
    }
    public void setYVelocity(double yVelocity){
        this.yVelocity = yVelocity;
    }

    public void setIsControllable(boolean isControllable){
        this.isControllable = isControllable;
    }

    public boolean getIsControllable(){
        return isControllable;
    }
    public double getYVelocity(){
        return yVelocity;
    }
    public double getXVelocity(){
        return xVelocity;
    }

    public void setFacing(boolean isLeft){
        if(isLeft){
            image = new Image("/assets/playerLeft.png");
        }
        else{
            image = new Image("/assets/playerRight.png");
        }
        this.setFill(new ImagePattern(image));
    }

    public void velocityDo(){
        this.setX(getX() + xVelocity);
        this.setY(getY() + yVelocity);
    }

    
}
