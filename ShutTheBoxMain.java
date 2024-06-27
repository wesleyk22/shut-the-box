/* 
 * Wesley Keller
 * CIS 261 Data Structures
 * Project: GUI Shut the Box Game
 * Main class
 * 3/18/2024
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShutTheBoxMain extends Application {
    
   @Override
   public void start(Stage primaryStage) {
       ShutTheBoxView view = new ShutTheBoxView();
       ShutTheBoxModel model = new ShutTheBoxModel();
       ShutTheBoxController controller = new ShutTheBoxController(model,view);
       
       // Start the game
       controller.startGame();
       
       /* Launch the view */
       Scene myScene = new Scene(view, 520, 520);
       primaryStage.setTitle("Shut The Box");
       primaryStage.setScene(myScene);
       primaryStage.show();
   }
   
   public static void main(String[] args){
       launch(args);
   }
   
}
