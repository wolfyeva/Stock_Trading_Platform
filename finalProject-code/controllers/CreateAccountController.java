/*
 * Final Project
 * Course: ce1002
 * Name: 王均琦
 * Student ID: 107502545
 */
package finalProject.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class CreateAccountController {
	static boolean yes=false;
	
	@FXML
	TextField _Name;
	@FXML
	PasswordField _Password;
	@FXML
	PasswordField _RePassword;
	@FXML
	Label _SameName;
	@FXML
	Label _WrongPassword;
	@FXML
	Label _NoName;
	@FXML
	Label _NoPassword;
	public static Scene newScene;
	public void onRegister() throws IOException {
		if(_Name.getText().equals(""))
			_NoName.setVisible(true);
		else
			_NoName.setVisible(false);
		if(_Password.getText().equals("")||_RePassword.getText().equals(""))
			_NoPassword.setVisible(true);
		else
			_NoPassword.setVisible(false);
		if(_Name.getText().equals("")==false&&_Password.getText().equals("")==false&&_RePassword.getText().equals("")==false) {
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
					_SameName.setVisible(true);
					break;
				}
				else {
					_SameName.setVisible(false);
					if(_RePassword.getText().equals(_Password.getText())==false) {
						_WrongPassword.setVisible(true);
						break;
					}
					else {
						MainController.account=_Name.getText();
						yes=true;
						FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"\\\\src\\\\finalProject\\\\data\\\\account.txt"); //declare a file output stream under the path
						fos.write(allbytes);
						fos.write("\n".getBytes("UTF-8"));
						fos.write(_Name.getText().getBytes("UTF-8"));//convert content of text area from UTF-8 to string and output to file
						fos.write("\n".getBytes("UTF-8"));
						fos.write(_Password.getText().getBytes("UTF-8")); //convert content of text area from UTF-8 to string and output to file
						fos.close(); //close it~!
						FileOutputStream fca = new FileOutputStream(System.getProperty("user.dir")+"\\src\\finalProject\\data\\"+MainController.account+".txt");
						fca.write("1000\n".getBytes("UTF-8"));
						fca.close();
						FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/main.fxml")); //get fxml
					    Parent main = loadder.load(); //load it
					    newScene = new Scene(main); //create a scene
					    MainController mainCtrl = loadder.getController(); //for keyboard input
					    newScene.setOnKeyPressed(mainCtrl.onKeyPressed); //for keyboard input
					    f1.primaryStage.setScene(newScene); //change scene
					    
					}
				}
			}
		}
	}
	public void onReturn() {
		_Name.clear();
		_Password.clear();
		_RePassword.clear();
		_SameName.setVisible(false);
		_WrongPassword.setVisible(false);
		_NoPassword.setVisible(false);
		_NoName.setVisible(false);
		f1.primaryStage.setScene(f1.primaryScene); //change scene
	}
	public EventHandler<KeyEvent> onKeyPressed = (e)->{ //get input from keyboard
		if (e.getCode() == KeyCode.ENTER)
			try {
				onRegister();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}; //lambda~
}
