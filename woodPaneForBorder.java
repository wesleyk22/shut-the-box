/* 
 * Wesley Keller
 * CIS 261 Data Structures
 * Project: GUI Shut the Box Game
 * woodpane UI class that will be used in View class
 * 3/18/2024
 */

import javafx.scene.layout.Pane;

public class woodPaneForBorder extends Pane {
    
    // Constructor
    public woodPaneForBorder(){
        /* for the Outer border pane, a light brown pane to be the "border" that
           will be re-used 4 times */
        this.setStyle("-fx-background-color: rgb(196, 164, 132)");
        this.setPrefSize(50,50);
    }
    
}
