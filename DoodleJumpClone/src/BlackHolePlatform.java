import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/* Schaene Trible
 * 11/29/2023
 * CSCI-3331-001
 */

public class BlackHolePlatform extends Platform{

    public BlackHolePlatform(double x, double y) {
        super(x, y);
        toy = null;
        Image blackHoleImage = new Image("/assets/blackhole.png");
        this.setFill(new ImagePattern(blackHoleImage));
        this.setWidth(blackHoleImage.getWidth());
        this.setHeight(blackHoleImage.getHeight());
    }

    @Override
    public void collidedWith(Player player){
        if((player.getX() + 40 <= this.getX() + this.getWidth() -20)  && (player.getX() + player.getWidth() - 20 >= this.getX())){
            player.setVisible(false);
            player.setY(GameConstants.GameHeight);
            player.setYVelocity(1);
        }
    }
    
}
