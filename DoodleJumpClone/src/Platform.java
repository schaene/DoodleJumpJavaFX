// The basic green platform. other platforms extend this one

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
    static final int platformWidth = 300;
    static final int platformHeight = 100;



    // location of the platform
    double x;
    double y;
    // color of the platform
    Color color;
    // the platform itself
    Rectangle rectangle;

    public Platform(double x, double y){
        this.x = x;
        this.y = y;
        color = Color.GREEN;
        rectangle = new Rectangle(x, y, platformWidth, platformHeight);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        

    }

    // returns the velocity to give the character when jumped on
    public int getJumpVelocity(){
        return 100;
    }

    // the action to complete when jumped on
    public void jumpedOn(){

    } 

    public Rectangle getRectangle(){
        return rectangle;
    }
} 
