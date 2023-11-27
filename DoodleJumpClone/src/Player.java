// The player object

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle{
    private double xVelocity, yVelocity;
    private boolean isControllable = false;;

    private Image image;

    public Player(double x, double y){
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.image = new Image("/assets/playerLeft.png");
        this.setX(x);
        this.setY(y);
        this.setWidth(image.getWidth());
        this.setHeight(image.getHeight());
        this.setFill(new ImagePattern(image));
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
