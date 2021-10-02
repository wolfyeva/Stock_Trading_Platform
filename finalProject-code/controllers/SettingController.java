/*
 * Final Project
 * Course: ce1002
 * Name: 王均琦
 * Student ID: 107502545
 */
package finalProject.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import finalProject.f1;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

public class SettingController {
	public static boolean R=true;
	public static String name1="woof~";
	public static boolean tag=false;
	public static boolean a=false;
	@FXML
	ToggleGroup speed;
	@FXML
	CheckBox _RSI;
	@FXML
	Label _Name;
	@FXML
	RadioButton _Manual;
	@FXML
	RadioButton _Auto;
	@FXML
    public void initialize() {
		name1=MainController.name;
		_Name.setText(name1);
		a=MainController.a1;
		if(a==true) {
			_Auto.setSelected(true);
			_Manual.setSelected(false);
		}
			
		if(MainController.R1==true)
			_RSI.setSelected(true);
		else
			_RSI.setSelected(false);
    }
	public void onAuto() {
		a=true;
	}
	public void onManual() {
		a=false;
	}
	
	public void onLogOut() throws UnsupportedEncodingException, IOException {
		CreateAccountController.yes=false;
		FileOutputStream fca = new FileOutputStream(System.getProperty("user.dir")+"\\\\src\\\\finalProject\\\\data\\"+MainController.account+".txt");
		fca.write(String.valueOf(MainController.money).getBytes("UTF-8"));
		fca.close();
		f1.primaryStage.setScene(f1.primaryScene); //change scene
	}
	public void onReturn() {
		if(CreateAccountController.yes==false)
			f1.primaryStage.setScene(LogginController.mainScene); //change scene
		else {
			f1.primaryStage.setScene(CreateAccountController.newScene); //change scene
		}
	}
	public void onRSI() {
		if(_RSI.isSelected()==true)
			R=true;
		else
			R=false;
	}
	public void onChooseFile() {
		FileChooser fc = new FileChooser();
		File openFile = fc.showOpenDialog(f1.primaryStage);
		String openfilepath = openFile.getPath();
		_Name.setText(openFile.getName());
		name1=openFile.getName();
		
	    MainController.s=openfilepath;
	    MainController.name=name1;
	    tag=true;
	}
	
	public EventHandler<KeyEvent> onKeyPressed = (e)->{ //get input from keyboard
		if (e.getCode() == KeyCode.E) {
			try {
				onLogOut();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getCode() == KeyCode.R) {
			onReturn();
		}
				
	}; //lambda~
}
