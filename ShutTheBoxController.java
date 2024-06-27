/* 
 * Wesley Keller
 * CIS 261 Data Structures
 * Project: GUI Shut the Box Game
 * Controller class
 * 3/18/2024
 */

import javafx.stage.Stage;

public class ShutTheBoxController {
    private ShutTheBoxModel model; // Model Object
    private ShutTheBoxView view; // View Object
    
    // Constructor for controller
    public ShutTheBoxController(ShutTheBoxModel model, ShutTheBoxView view){
        this.model = model;
        this.view = view;
        /* Attach actionEvent listeners to the UI elements that
        can be interacted with in view*/
        view.getRollButton().setOnAction(e -> playRound());
        view.getRestartButton().setOnAction(e -> restartGame());
        view.getUndoButton().setOnAction(e -> undoSelections());
        Door[] doorArray = view.getDoors();
        /* Create an event for each door if it's clicked to be sent to the method
        clickedDoor based on the door number */
        for (Door i : doorArray) {
            i.setOnMouseClicked(e-> clickedDoor(i.getNumber()));
        }
    }
    
    public void clickedDoor(int doorNum){
        if (model.isSelectingDoors() == true) {
            /* Get the doors array from model that is a boolean array that
               represents true/false based on flipped/unflipped */
            if (model.isFlipped(doorNum) == false && model.isSelected(doorNum) == false) { 
                model.addSelectedNumber(doorNum);
                view.getDoorFXObject(doorNum).setHighlight(true);
                if (model.checkIfSelectedAddToSum() == true) { // If true, then a match was found,
                    // Flip the selected doors, restart round
                    for (Integer num : model.getSelectedNumbers()){
                        // In the view, flip the FX Door object so it appears "flipped"
                        view.getDoorFXObject(num).flipDown();
                        // In the model, update the boolean array
                        model.setFlip(num, true);
                    }
                    view.setGameText(""+model.getSelectedNumbers()+" Adds up to "+model.getCurrentRollSum() +", Nice job! Those doors are now flipped. Click 'Roll' to start the next round");
                    // Clear selected numbers
                    model.resetSelectedNumbers();
                    // No longer selecting doors
                    model.setSelectingDoors(false);
                    // Clear highlights
                    view.clearHighlights();
                    // Check if game is won
                    if (model.allDoorsFlipped() == true){
                        /* If there are no possible combinations, then the game is lost */
                        view.setGameText("YOU WIN! You flipped all of the doors! Congratulations. Press 'Restart' to play again");
                        // Disable game functionality until restart button is clicked
                        model.setGameRunning(false);
                        model.setSelectingDoors(false);
                    }
                }
            }
            
            /* After this point, check if the sum happens to be GREATER */
            if (model.checkIfSelectedGreaterThanSum() == true){
                view.setGameText("Your current selected numbers exceed the roll sum. There is a possible combination, try again!");
                model.resetSelectedNumbers();
                view.clearHighlights();
            }
            
        }
    }
    
    public void startGame(){
        model.setGameRunning(true);
    }
   
    public void playRound(){
       // Make sure game is started
       if (model.getGameRunning() == true && model.isSelectingDoors() == false ){ // Can't start a new round if you're already selecting doors    
           // Generate two random ints in model
           model.generateTwoRollNumbers(); 
           // Play dice roll animation with the two roll values from model 
           view.getDieOne().roll(model.getRollOne());
           view.getDieTwo().roll(model.getRollTwo()) ; 
           // Calculate possible combinations 
           if (model.possibleCombinationExists() == true ) {
               /* There are possible combinations, let the user pick doors to flip */
               view.setGameText("You rolled a "+model.getCurrentRollSum()+", flip any combination of doors that add up to that number.");
               // The user is selecting doors now
               model.setSelectingDoors(true);
               
           } else {
               /* If there are no possible combinations, then the game is lost */
               view.setGameText("There are no possible combinations. Final Score: "+model.getScore()+", Click 'Restart' To restart the game and try again.");
               // Disable game functionality until restart button is clicked
               model.setGameRunning(false);
               model.setSelectingDoors(false);
           }
       }
           
    }
    
    public void restartGame() {
        // Resets all stats to default and starts new game
        // Reset model and view to their defaults
        model.resetToDefaults();
        view.resetToDefaults();
        // Start a new game
        startGame();
    }
    
    public void undoSelections(){
        view.clearHighlights();
        model.resetSelectedNumbers();
    }
}
