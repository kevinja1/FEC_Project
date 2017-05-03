package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.sun.prism.paint.Color;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class Main_Menu_Controller implements Initializable {
	
	public Main_Menu_EmployeeModel Employee_Table_Screen = new Main_Menu_EmployeeModel();
	
    private int temp;
    
    @FXML
	private TreeTableView<String> treeTableMenu;
	@FXML
	private TreeTableColumn<String, String> treeTableMenuColumn;
	@FXML
	private JFXDrawer topDrawer;
	@FXML
	private JFXButton button1;
	@FXML
	private HBox hbMenu;
	
	
	TreeItem<String> item_l1 = new TreeItem<>("Scheduler");
	TreeItem<String> item_l2 = new TreeItem<>("Details");
	TreeItem<String> parent1 = new TreeItem<>("Employee");
	
	TreeItem<String> item_r1 = new TreeItem<>("Attendance");
	TreeItem<String> item_r2 = new TreeItem<>("Bar Chart");
	TreeItem<String> item_r3 = new TreeItem<>("Line Chart");
	TreeItem<String> parent2 = new TreeItem<>("Customer");
	
	TreeItem<String> rootie = new TreeItem<>("Menu");
	
	@FXML
	private BorderPane root;
	
	public static BorderPane rootP;
	
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//"Configures" the value of each column in the table
    	Image image = new Image(getClass().getResourceAsStream("/FBLA FEC/resources/ic_perm_identity_white_48pt_2x.png"));
        button1.setGraphic(new ImageView(image));
        
    	
    	/*treeTableMenu.setStyle("-fx-focus-color: transparent;");
    	treeTableMenuColumn.setStyle("-fx-focus-color: transparent;");
*/
    	for(Node node: hbMenu.getChildren()){
    		if(node.getAccessibleText()!=null){
    			node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
    				switch(node.getAccessibleText()){
    				case "Employee Details":
    					BorderPane pane;
    					try {
    						pane = FXMLLoader.load(getClass().getResource("Main_Menu_Employee.fxml"));
    						root.getChildren().setAll(pane);
    					} catch (IOException e3) {
    						// TODO Auto-generated catch block
    						e3.printStackTrace();
    					}
    				case "Schedule Employee":
    					AnchorPane pane1;
    					try {
    						pane1 = FXMLLoader.load(getClass().getResource("Employee_Shift_Scheduler.fxml"));
    						root.getChildren().setAll(pane1);
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    				case "Customer Attendance":
    					AnchorPane pane2;
    					try {
    						pane2 = FXMLLoader.load(getClass().getResource("Menu_Customer.fxml"));
    						root.getChildren().setAll(pane2);
    					} catch (IOException e2) {
    						// TODO Auto-generated catch block
    						e2.printStackTrace();
    					}
    				case "Reports":
    				
    				case "About":
    				
    				case "Exit":
    				}
    			});
    		}
    	}
        /*
    	parent1.getChildren().setAll(item_l2, item_l1);
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
	
		treeTableMenu.getSelectionModel()
	        .selectedItemProperty()
	        .addListener((observable, oldValue, newValue) -> {
	        	if(newValue.getValue() == "Details"){
	        		BorderPane pane;
					try {
						pane = FXMLLoader.load(getClass().getResource("Main_Menu_Employee.fxml"));
						root.getChildren().setAll(pane);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	        	}
	        	else if(newValue.getValue() == "Scheduler"){
	        		AnchorPane pane;
					try {
						pane = FXMLLoader.load(getClass().getResource("Employee_Shift_Scheduler.fxml"));
						root.getChildren().setAll(pane);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	        	}
	        	else if(newValue.getValue() == "Attendance"){
	        		AnchorPane pane;
					try {
						pane = FXMLLoader.load(getClass().getResource("Menu_Customer.fxml"));
						root.getChildren().setAll(pane);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	        	}
	        	else if(newValue.getValue() == "Bar Chart"){
	        		FXMLLoader loader = new FXMLLoader();
	    	        loader.setLocation(getClass().getResource("AMPM_Bar_Chart.fxml"));
	    	        try {
						loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        Parent p = loader.getRoot();
	    	        Stage stage = new Stage();
	    	        stage.setScene(new Scene(p));
	    	        stage.setTitle("All Customer Attendance Data");
	    	        stage.show();
	        	}
	        	else if(newValue.getValue() == "Line Chart"){
	        		FXMLLoader loader = new FXMLLoader();
	    	        loader.setLocation(getClass().getResource("Customer_Attendance_Line_Chart.fxml"));
	    	        try {
						loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        Parent p = loader.getRoot();
	    	        Stage stage = new Stage();
	    	        stage.setScene(new Scene(p));
	    	        stage.setTitle("Week Customer Attendance Data");
	    	        stage.show();
	        	}
	        }
	        );
		
		*/
    }	
}


	
