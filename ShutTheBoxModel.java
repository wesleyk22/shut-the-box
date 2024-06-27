/* 
 * Wesley Keller
 * CIS 261 Data Structures
 * Project: GUI Shut the Box Game
 * View class
 * 3/18/2024
 */

import java.util.ArrayList;
import java.util.Random;

public class ShutTheBoxModel {
   // "Game status" variables
   private boolean gameRunning = false;
   private boolean selectingDoors = false;
   
   /* Array of 9 boolean that represent 9 flip/unflipped doors */
   private boolean[] doors; 
   
   /* Two variables for the first and second die and what number they rolled */
   private int rollOne;
   private int rollTwo;
   
   /* Current number that we have to "add to" */
   private int currentRollSum; 
   
   /* 2D Arraylist that will represent the valid combinations for the current round */
   private ArrayList<Integer> selectedNumbers; 
    
   // Constructor
   public ShutTheBoxModel(){
       /* Instantiate the doors array with a length of 9 */
       doors = new boolean[9]; 
       selectedNumbers = new ArrayList<Integer>();
   }
   
   public void generateTwoRollNumbers(){
       // Generate 2 random integers 1-6 and sum them
       Random myRandom = new Random();
       rollOne = myRandom.nextInt(6)+1;
       rollTwo = myRandom.nextInt(6)+1;
       currentRollSum = rollOne+rollTwo;
   }
   
   public boolean possibleCombinationExists(){
       /* Calculate the possible combinations for the currentRollSum
          based on what doors are flipped/unflipped in the doors 
          array */
          
       /* Instantiate an arrayList that will include all of the 
        * unflipped numbers we have to work with*/
       ArrayList<Integer> unflippedNumbers = new ArrayList<Integer>();
       
       for (int i=0; i<doors.length; i++){ 
           if (doors[i] == false) {
               unflippedNumbers.add(i+1); // The actual number would is the index+1
           }
       }
    
       /* Quadruply nested for loop to check combinations. Returns true as 
        * soon as it finds a possible combination*/
       for (int i = 0; i<unflippedNumbers.size(); i++){
           if (unflippedNumbers.get(i) == currentRollSum) {
               return true;
           }
           //Check double combinatinos
           for (int j = 1; j<unflippedNumbers.size(); j++){
               if ( (unflippedNumbers.get(i)+unflippedNumbers.get(j)) == currentRollSum
                   && i != j ) { // No duplicates
                   return true;
               }
               // Check triple combinations
               for (int k = 2; k<unflippedNumbers.size(); k++){
                   if ( (unflippedNumbers.get(i)+unflippedNumbers.get(j)+unflippedNumbers.get(k)) == currentRollSum 
                       && i != j && j != k && k != i){ // No duplicates
                       return true;
                   }
                   // Check quadruple combinations (the most there could be)(
                   for (int l = 3; l<unflippedNumbers.size(); l++){
                   if ( (unflippedNumbers.get(i)+unflippedNumbers.get(j)+unflippedNumbers.get(k)+unflippedNumbers.get(l)) == currentRollSum 
                       && i != j && j != k && k != l && l != i && l != j ){ // No duplicates
                       return true;
                   }
               } 
               } 
           }
       }
       // If we are here, no combinations were found, return false
       return false;
   }
   
   public void addSelectedNumber(int val) {
       this.selectedNumbers.add(val);
   }
   
   public void resetSelectedNumbers(){
       /* Clear the array so that there are no contents in it*/
       this.selectedNumbers.clear();
   }
   
   public boolean isSelected(int doorNum){
       if (this.selectedNumbers.contains(doorNum) == true) {
           return true;
       } else {
           return false;
       }
   }
   
   /* Method for checking if a door is flipped, takes a door number and converts 
    * it to the index version by subtracting it by one*/
   public boolean isFlipped(int doorNum){
       return this.doors[doorNum-1];
   }
   
   public void setFlip(int doorNum, boolean b){
       this.doors[doorNum-1] = b;
   }
   
   /* Method to check if the current selected numbers add to the sum.
      if they do, then return true */
   public boolean checkIfSelectedAddToSum(){
       int sum = 0;
       for (Integer num : selectedNumbers){
           sum += num; // Add i value to the usm
       }
       if (sum == this.currentRollSum) {
           return true;
       } else {
           return false;
       }
   }
   
   /* Method to check if the current selected numbers are GREATER than the sum.
      if they do, then return true */
   public boolean checkIfSelectedGreaterThanSum(){
       int sum = 0;
       for (Integer num : selectedNumbers){
           sum += num; // Add i value to the usm
       }
       if (sum > currentRollSum){
           return true;
       } else {
           return false;
       }
   }
   
   public void resetToDefaults(){
       /* Set game to not be running and selecting doors to false */
       setGameRunning(false);
       setSelectingDoors(false);
       resetSelectedNumbers();
       /* Set all flip values to false */
       for (int i=0; i<doors.length; i++){
           doors[i] = false;
       }
   }
   
   /* Method to check if all doors are flipped AKA "is the game won?" */
   public boolean allDoorsFlipped(){
       boolean assumption = true;
       for (int i=0; i<doors.length; i++){
            if (doors[i] == false){
                assumption = false;
            }
       }
       return assumption;
   }
   
   /* Getters/Setters */
   public ArrayList<Integer> getSelectedNumbers() {
       /* Return the array of selected numbers */
       return this.selectedNumbers;
   }
   
   public int getScore(){
       /* Score is the total of the remaining numbers, so calculate that and return that */
       int score = 0;
       for (int i=0; i<doors.length; i++){
           if (doors[i] == false){ // If it's an unflipped door
               score += (i+1); // Add the score
           }
       }
       return score;
   }
   
   public void setGameRunning(boolean val) {
       this.gameRunning = val;
   }
   
   public boolean getGameRunning(){
       return this.gameRunning;
   }
   
   public int getRollOne(){
       return this.rollOne;
   }
   
   public int getRollTwo(){
       return this.rollTwo;
   }
   
   public int getCurrentRollSum(){
       return this.currentRollSum;
   }
   
   public boolean isSelectingDoors(){
       return this.selectingDoors;
   }
   
   public void setSelectingDoors(boolean b){
       this.selectingDoors = b;
   }
}
