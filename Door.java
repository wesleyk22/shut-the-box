/* 
 * Wesley Keller
 * CIS 261 Data Structures
 * Project: GUI Shut the Box Game
 * Door class that will be used in View class
 * 3/18/2024
 */

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Door extends StackPane {
    // Member variables
    private int number;
    private boolean flipped;
    private Rectangle doorShape;
    Text doorNumText;
    
    // Constructor
    public Door (int number){
        // Set variables
        this.number = number;
        this.flipped = false; // By default, a door is not flipped
        
        // The door is a rectangular shape
        doorShape = new Rectangle(40,100);
        doorShape.setStroke(Color.BLACK);
        doorShape.setFill(Color.rgb(196, 164, 132)); // This color should be a "light brown"
        
        // Text for the door that will be the number
        doorNumText = new Text(""+number+"");
        doorNumText.setFont(Font.font("Calibri", FontWeight.BOLD, 25)); // family,weight,size
        
        this.getChildren().addAll(doorShape,doorNumText);
        
    }
    
    public void flipDown(){
        this.flipped = true;
        doorNumText.setText("");
    }
    
    public boolean isFlipped(){
        return this.flipped;
    }
    
    public void flipUp(){
        this.flipped = false;
        doorNumText.setText(""+number+"");
    }
    
    public int getNumber(){
        return this.number;
    }
    
    public void setHighlight(boolean b) {
        if (b == true){
           /* "Highlighting" this door */
           doorShape.setStroke(Color.YELLOW);
           doorShape.setStrokeWidth(2);
        } else {
           // Defaults
           doorShape.setStroke(Color.BLACK);
           doorShape.setStrokeWidth(1);
        }
    }
}
