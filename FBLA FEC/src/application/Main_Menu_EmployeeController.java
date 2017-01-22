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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class Main_Menu_EmployeeController implements Initializable {
	
	public Main_Menu_EmployeeModel Employee_Table_Screen = new Main_Menu_EmployeeModel();
	
	@FXML
	private Button txtAdd;
	@FXML
	private Button MainMenuSaveButton;
	@FXML
	private Button MainMenuClearButton;
	@FXML
	private TextField txtFirst_Name;
	@FXML
	private TextField txtLast_Name;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtSearch;
	@FXML
	private DatePicker dtDOB;
	@FXML
	private TableView<Main_Menu_EmployeeModel> TableEmployees;
	@FXML
	private TableColumn<Main_Menu_EmployeeModel, String> EmployeesFirst_Name;
	@FXML
	private TableColumn<Main_Menu_EmployeeModel, String> EmployeesLast_Name;
	@FXML
	private TableColumn<Main_Menu_EmployeeModel, String> EmployeesID;
	@FXML
	private TableColumn<Main_Menu_EmployeeModel, String> EmployeesEmail;
	
	
	private boolean isMainMenuAddNewButtonClick;
	private boolean isMainMenuEditButtonClick;
	
	Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    private int temp;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Get data from adminTableData ObservableList and set this data on JavaFX table column

        EmployeesFirst_Name.setCellValueFactory(new PropertyValueFactory<Main_Menu_EmployeeModel,String>("EmployeesFirst_Name")); 
        EmployeesLast_Name.setCellValueFactory(new PropertyValueFactory<Main_Menu_EmployeeModel,String>("EmployeesLast_Name"));
        EmployeesID.setCellValueFactory(new PropertyValueFactory<Main_Menu_EmployeeModel,String>("EmployeesID"));
        EmployeesEmail.setCellValueFactory(new PropertyValueFactory<Main_Menu_EmployeeModel,String>("EmployeesEmail"));
        
        TableEmployees.setItems(Employee_Table_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM EMPLOYEES"));
        dtDOB.setEditable(false);
    }
	
	@FXML
    private void setMainMenuAddNewButtonClick(Event event){
        MainMenuSetAllEnable();
        MainMenuSetAllClear();
        isMainMenuAddNewButtonClick = true;
    }
	
	private void MainMenuSetAllEnable(){
        txtFirst_Name.setDisable(false);
        txtLast_Name.setDisable(false);
        txtPhone.setDisable(false);
        txtAddress.setDisable(false);
        txtEmail.setDisable(false);
        dtDOB.setDisable(false);
        

        MainMenuSaveButton.setDisable(false);
        MainMenuClearButton.setDisable(false);

    }
	
	private void MainMenuSetAllDisable(){
        txtFirst_Name.setDisable(true);
        txtLast_Name.setDisable(true);
        txtPhone.setDisable(true);
        txtAddress.setDisable(true);
        txtEmail.setDisable(true);
        dtDOB.setDisable(true);
        

        MainMenuSaveButton.setDisable(true);
        MainMenuClearButton.setDisable(true);

    }
	
	
	
	
	 private void MainMenuSetAllClear(){
		 	txtFirst_Name.clear();
	        txtLast_Name.clear();
	        txtPhone.clear();
	        txtAddress.clear();
	        txtEmail.clear();
	        dtDOB.setValue(null);
	       	        
	    }
	 
	 @FXML
	 private void MainMenuSetAllClear(Event event){
		 	txtFirst_Name.clear();
	        txtLast_Name.clear();
	        txtPhone.clear();
	        txtAddress.clear();
	        txtEmail.clear();
	        dtDOB.setValue(null);
	       	        
	    }
	
	 @FXML
	    private void setMainMenuSaveButtonClick(Event event){
	        try{	       
	  
	            if(validateFirstName() && validateLastName() && validateEmail() && validatePhone() && validateDOB() && validateAddress()){
	            	
	            	connection = SqliteConnection.Connector();
		            statement = connection.createStatement();
		            
	            	if(isMainMenuAddNewButtonClick){
		                int rowsAffected = statement.executeUpdate("insert into`Employees` "+
		                        "(`First_Name`,`Last_Name`,`Email`,`Phone`,"+
		                        "`Address`,`DOB`"+
		                        ") "+
		                        "values ('"+txtFirst_Name.getText()+"','"+txtLast_Name.getText()+"','"+txtEmail.getText()
		                        +"','"+txtPhone.getText()
		                        +"','"+txtAddress.getText()
		                        +"','"+dtDOB.getValue().toString()
		                        +"'); ");
		           
		            }
		            else if (isMainMenuEditButtonClick){

		                int rowsAffected = statement.executeUpdate("update Employees set "+
		                        "First_Name = '"+txtFirst_Name.getText()+"',"+
		                        "Last_Name = '"+txtLast_Name.getText()+"',"+                      
		                        "Email = '"+txtEmail.getText()+"',"+
		                        "Phone = '"+txtPhone.getText()+"',"+
		                        "Address = '"+txtAddress.getText()+"',"+
		                        "DOB = '"+dtDOB.getValue()+
		                        "' where ID = "+
		                        temp+";");	               
		            }

		            statement.close();
		            connection.close();
		            
		            MainMenuSetAllClear();
			        MainMenuSetAllDisable();
			        TableEmployees.setItems(Employee_Table_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Employees;"));
			        isMainMenuEditButtonClick=false;
			        isMainMenuAddNewButtonClick = false;
			        
					txtFirst_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
					txtLast_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
					txtAddress.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
					txtEmail.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
					txtPhone.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
					dtDOB.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");

	            }
	        }
		        catch (SQLException e){
		            e.printStackTrace();
		        }
		        
		    }
	            
	            
	            
	 
	 @FXML
	    private void setMainMenuEditButtonClick(Event event){
	        
	     if(TableEmployees.getSelectionModel().getSelectedItem()!=null) {
	    	 Main_Menu_EmployeeModel getSelectedRow = TableEmployees.getSelectionModel().getSelectedItem();
	        	String sqlQuery = "select * FROM Employees where ID = "+getSelectedRow.getEmployeesID()+";";
	        	 
	        try {
	        	 connection = SqliteConnection.Connector();
		         statement = connection.createStatement();
	             resultSet = statement.executeQuery(sqlQuery);
	        
	             MainMenuSetAllEnable();
	             while(resultSet.next()) {
	                 txtFirst_Name.setText(resultSet.getString("First_Name"));
	                 txtLast_Name.setText(resultSet.getString("Last_Name"));
	                 txtEmail.setText(resultSet.getString("Email"));
	                 txtPhone.setText(resultSet.getString("Phone"));
	                 txtAddress.setText(resultSet.getString("Address"));
	                 dtDOB.setValue(LocalDate.parse(resultSet.getString("DOB")));
	                 temp = resultSet.getInt("ID");
	 	             isMainMenuEditButtonClick = true;
	            }

	            
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }

	     }
	     else{
	    	    NotificationType notificationType = NotificationType.ERROR;
	            TrayNotification tray = new TrayNotification();
	            tray.setTitle("No Employee Selected");
	            tray.setMessage("To edit, please select an Employee from the table");
	            tray.setNotificationType(notificationType);
	            tray.showAndDismiss(Duration.millis(5000));
	     }
		 		
	    }
	 
	 @FXML
	    private void setMainMenuDeleteButtonClick(Event event){
		 	TableEmployees.setPlaceholder(new Label("No Employees"));
		 	if(TableEmployees.getSelectionModel().getSelectedItem()!=null){
		 		Main_Menu_EmployeeModel getSelectedRow = TableEmployees.getSelectionModel().getSelectedItem();
		        String sqlQuery = "delete from Employees where ID = '"+getSelectedRow.getEmployeesID()+"';";
		        String sqlQuery2 = "delete from Employees_Schedule where ID = '"+getSelectedRow.getEmployeesID()+"';";
		        
		        try {
		        	connection = SqliteConnection.Connector();
			        statement = connection.createStatement();
		             
		            statement.executeUpdate(sqlQuery);
		            statement.executeUpdate(sqlQuery2);
		            //statement.executeUpdate("delete from Employees where ID ='"+getSelectedRow.getEmployeesID()+"';");
		            TableEmployees.setItems(Employee_Table_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Employees;"));
		            statement.close();
		            connection.close();

		        }
		        catch (SQLException e) {
		            e.printStackTrace();
		        }


		        //adminTableView.setItems(getDataFromSqlAndAddToObservableList(sqlQuery));
		 	}
		 	else{
		 		NotificationType notificationType = NotificationType.ERROR;
	            TrayNotification tray = new TrayNotification();
	            tray.setTitle("No Employee Selected");
	            tray.setMessage("To delete, please select an Employee from the table");
	            tray.setNotificationType(notificationType);
	            tray.showAndDismiss(Duration.millis(5000));
		 	}        
	    }
	 
	 @FXML
	    private void setMainMenuSearchButtonClick(Event event){
	        String sqlQuery = "select * FROM Employees where ID = '"+txtSearch.getText()+"';";
	        TableEmployees.setItems(Employee_Table_Screen.getDataFromSqlAndAddToObservableList(sqlQuery));
	    }
	 
	 @FXML
	    private void setMainMenuRefreshButtonClick(Event event){
	        TableEmployees.setItems(Employee_Table_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Employees;"));//sql Query
	        txtSearch.clear();
	    }
	 
	 @FXML
	    private void launchScheduler(Event event) throws IOException{
		 	((Node)event.getSource()).getScene().getWindow().hide();
		 	Parent Scheduler = FXMLLoader.load(getClass().getResource("Employee_Shift_Scheduler.fxml"));
		 	Scene scheduler = new Scene(Scheduler);
		 	Stage Schedule = (Stage) ((Node) event.getSource()).getScene().getWindow();
		 	Schedule.hide();
		 	Schedule.setScene(scheduler);
		 	Schedule.setTitle("Scheduler");
		 	Schedule.show();
	    }
	 

	 @FXML
	    private void launchCustomerScreen(Event event) throws IOException{
		 	((Node)event.getSource()).getScene().getWindow().hide();
		 	Parent CustomerScreen = FXMLLoader.load(getClass().getResource("Menu_Customer.fxml"));
		 	Scene customer_screen = new Scene(CustomerScreen);
		 	Stage Customer_Screen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		 	Customer_Screen.hide();
		 	Customer_Screen.setScene(customer_screen);
		 	Customer_Screen.setTitle("Customer Screen");
		 	Customer_Screen.show();
	    }
	 
	 @FXML
	    private void launchBarChart(Event event) throws IOException{
		 	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("AMPM_Bar_Chart.fxml"));
	        loader.load();
	        Parent p = loader.getRoot();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(p));
	        stage.setTitle("All Customer Attendance Data");
	        stage.show();
	    }
	 
	 @FXML
	    private void launchLineChart(Event event) throws IOException{
		 	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("Customer_Attendance_Line_Chart.fxml"));
	        loader.load();
	        Parent p = loader.getRoot();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(p));
	        stage.setTitle("Week Customer Attendance Data");
	        stage.show();
	    }

	 @FXML
	    private void setMainMenuViewButtonClick(Event event) throws IOException {

		 if(TableEmployees.getSelectionModel().getSelectedItem()!=null){
			 	Employee_Profile_ViewModel info = new Employee_Profile_ViewModel(TableEmployees.getSelectionModel().getSelectedItem().getEmployeesID().toString());
			 	FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(getClass().getResource("Employee_Profile_View.fxml"));
		        loader.load();
		        Parent p = loader.getRoot();
		        Stage stage = new Stage();
		        stage.setScene(new Scene(p));

		        Employee_Profile_ViewController profileView = loader.getController();
		        profileView.setCurrentInfo(info);
		        stage.show();
		 }
		 else{
			    NotificationType notificationType = NotificationType.ERROR;
	            TrayNotification tray = new TrayNotification();
	            tray.setTitle("No Employee Selected");
	            tray.setMessage("Select Employee to View");
	            tray.setNotificationType(notificationType);
	            tray.showAndDismiss(Duration.millis(5000));
		 }
	        
	    }
	 
	 private boolean validateFirstName(){
		 Pattern p = Pattern.compile("[a-zA-z]+");
		 Matcher m = p.matcher(txtFirst_Name.getText());
		 if(m.find() && m.group().equals(txtFirst_Name.getText())){
			 return true;
		 }
		 else{
			 Alert alert = new Alert(AlertType.WARNING);
		   	 alert.setTitle("Validate Name");
		   	 alert.setHeaderText(null);
			 alert.setContentText("Please Enter a Valid First Name");
			 alert.showAndWait();
			 txtFirst_Name.clear();
			 txtFirst_Name.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			 return false;
		}
	}
	
	 
	 private boolean validateLastName(){
		 Pattern p = Pattern.compile("[a-zA-z]+");
		 Matcher m = p.matcher(txtLast_Name.getText());
		 if(m.find() && m.group().equals(txtLast_Name.getText())){
			 return true;
		 }
		 else{
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Validate Name");
			 alert.setHeaderText(null);
			 alert.setContentText("Please Enter a Valid Last Name");
			 alert.showAndWait();
			 txtLast_Name.clear();
			 txtLast_Name.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			 return false;
		 }
	 }
	 
	 private boolean validateEmail(){
		 Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		 Matcher m = p.matcher(txtEmail.getText());
		 if(m.find() && m.group().equals(txtEmail.getText())){
			 return true;
		 }
		 else{
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Validate Email");
			 alert.setHeaderText(null);
			 alert.setContentText("Please Enter a Valid Email");
			 alert.showAndWait();
			 txtEmail.clear();
			 txtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			 return false;
		 }
	 }
	 
	 private boolean validatePhone(){
		 Pattern p = Pattern.compile("[0-9]{3}[-][0-9]{3}[-][0-9]{4}");
		 Matcher m = p.matcher(txtPhone.getText());
		 if(m.find() && m.group().equals(txtPhone.getText())){
			 return true;
		 }
		 else{
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Validate Phone Number");
			 alert.setHeaderText(null);
			 alert.setContentText("Please Enter a Valid Phone Number (aaa-aaa-aaaa)");
			 alert.showAndWait();
			 txtPhone.clear();
			 txtPhone.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			 return false;
		 }
	 }
	 
	 private boolean validateDOB(){
		 if(dtDOB.getValue() != null){
			 return true;
		 }
		 else{
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Validate Date");
			 alert.setHeaderText(null);
			 alert.setContentText("Please Enter a DOB");
			 alert.showAndWait();
			 dtDOB.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			 return false;
		 }
		 
	 }
	 
	 private boolean validateAddress(){
		 if(txtAddress.getText() != null){
			 return true;
		 }
		 else{
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Validate Address");
			 alert.setHeaderText(null);
			 alert.setContentText("Please Enter an Address");
			 alert.showAndWait();
			 txtAddress.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			 return false;
		 } 
	 }
	 
	 @FXML
	 public void setOnSearchKeyPressed(KeyEvent event) throws IOException{
		 if(txtSearch.getText()!=""){
			 String sqlQuery = "select * FROM Employees where First_Name like '%"+txtSearch.getText()+"%' OR "
			 		+ "Last_Name like '%"+txtSearch.getText() + "%';";
			 if(Employee_Table_Screen.getDataFromSqlAndAddToObservableList(sqlQuery)==null){
		    	 TableEmployees.setPlaceholder(new Label("No Employee With Given Name"));
		     }
			 TableEmployees.setItems(Employee_Table_Screen.getDataFromSqlAndAddToObservableList(sqlQuery));
		 }
		 else{
			 TableEmployees.setItems(Employee_Table_Screen.getDataFromSqlAndAddToObservableList("select * FROM Employees"));
		 }
	 } 
	 
	 @FXML
	 public void setOnPhoneKeyReleased(KeyEvent event) throws IOException{
		 if(txtPhone.getText().length()==3){
			 txtPhone.setText(txtPhone.getText()+"-");
			 txtPhone.positionCaret(4);
		 }
		 else if(txtPhone.getText().length()==7){
			 txtPhone.setText(txtPhone.getText()+"-");
			 txtPhone.positionCaret(8);
		 }
	 }
	 
	 @FXML
	 public void handleDoubleClick(MouseEvent mouseEvent) throws IOException {
	        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
	            if(mouseEvent.getClickCount() == 2){
	            	if(TableEmployees.getSelectionModel().getSelectedItem()!=null){
	    			 	Employee_Profile_ViewModel info = new Employee_Profile_ViewModel(TableEmployees.getSelectionModel().getSelectedItem().getEmployeesID().toString());
	    			 	FXMLLoader loader = new FXMLLoader();
	    		        loader.setLocation(getClass().getResource("Employee_Profile_View.fxml"));
	    		        loader.load();
	    		        Parent p = loader.getRoot();
	    		        Stage stage = new Stage();
	    		        stage.setScene(new Scene(p));

	    		        Employee_Profile_ViewController profileView = loader.getController();
	    		        profileView.setCurrentInfo(info);
	    		        stage.show();
	    		 }
	    		 else{
	    			    NotificationType notificationType = NotificationType.ERROR;
	    	            TrayNotification tray = new TrayNotification();
	    	            tray.setTitle("No Employee Selected");
	    	            tray.setMessage("Select Employee to View");
	    	            tray.setNotificationType(notificationType);
	    	            tray.showAndDismiss(Duration.millis(5000));
	    		 }
	            }
	        }
	    }
}

	 
	


	

