/*
 * Final Project
 * Course: ce1002
 * Name: 王均琦
 * Student ID: 107502545
 */
package finalProject;

import java.io.IOException;
import finalProject.controllers.LogginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.application.Application;

public class f1 extends Application{
	
	public static Stage primaryStage; //declare static Stage
	public static Scene primaryScene;
	@Override //override class Application
    public void start(Stage mainStage) throws IOException{
		f1.primaryStage = mainStage;
		f1.primaryStage.getIcons().add(new Image(f1.class.getResourceAsStream("views/image/Shiba.gif")));
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("views/loggin.fxml")); //get fxml
	    Parent main = loadder.load();
	    f1.primaryStage.setTitle("柴柴的股票模擬系統~"); //set title of window
		f1.primaryScene = new Scene(main); //create a scene
	    LogginController logCtrl = loadder.getController(); //for keyboard input
	    f1.primaryScene.setOnKeyPressed(logCtrl.onKeyPressed); //for keyboard input
	    f1.primaryStage.setScene(f1.primaryScene); //change scene
	    f1.primaryStage.show(); //show window
    }
	public static void main(String[] hay) {
        launch(hay); //launch
    }
} //end of class