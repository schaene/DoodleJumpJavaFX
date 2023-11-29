// The basic green platform. other platforms extend this one

import java.io.File;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle {
    //image of the platform
    protected Image platformImage;
    // sound it plays when jumped on
    protected File jumpSound;

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
        // set up the image and rectangle, and sound
        platformImage = new Image("/assets/greenPlatform.png");
        jumpSound = new File("./src/assets/SFX/jump.wav");
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
    public void jumpedOn(Player player){
        // set the player velocity to make them jump
        player.setYVelocity(this.getJumpVelocity());
        // tries to play the jumped on sound
        try {
            GameConstants.playSound(jumpSound);
        } catch (Exception e){}
    } 
    // the action to complete when collided with. calls jumpedOn and HeadHit if applicable
    public void collidedWith(Player player){
        //limits collision to just feet
        if(((player.getY() + player.getHeight()) -16 <= this.getY()) && (player.getX() + 40 <= this.getX() + this.getWidth()) && (player.getX() + player.getWidth() - 40 >= this.getX())){
            //limits collision to if the player is actively falling and the platform is visible
            if(player.getYVelocity() > 0 && this.isVisible()){
                // perform the jumped on action
                this.jumpedOn(player);
            }
        }
    }

    private void generateToy(){
        int toyChance = new Random().nextInt(200);
        System.out.println("toy chance: " + toyChance);
        // dont generate a toy 65% chance
        if(toyChance <=60){
            toy = null;
        // Generate a spring 10% chance
        } else if(toyChance <= 90){
            System.out.println("generated spring");
            toy = new Spring(this.getX() + (new Random().nextInt(GameConstants.PlatformWidth - GameConstants.springWidth*2) + GameConstants.springWidth), this.getY() - 50);
        } else if(toyChance <= 120){
            System.out.println("generated breakable");
            toy = new BreakablePlatform(new Random().nextInt(GameConstants.GameWidth - GameConstants.PlatformWidth), new Random().nextDouble(Math.abs(this.getY()+.1)) * -1, true);
        } else if(toyChance <= 125){
            System.out.println("generated black hole");
            toy = new BlackHolePlatform(new Random().nextInt(GameConstants.GameWidth - GameConstants.PlatformWidth), new Random().nextDouble(Math.abs(this.getY()+.1)) * -1);
        }
    }

    public Platform getToy(){
        return toy;
    }
} 
