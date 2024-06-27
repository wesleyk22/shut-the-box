/* 
 * Wesley Keller
 * CIS 261 Data Structures
 * Project: GUI Shut the Box Game
 * View class
 * 3/18/2024
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/* I tried making this just extend application, but instead I'm making this
   view class extend borderpane and all of UI will be inside of this BorderPane,
   and then in the main class I will launch an application with an instance
   of this borderpane view */
public class ShutTheBoxView extends BorderPane {
    // Private member variables
    private Die die1; // The first dice
    private Die die2; // The second dice
    private Door[] doors; // array for the 9 doors
    
    /* Private member variables that need to have getters 
    and be accessible from the controller to have a listener
    attached to them*/
    Button btRoll;
    Button btRestart;
    Button btUndo;
    Text gameText;
    
    // Constructor
    public ShutTheBoxView() {
        /* Create an "inner" borderPane that will have the green background,
           it will be the center of THIS border pane which will have light brown
           borders and buttons in the bottom and text at the top */
        BorderPane innerPane = new BorderPane();
        innerPane.setStyle("-fx-background-color: green");
        
        /* HBox and an array for the 9 doors */
        HBox hboxForDoors = new HBox(5); // Spacing of 5
        hboxForDoors.setAlignment(Pos.CENTER);
        doors = new Door[9]; 

        /* Add 9 door objects into the array */
        for (int i=1; i<=9; i++){ // 1-9
            Door newDoor = new Door(i);
            doors[i-1] = newDoor; // i-1 because array starts at 0
            hboxForDoors.getChildren().add(newDoor);
        }
        
        /* HBox for the two dice */
        HBox hboxForDice = new HBox(5);
        hboxForDice.setAlignment(Pos.CENTER);
        /* Instantiate the dice and add them to hbox */
        die1 = new Die();
        die2 = new Die();
        hboxForDice.getChildren().addAll(die1,die2);

        /* Set positions of everything in innerPane*/
        innerPane.setTop(hboxForDoors);
        innerPane.setBottom(hboxForDice);
        
        /* Set the inner borderpane to be the center of the outer borderpane */
        this.setCenter(innerPane);
        
        /* Hbox for Buttons for bottom of the outer borderpane */
        HBox hboxForButtons = new HBox(5);
        hboxForButtons.setStyle("-fx-background-color: rgb(196, 164, 132)");
        hboxForButtons.setPrefSize(50,50);
        
        /* Buttons */
        btRoll = new Button("Roll");
        btRestart = new Button("Restart");
        btUndo = new Button("Undo");
        hboxForButtons.getChildren().addAll(btRoll, btRestart, btUndo);
        hboxForButtons.setAlignment(Pos.CENTER);

        /* Pane for the "text" of the game that instructs the user what to do
           as the game goes on*/
        StackPane paneForText = new StackPane(); 
        paneForText.setStyle("-fx-background-color: rgb(196, 164, 132)");
        paneForText.setPrefSize(50,50);
        gameText = new Text("Welcome to shut the box! Click 'Roll' to play");
        gameText.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
        gameText.setWrappingWidth(400);
        gameText.setTextAlignment(TextAlignment.CENTER);
        paneForText.getChildren().add(gameText);
        
        /* Set everything accordingly in outer border */
        this.setTop(paneForText);
        this.setBottom(hboxForButtons);
        this.setLeft(new woodPaneForBorder());
        this.setRight(new woodPaneForBorder()); 
    }
    
    public void clearHighlights() {
        for (Door d : doors) {
            d.setHighlight(false);
        }
    }
    
    public void resetToDefaults(){
        /* Visually unflip all of the door objects */
        for (Door d : doors){
            d.flipUp();
        }
        /* Clear highlights*/
        clearHighlights();
        setGameText("Game restarted! Click 'Roll' to play");
        
    }
    
    /* Getters/Setters */
    public Button getRollButton(){
        return btRoll;
    }
    
    public Button getRestartButton(){
        return btRestart;
    }
    
    public Button getUndoButton(){
        return btUndo;
    }
    
    public Die getDieOne(){
        return this.die1;
    }
    
    public Die getDieTwo(){
        return this.die2;
    }
    
    public void setGameText(String s){
        this.gameText.setText(s);
    }
    
    public Door getDoorFXObject(int doorNum){
        return doors[doorNum-1];
    }
    
    public Door[] getDoors(){
        return this.doors;
    }
}
