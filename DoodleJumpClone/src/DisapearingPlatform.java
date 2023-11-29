import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class DisapearingPlatform extends Platform{

    public DisapearingPlatform(double x, double y) {
        super(x, y);
        // make toy null. Nothing shold spawn on these platforms.
        toy = null;
        // make it white instead
        this.setFill(new ImagePattern(new Image("/assets/whitePlatform.png")));
    }

    // the action to complete when jumped on
    @Override
    public void jumpedOn(Player player){
        super.jumpedOn(player);
        this.setVisible(false);
    } 
    
}
