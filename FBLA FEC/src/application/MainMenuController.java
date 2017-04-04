package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.FadeTransition;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class MainMenuController implements Initializable {
		
		public static boolean isSplashLoaded;
		@FXML
		private BorderPane root;
		
		public static BorderPane rootP;
		
		@Override
		public void initialize(URL location, ResourceBundle resources){
			rootP = root;
			if(!isSplashLoaded){
				loadSplashScreen();
			}
			
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

