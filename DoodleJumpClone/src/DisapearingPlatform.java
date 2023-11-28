import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class DisapearingPlatform extends Platform{

    public DisapearingPlatform(double x, double y) {
        super(x, y);
        toy = null;
        // make it white instead
        this.setFill(new ImagePattern(new Image("/assets/whitePlatform.png")));
    }

    // the action to complete when jumped on
    @Override
    public void jumpedOn(){
        this.setVisible(false);
    } 
    
}
