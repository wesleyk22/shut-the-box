/* 
 * Wesley Keller
 * CIS 261 Data Structures
 * Project: GUI Shut the Box Game
 * Die class that will be used in View class
 * 3/18/2024
 */

import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Die extends ImageView {
    
    // Default no-arg Constructor
    public Die(){
        this.setFace(1); 
        this.setFitHeight(50);
        this.setFitWidth(50);
    }
    
    public void roll(int resultNumber){
        // set the Face visually
        this.setFace(resultNumber);
    }
    
    public void setFace(int resultNum){
        if (resultNum > 0 && resultNum <= 6) {
            // Valid number 1-6
            this.setImage(new Image("image/die"+resultNum+".png"));
        }
    }
    
}
