/*
 * Final Project
 * Course: ce1002
 * Name: 王均琦
 * Student ID: 107502545
 */
package finalProject.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import finalProject.f1;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LogginController {
	
	@FXML
	TextField _Name;
	@FXML
	PasswordField _Password;
	@FXML
	Label _NotExist;
	@FXML
	Label _WrongPassword;
	public static Scene mainScene;
	public void onLogIn () throws IOException {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\finalProject\\data\\account.txt");
			} catch (FileNotFoundException e) {e.printStackTrace();} //declare a file input stream under the path
			byte[] allbytes = null;
			allbytes = fis.readAllBytes();
			String content = ""; //template to store content
			try {content = new String(allbytes, "UTF-8");} catch (UnsupportedEncodingException e) {e.printStackTrace();} //convert from UTF-8 to string
			String[] contentArr = content.split("\n"); //split the name with the score
		for(int i=0 ; i<contentArr.length ; i+=2) { //and get the names
			if(contentArr[i].equals(_Name.getText())) {
				_NotExist.setVisible(false);
				_WrongPassword.setVisible(true);
				if(contentArr[i+1].equals(_Password.getText())) {
					_WrongPassword.setVisible(false);
					MainController.account=_Name.getText();
					FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/main.fxml")); //get fxml
				    Parent main = loadder.load(); //load it
				    mainScene = new Scene(main); //create a scene
				    MainController mainCtrl = loadder.getController(); //for keyboard input
				    mainScene.setOnKeyPressed(mainCtrl.onKeyPressed); //for keyboard input
				    
				    f1.primaryStage.setScene(mainScene); //change scene
					break;
				}
			}
			else
				_NotExist.setVisible(true);
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //close it~!
	}
	public void onCreateAccount () throws IOException {
		_Name.clear();
		_Password.clear();
		_NotExist.setVisible(false);
		_WrongPassword.setVisible(false);
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/createAccount.fxml")); //get fxml
	    Parent maze = loadder.load(); //load it
	    Scene mazeScene = new Scene(maze); //create a scene
	    CreateAccountController createCtrl = loadder.getController(); //for keyboard input
	    mazeScene.setOnKeyPressed(createCtrl.onKeyPressed); //for keyboard input
	    f1.primaryStage.setScene(mazeScene); //change scene
	}
	public EventHandler<KeyEvent> onKeyPressed = (e)->{ //get input from keyboard
		if (e.getCode() == KeyCode.ENTER)
			try {
				onLogIn ();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}; //lambda~
	@FXML
    public void initialize() {
	 _Name.setText("wolfy");
	 _Password.setText("123");
    }
}
