package application;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class MainMenuController implements Initializable {
		
		@FXML
		private TreeTableView<String> treeTableMenu;
		@FXML
		private TreeTableColumn<String, String> treeTableMenuColumn;
		
		public static boolean isSplashLoaded;
		
		TreeItem<String> item_l1 = new TreeItem<>("Scheduler");
		TreeItem<String> item_l2 = new TreeItem<>("Managing");
		TreeItem<String> parent1 = new TreeItem<>("Employee Management");
		
		TreeItem<String> item_r1 = new TreeItem<>("Attendance");
		TreeItem<String> item_r2 = new TreeItem<>("Bar Chart");
		TreeItem<String> item_r3 = new TreeItem<>("Line Chart");
		TreeItem<String> parent2 = new TreeItem<>("Customer Management");
		
		TreeItem<String> rootie = new TreeItem<>("Menu");
		
		@FXML
		private BorderPane root;
		
		public static BorderPane rootP;
		
		@Override
		public void initialize(URL location, ResourceBundle resources){
			parent1.getChildren().setAll(item_l1, item_l2);
			parent2.getChildren().setAll(item_r1, item_r2, item_r3);
			rootie.getChildren().setAll(parent1, parent2);
			
			treeTableMenuColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String, String>, ObservableValue<String>>(){
				@Override
				public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<String, String> param){
					return new SimpleStringProperty(param.getValue().getValue());
					
				}
			});
		
			treeTableMenu.setRoot(rootie);
			treeTableMenu.setShowRoot(false);
			parent1.setExpanded(true);
			parent2.setExpanded(true);
			rootP = root;
			/*if(!isSplashLoaded){
				loadSplashScreen();
			}
			*/
			 treeTableMenu.getSelectionModel()
	        .selectedItemProperty()
	        .addListener((observable, oldValue, newValue) -> System.out.println("Selected Text : " + newValue.getValue()));
	      
			
		}
		
		private void loadSplashScreen(){
			try {
				isSplashLoaded = true;
				
				AnchorPane pane = FXMLLoader.load(getClass().getResource("WelcomeSplashScreen.fxml"));
				root.getChildren().setAll(pane);
				
				FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
				fadeIn.setFromValue(0);
				fadeIn.setToValue(1);
				fadeIn.setCycleCount(1);
				
				FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
				fadeOut.setFromValue(1);
				fadeOut.setToValue(0);
				fadeOut.setCycleCount(1);
				
				fadeIn.play();
				fadeIn.setOnFinished((e) -> {
					fadeOut.play();
				});
				
				fadeOut.setOnFinished((e) -> {
					try {
						BorderPane parentContent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
						root.getChildren().setAll(parentContent);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				
				
				
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
}


