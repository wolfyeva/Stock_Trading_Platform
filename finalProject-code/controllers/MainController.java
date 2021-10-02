/*
 * Final Project
 * Course: ce1002
 * Name: 王均琦
 * Student ID: 107502545
 */
package finalProject.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import finalProject.f1;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class MainController {
	public static String account="";
	public static boolean R1=true;
	public static boolean a1=false;
	public static double money =1000;
	public static String s=System.getProperty("user.dir")+"\\src\\finalProject\\data\\0056.csv";
	public static String name="0056.csv";
	int data=100, portion=0, percent=0, loop=0;
	double position=0, profit=0 ;
	
	ArrayList<Double> close = new ArrayList<>();
	ArrayList<Integer> amount = new ArrayList<>();
	ArrayList<Double> average10 = new ArrayList<>();
	ArrayList<Double> average30 = new ArrayList<>();
	ArrayList<Double> rsi5 = new ArrayList<>();
	ArrayList<Double> rsi10 = new ArrayList<>();
	ArrayList<Double> macd12 = new ArrayList<>();
	ArrayList<Double> macd16 = new ArrayList<>();
	XYChart.Series<String,Number> series1 = new XYChart.Series<>(); 
	XYChart.Series<String,Number> series2 = new XYChart.Series<>(); 
	XYChart.Series<String,Number> ave1 = new XYChart.Series<>();
	XYChart.Series<String,Number> ave2 = new XYChart.Series<>();
	XYChart.Series<String,Number> rsi1 = new XYChart.Series<>();
	XYChart.Series<String,Number> rsi2 = new XYChart.Series<>();
	XYChart.Series<String,Number> ma12 = new XYChart.Series<>();
	XYChart.Series<String,Number> ma16 = new XYChart.Series<>(); 
	
	Timeline fps = new Timeline(new KeyFrame(Duration.seconds(0.5),(e)-> {
		onNextClicked();
	}));
	
	@FXML
	    public void initialize() throws IOException {
		 _Slide.valueProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
					_Chart.getData().clear();
					series1.getData().clear();
					_Amount.getData().clear();
					series2.getData().clear();
					ave1.getData().clear();
					ave2.getData().clear();
					rsi1.getData().clear();
					rsi2.getData().clear();
					ma12.getData().clear();
					ma16.getData().clear();
					_Average.getData().clear();
					_MACD.getData().clear();
					for(int i= data-(int)_Slide.getValue(); i<data;i++) {
						series1.getData().add(new Data<String, Number>(String.valueOf(i), close.get(i))); 
						series2.getData().add(new Data<String, Number>(String.valueOf(i), amount.get(i))); 
						ave1.getData().add(new Data<String, Number>(String.valueOf(i), average10.get(i))); 
						ave2.getData().add(new Data<String, Number>(String.valueOf(i), average30.get(i)));
						rsi1.getData().add(new Data<String, Number>(String.valueOf(i), rsi5.get(i))); 
						rsi2.getData().add(new Data<String, Number>(String.valueOf(i), rsi10.get(i))); 
						ma12.getData().add(new Data<String, Number>(String.valueOf(i), macd12.get(i))); 
						ma16.getData().add(new Data<String, Number>(String.valueOf(i), macd16.get(i))); 
					}
					_Chart.getData().add(series1); 
					_Amount.getData().add(series2);
					_Average.getData().add(ave1);
					_Average.getData().add(ave2);
					_MACD.getData().add(rsi1);
				}
	        });
		 FileInputStream fis = null;
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\finalProject\\data\\"+account+".txt");
			} catch (FileNotFoundException e) {e.printStackTrace();} //declare a file input stream under the path
			byte[] allbytes = null;
			allbytes = fis.readAllBytes();
			String content = new String(allbytes, "UTF-8");
		 money=Double.parseDouble(content);
		 
		 fps.setCycleCount(Timeline.INDEFINITE);
		 series1.setName("");
		 series2.setName("");
		 
		 BufferedReader csvReader = new BufferedReader (new FileReader(s)); 
			String Close, Amount, Average10,Average30,RSI5,RSI10,MACD12,MACD16, line;
			while((line = csvReader.readLine()) != null && loop<=500) {
				loop++;
				String[] info = line.split(",");
				if(loop==1)
					continue;
				Close = info[0].trim();
				Amount = info[1].trim();
				Average10= info[2].trim();
				Average30= info[3].trim();
				RSI5= info[4].trim();
				RSI10= info[5].trim();
				MACD12= info[6].trim();
				MACD16= info[7].trim();
				close.add(Double.parseDouble(Close));
				amount.add(Integer.parseInt(Amount));
				average10.add(Double.parseDouble(Average10));
				average30.add(Double.parseDouble(Average30));
				rsi5.add(Double.parseDouble(RSI5));
				rsi10.add(Double.parseDouble(RSI10));
				macd12.add(Double.parseDouble(MACD12));
				macd16.add(Double.parseDouble(MACD16));
			}
			csvReader.close();

		 
		 for(int i= data-(int)_Slide.getValue(); i<data;i++) {
			series1.getData().add(new Data<String, Number>(String.valueOf(i), close.get(i))); 
			series2.getData().add(new Data<String, Number>(String.valueOf(i), amount.get(i))); 
			ave1.getData().add(new Data<String, Number>(String.valueOf(i), average10.get(i))); 
			ave2.getData().add(new Data<String, Number>(String.valueOf(i), average30.get(i)));
			rsi1.getData().add(new Data<String, Number>(String.valueOf(i), rsi5.get(i))); 
			rsi2.getData().add(new Data<String, Number>(String.valueOf(i), rsi10.get(i))); 
			ma12.getData().add(new Data<String, Number>(String.valueOf(i), macd12.get(i))); 
			ma16.getData().add(new Data<String, Number>(String.valueOf(i), macd16.get(i))); 
		 }
		_Chart.getData().add(series1); 
		_Amount.getData().add(series2);
		_Average.getData().add(ave1);
		_Average.getData().add(ave2);
		_MACD.getData().add(rsi1);
		_Amount.setVisible(true);
		data++;
		 
		_Balence.setText(String.format("%.2f",money)+" $");
		_Position.setText(String.format("%.2f",position)+" $");
		_Profit.setText((String.format("%.2f",profit)+" $"));
		_Percent.setText(percent +" %");
		_Message.setText(name +" is loaded!");
		SettingController.name1=name;
		
		
	 }
	@FXML
	AreaChart<String, Number> _Chart;
	@FXML
	BarChart<String, Number> _Amount;
	@FXML
	Label _Message;
	@FXML
	Label _Balence;
	@FXML
	Label _Position;
	@FXML
	Label _Profit;
	@FXML
	Label _Percent;
	@FXML
	CategoryAxis _X;
	@FXML
	NumberAxis _Y;
	@FXML
	Line _H;
	@FXML
	Line _V;
	@FXML
	Label _Price;
	@FXML
	LineChart<String, Number> _Average;
	@FXML
	LineChart<String, Number> _MACD;
	@FXML
	Button _Start;
	@FXML
	Button _Next;
	@FXML
	Slider _Slide;
	
	public void onDrag() {
	}
	public void onStart() {
		
		if(_Start.getText().equals("Start")) {
			fps.play();
			_Start.setText("Stop");
		}
		else {
			fps.pause();
			_Start.setText("Start");
		}
	}
	public final EventHandler<MouseEvent> onIconClicked() {
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/setting.fxml")); //get fxml
	    Parent setting = null;
		try {
			setting = loadder.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Scene settingScene = new Scene(setting); //create a scene
	    SettingController setCtrl = loadder.getController(); //for keyboard input
	    settingScene.setOnKeyPressed(setCtrl.onKeyPressed); //for keyboard input
	    f1.primaryStage.setScene(settingScene); //change scene
		return null;
	}
	public void Move(MouseEvent event) {
		_V.setVisible(true);
		Point2D mouseSceneCoords = new Point2D(event.getSceneX(), event.getSceneY());
		double x = _X.sceneToLocal(mouseSceneCoords).getX();
		double y = _Y.sceneToLocal(mouseSceneCoords).getY();
		_H.setLayoutY(y+93);
		_V.setLayoutX(x+90);
		if(_X.getValueForDisplay(x)==null)
			_V.setVisible(false);
		else
			_Price.setText(Double.toString(close.get(Integer.parseInt(_X.getValueForDisplay(x)))));
			
	    _Message.setText("" +_X.getValueForDisplay(x) + ",  " +_Y.getValueForDisplay(y));
	    
		
	}
	public void onNextClicked(){
		percent=0;
		_Chart.getData().clear();
		series1.getData().clear();
		_Amount.getData().clear();
		series2.getData().clear();
		ave1.getData().clear();
		ave2.getData().clear();
		rsi1.getData().clear();
		rsi2.getData().clear();
		ma12.getData().clear();
		ma16.getData().clear();
		_Average.getData().clear();
		_MACD.getData().clear();
		for(int i= data-(int)_Slide.getValue(); i<data;i++) {
			series1.getData().add(new Data<String, Number>(String.valueOf(i), close.get(i))); 
			series2.getData().add(new Data<String, Number>(String.valueOf(i), amount.get(i))); 
			ave1.getData().add(new Data<String, Number>(String.valueOf(i), average10.get(i))); 
			ave2.getData().add(new Data<String, Number>(String.valueOf(i), average30.get(i)));
			rsi1.getData().add(new Data<String, Number>(String.valueOf(i), rsi5.get(i))); 
			rsi2.getData().add(new Data<String, Number>(String.valueOf(i), rsi10.get(i))); 
			ma12.getData().add(new Data<String, Number>(String.valueOf(i), macd12.get(i))); 
			ma16.getData().add(new Data<String, Number>(String.valueOf(i), macd16.get(i))); 
		}
		_Chart.getData().add(series1); 
		_Amount.getData().add(series2);
		_Average.getData().add(ave1);
		_Average.getData().add(ave2);
		_MACD.getData().add(rsi1);
		//_MACD.getData().addAll(rsi2);
		_Amount.setVisible(true);
		data++;
		
		profit= portion*close.get(data+(int)_Slide.getValue()-1)-position;
		if(portion>=0)
			percent=(int) ((profit/position)*100);
		else
			percent=(int) ((profit/position)*(-100));
		_Balence.setText(String.format("%.2f",money)+" $");
		_Position.setText(String.format("%.2f",position)+" $");
		_Profit.setText((String.format("%.2f",profit)+" $"));
		_Percent.setText(percent +" %");
		
		
		
	}
	
	public void onBuy() {
		_Message.setText("Buy at :"+ close.get(data+(int)_Slide.getValue()-1));
		
		money = money - close.get(data+(int)_Slide.getValue()-1);
		portion++;
		if(portion==0)
			position=profit=percent=0;
		else
			position=position+close.get(data+(int)_Slide.getValue()-1);
		_Balence.setText(String.format("%.2f",money)+" $");
		_Position.setText(String.format("%.2f",position)+" $");
		_Profit.setText((String.format("%.2f",profit)+" $"));
		_Percent.setText(percent +" %");
	}
	public void onSell() {
		_Message.setText("Sell at :"+ close.get(data+(int)_Slide.getValue()-1));
		portion--;
		money = money + close.get(data+(int)_Slide.getValue()-1);
		if(portion==0)
			position=profit=percent=0;
		else
			position=position-close.get(data+(int)_Slide.getValue()-1);
		_Balence.setText(String.format("%.2f",money)+" $");
		_Position.setText(String.format("%.2f",position)+" $");
		_Profit.setText((String.format("%.2f",profit)+" $"));
		_Percent.setText(percent +" %");
	}
	public EventHandler<KeyEvent> onKeyPressed = (e)->{ //get input from keyboard
		if (e.getCode() == KeyCode.N)
			onNextClicked();
		if (e.getCode() == KeyCode.S)
				onIconClicked();
	}; //lambda~
	public final void mouseEnter()throws NumberFormatException, IOException{
		if(SettingController.R==true) {
	    	_MACD.setVisible(true);
	    	R1=true;
	    }
		else {
			_MACD.setVisible(false);
			R1=false;
		}
		if(SettingController.tag==true) {
			data=100;
			loop=0;
			_Chart.getData().clear();
			series1.getData().clear();
			_Amount.getData().clear();
			series2.getData().clear();
			ave1.getData().clear();
			ave2.getData().clear();
			rsi1.getData().clear();
			rsi2.getData().clear();
			ma12.getData().clear();
			ma16.getData().clear();
			_Average.getData().clear();
			_MACD.getData().clear();
			close.clear();
			amount.clear();
			average10.clear();
			average30.clear();
			rsi5.clear();
			rsi10.clear();
			macd12.clear();
			macd16.clear();
			BufferedReader csvReader = new BufferedReader (new FileReader(s)); 
			String Close, Amount, Average10,Average30,RSI5,RSI10,MACD12,MACD16, line;
			while((line = csvReader.readLine()) != null && loop<=500) {
				loop++;
				String[] info = line.split(",");
				if(loop==1)
					continue;
				Close = info[0].trim();
				Amount = info[1].trim();
				Average10= info[2].trim();
				Average30= info[3].trim();
				RSI5= info[4].trim();
				RSI10= info[5].trim();
				MACD12= info[6].trim();
				MACD16= info[7].trim();
				close.add(Double.parseDouble(Close));
				amount.add(Integer.parseInt(Amount));
				average10.add(Double.parseDouble(Average10));
				average30.add(Double.parseDouble(Average30));
				rsi5.add(Double.parseDouble(RSI5));
				rsi10.add(Double.parseDouble(RSI10));
				macd12.add(Double.parseDouble(MACD12));
				macd16.add(Double.parseDouble(MACD16));
			}
			csvReader.close();

		 
		 for(int i= data-(int)_Slide.getValue(); i<data;i++) {
			series1.getData().add(new Data<String, Number>(String.valueOf(i), close.get(i))); 
			series2.getData().add(new Data<String, Number>(String.valueOf(i), amount.get(i))); 
			ave1.getData().add(new Data<String, Number>(String.valueOf(i), average10.get(i))); 
			ave2.getData().add(new Data<String, Number>(String.valueOf(i), average30.get(i)));
			rsi1.getData().add(new Data<String, Number>(String.valueOf(i), rsi5.get(i))); 
			rsi2.getData().add(new Data<String, Number>(String.valueOf(i), rsi10.get(i))); 
			ma12.getData().add(new Data<String, Number>(String.valueOf(i), macd12.get(i))); 
			ma16.getData().add(new Data<String, Number>(String.valueOf(i), macd16.get(i))); 
		 }
		_Chart.getData().add(series1); 
		_Amount.getData().add(series2);
		_Average.getData().add(ave1);
		_Average.getData().add(ave2);
		_MACD.getData().add(rsi1);
		_Amount.setVisible(true);
		data++;
		_Message.setText(name +" is loaded!");
		SettingController.name1=name;
		SettingController.tag=false;
		}
		if(SettingController.a==true) {
			a1=true;
			_Next.setVisible(false);
			_Start.setVisible(true);
		}
		else {
			a1=false;
			_Next.setVisible(true);
			_Start.setVisible(false);
		}
	}
}
