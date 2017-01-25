package application;

import java.awt.Insets;
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

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class Menu_CustomerController implements Initializable {

	public Menu_CustomerModel Customers_Table_Screen = new Menu_CustomerModel();
	public Menu_Customer_AttendanceModel Customers_Table_Attendance_Screen = new Menu_Customer_AttendanceModel();

	@FXML
	AnchorPane ancpane;
	@FXML
	private Button txtAdd;
	@FXML
	private Button btShowAllAtt;
	@FXML
	private ToggleGroup group;
	@FXML
	private RadioButton rdAM;
	@FXML
	private RadioButton rdPM;
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
	private DatePicker dtDOB;
	@FXML
	private DatePicker dtAttendance;
	@FXML
	private Button CustomerSaveButton;
	@FXML
	private Button CustomerClearButton;
	@FXML
	private Button DeleteAttendanceButton;
	@FXML
	private Button SaveAttendanceButton;
	@FXML
	private TextField txtSearch;
	@FXML
	private TableView<Menu_CustomerModel> TableCustomers;
	@FXML
	private TableColumn<Menu_CustomerModel, String> CustomersID;
	@FXML
	private TableColumn<Menu_CustomerModel, String> CustomersFirst_Name;
	@FXML
	private TableColumn<Menu_CustomerModel, String> CustomersLast_Name;
	@FXML
	private TableColumn<Menu_CustomerModel, String> CustomersEmail;
	@FXML
	private TableView<Menu_Customer_AttendanceModel> TableCustomerAttendance;
	@FXML
	private TableColumn<Menu_Customer_AttendanceModel, String> CustomerAttFirst_Name;
	@FXML
	private TableColumn<Menu_Customer_AttendanceModel, String> CustomerAttLast_Name;
	@FXML
	private TableColumn<Menu_Customer_AttendanceModel, String> CustomerAttDate;
	@FXML
	private TableColumn<Menu_Customer_AttendanceModel, String> CustomerAttAMPM;

	private boolean isCustomersAddNewButtonClick;
	private boolean isCustomersEditButtonClick;

	Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	private int temp;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//Get data from adminTableData ObservableList and set this data on JavaFX table column

		CustomersFirst_Name.setCellValueFactory(new PropertyValueFactory<Menu_CustomerModel,String>("CustomersFirst_Name")); 
		CustomersLast_Name.setCellValueFactory(new PropertyValueFactory<Menu_CustomerModel,String>("CustomersLast_Name"));
		CustomersID.setCellValueFactory(new PropertyValueFactory<Menu_CustomerModel,String>("Customers_ID"));
		CustomersEmail.setCellValueFactory(new PropertyValueFactory<Menu_CustomerModel,String>("Customers_Email"));

		CustomerAttFirst_Name.setCellValueFactory(new PropertyValueFactory<Menu_Customer_AttendanceModel,String>("Customers_FirstName")); 
		CustomerAttLast_Name.setCellValueFactory(new PropertyValueFactory<Menu_Customer_AttendanceModel,String>("Customers_LastName"));
		CustomerAttDate.setCellValueFactory(new PropertyValueFactory<Menu_Customer_AttendanceModel,String>("Customers_Date"));
		CustomerAttAMPM.setCellValueFactory(new PropertyValueFactory<Menu_Customer_AttendanceModel,String>("Customers_AMPM"));

		TableCustomers.setItems(Customers_Table_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Customers"));
		//TableCustomerAttendance.setItems(Customers_Table_Attendance_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Customers_Attendance"));
		TableCustomerAttendance.setItems(Customers_Table_Attendance_Screen.getDataFromSqlAndAddToObservableList("SELECT Customers_Attendance.*, Customers.ID, Customers.First_Name, Customers.Last_Name FROM"
				+ " Customers_Attendance INNER JOIN Customers ON Customers_Attendance.ID=Customers.ID;"));
		CustomersAttendanceSetAllDisable();
		btShowAllAtt.setDisable(false); 
		rdAM.setSelected(true);

		dtDOB.setEditable(false);
		dtAttendance.setEditable(false);
		
	}

	@FXML
	private void setCustomersAddNewButtonClick(Event event){
		CustomersSetAllEnable();
		isCustomersAddNewButtonClick = true;
	}

	private void CustomersSetAllEnable(){
		txtFirst_Name.setDisable(false);
		txtLast_Name.setDisable(false);
		txtEmail.setDisable(false);
		txtPhone.setDisable(false);
		txtAddress.setDisable(false);
		dtDOB.setDisable(false);


		CustomerSaveButton.setDisable(false);
		CustomerClearButton.setDisable(false);

	}

	private void CustomersSetAllDisable(){
		txtFirst_Name.setDisable(true);
		txtLast_Name.setDisable(true);
		txtEmail.setDisable(true);
		txtPhone.setDisable(true);
		txtAddress.setDisable(true);
		dtDOB.setDisable(true);



		CustomerSaveButton.setDisable(true);
		CustomerClearButton.setDisable(true);

	}

	private void CustomersAttendanceSetAllDisable(){
		//DeleteAttendanceButton.setDisable(true);
		dtAttendance.setDisable(true);
		SaveAttendanceButton.setDisable(true);
		rdAM.setDisable(true);
		rdPM.setDisable(true);
		//TableCustomerAttendance.setDisable(true);
	}


	private void CustomersAttendanceSetAllEnable(){
		DeleteAttendanceButton.setDisable(false);
		dtAttendance.setDisable(false);
		SaveAttendanceButton.setDisable(false);
		rdAM.setDisable(false);
		rdPM.setDisable(false);
		//TableCustomerAttendance.setDisable(false);
	}




	private void CustomersSetAllClear(){
		txtFirst_Name.clear();
		txtLast_Name.clear();
		txtEmail.clear();
		txtPhone.clear();
		txtAddress.clear();
		dtDOB.setValue(null);



	}

	private void CustomersAttendanceSetAllClear(){
		dtAttendance.setValue(null);

	}

	@FXML
	private void CustomersSetAllClear(Event event){
		txtFirst_Name.clear();
		txtLast_Name.clear();
		txtEmail.clear();
		txtPhone.clear();
		txtAddress.clear();
		dtDOB.setValue(null);

		txtFirst_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
		txtLast_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
		txtAddress.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
		txtEmail.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
		txtPhone.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
		dtDOB.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");


	}

	@FXML
	private void setCustomerSaveButtonClick(Event event){
		try{	       
			if(validateFirstName() && validateLastName() && validateEmail() && validatePhone() && validateDOB() && validateAddress()){
				connection = SqliteConnection.Connector();
				statement = connection.createStatement();

				if(isCustomersAddNewButtonClick){
					isCustomersEditButtonClick = true;
					int rowsAffected = statement.executeUpdate("insert into`Customers` "+
							"(`First_Name`,`Last_Name`,`Email`,`Phone`,"+
							"`Address`,`DOB`"+
							") "+
							"values ('"+txtFirst_Name.getText()+"','"+txtLast_Name.getText()+"','"+txtEmail.getText()
							+"','"+txtPhone.getText()
							+"','"+txtAddress.getText()
							+"','"+dtDOB.getValue().toString()


							+"'); ");

				}
				else if (isCustomersEditButtonClick){
					isCustomersAddNewButtonClick = false;
					int rowsAffected = statement.executeUpdate("update Customers set "+
							"First_Name = '"+txtFirst_Name.getText()+"',"+
							"Last_Name = '"+txtLast_Name.getText()+"',"+
							"Email = '"+txtEmail.getText()+"',"+
							"Phone = '"+txtPhone.getText()+"',"+
							"Address = '"+txtAddress.getText()+"',"+
							"DOB = '"+dtDOB.getValue()+
							"' where ID = '"+
							temp+"';");
					/* if (temp.equals(txtID.getText())){
		                    statement.executeUpdate("update studentgpa set dbstudentgpaID ='"+adminTFStudentID.getText()+"' where dbStudentID = '"+ temp+"';");
		                }
					 */
				}

				statement.close();
				connection.close();

				CustomersSetAllClear();
				CustomersSetAllDisable();
				TableCustomers.setItems(Customers_Table_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Customers;"));
				isCustomersEditButtonClick=false;
				isCustomersAddNewButtonClick = false;

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
	private void setCustomerEditButtonClick(Event event){

		if(TableCustomers.getSelectionModel().getSelectedItem()!=null) {
			Menu_CustomerModel getSelectedRow = TableCustomers.getSelectionModel().getSelectedItem();
			String sqlQuery = "select * FROM Customers where ID = "+getSelectedRow.getCustomers_ID()+";"; 
			try {
				connection = SqliteConnection.Connector();
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sqlQuery);

				CustomersSetAllEnable();
				while(resultSet.next()) {
					txtFirst_Name.setText(resultSet.getString("First_Name"));
					txtLast_Name.setText(resultSet.getString("Last_Name"));
					txtEmail.setText(resultSet.getString("Email"));
					txtPhone.setText(resultSet.getString("Phone"));
					txtAddress.setText(resultSet.getString("Address"));
					dtDOB.setValue(LocalDate.parse(resultSet.getString("DOB")));
					temp = resultSet.getInt("ID");

				}


				isCustomersEditButtonClick = true;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else{
			NotificationType notificationType = NotificationType.ERROR;
			TrayNotification tray = new TrayNotification();
			tray.setTitle("No Customer Selected");
			tray.setMessage("To edit, please select a Customer from the table");
			tray.setNotificationType(notificationType);
			tray.showAndDismiss(Duration.millis(5000));
		}

	}

	@FXML
	private void setCustomerDeleteButtonClick(Event event){
		TableCustomers.setPlaceholder(new Label("No Customers"));	
		if(TableCustomers.getSelectionModel().getSelectedItem()!=null){
			Menu_CustomerModel getSelectedRow = TableCustomers.getSelectionModel().getSelectedItem();
			String sqlQuery = "delete from Customers where ID = '"+getSelectedRow.getCustomers_ID()+"';";
			String sqlQuery2 = "delete from Customers_Attendance where ID = '"+getSelectedRow.getCustomers_ID()+"';";

			try {
				connection = SqliteConnection.Connector();
				statement = connection.createStatement();

				statement.executeUpdate(sqlQuery);
				statement.executeUpdate("delete from Customers where ID ='"+getSelectedRow.getCustomers_ID()+"';");
				TableCustomers.setItems(Customers_Table_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Customers;"));
				statement.close();


				statement.executeUpdate(sqlQuery2);

				TableCustomerAttendance.setItems(Customers_Table_Attendance_Screen.getDataFromSqlAndAddToObservableList("SELECT Customers_Attendance.*, Customers.ID, Customers.First_Name, Customers.Last_Name FROM"
						+ " Customers_Attendance INNER JOIN Customers ON Customers_Attendance.ID=Customers.ID;"));
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
			tray.setTitle("No Customer Selected");
			tray.setMessage("To delete, please select a Customer from the table");
			tray.setNotificationType(notificationType);
			tray.showAndDismiss(Duration.millis(5000));
		}        
	}

	@FXML
	private void setCustomerSearchButtonClick(Event event){
		String sqlQuery = "select * FROM Customers where ID = '"+txtSearch.getText()+"';";
		TableCustomers.setItems(Customers_Table_Screen.getDataFromSqlAndAddToObservableList(sqlQuery));
	}

	@FXML
	private void setCustomerRefreshButtonClick(Event event){
		TableCustomers.setItems(Customers_Table_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Customers;"));//sql Query
		txtSearch.clear();
	}

	@FXML
	public void setOnSearchKeyPressed(KeyEvent event) throws IOException{
		if(txtSearch.getText()!=""){
			String sqlQuery = "select * FROM Customers where First_Name like '%"+txtSearch.getText()+"%' OR "
					+ "Last_Name like '%"+txtSearch.getText() + "%';";
			if(Customers_Table_Screen.getDataFromSqlAndAddToObservableList(sqlQuery)==null){
				TableCustomers.setPlaceholder(new Label("No Customer With Given Name"));
			}
			TableCustomers.setItems(Customers_Table_Screen.getDataFromSqlAndAddToObservableList(sqlQuery));
		}
		else{
			TableCustomers.setItems(Customers_Table_Screen.getDataFromSqlAndAddToObservableList("select * FROM Customers"));
		}
	} 

	@FXML
	private void setCustomerAttendanceSaveButtonClick(Event event){
		try{	       
			if(validateAttendanceDate()){
				connection = SqliteConnection.Connector();
				statement = connection.createStatement();

				Menu_CustomerModel getSelectedRow = TableCustomers.getSelectionModel().getSelectedItem();
				getSelectedRow.getCustomers_ID();

				int rowsAffected = statement.executeUpdate("insert into`Customers_Attendance` "+
						"(ID, `Date`,`AMPM`,`Day_of_Week`"+
						""+
						") "+
						"values ("+getSelectedRow.getCustomers_ID()+ ",'"+dtAttendance.getValue().toString()+"','"+AMPM() +"','" +dtAttendance.getValue().getDayOfWeek().toString()
						+"'); ");



				//TableCustomerAttendance.setItems(Customers_Table_Attendance_Screen.getDataFromSqlAndAddToObservableList("SELECT * FROM Customers_Attendance where ID = " + getSelectedRow.getCustomers_ID() + ";"));
				TableCustomerAttendance.setItems(Customers_Table_Attendance_Screen.getDataFromSqlAndAddToObservableList("SELECT Customers_Attendance.*, Customers.ID, Customers.First_Name, Customers.Last_Name FROM"
						+ " Customers_Attendance INNER JOIN Customers ON Customers_Attendance.ID=Customers.ID WHERE Customers_Attendance.ID = " + getSelectedRow.getCustomers_ID() + ";"));

				CustomersAttendanceSetAllClear();
				btShowAllAtt.setDisable(false);
				statement.close();
				connection.close();
			}

		}
		catch (SQLException e){
			e.printStackTrace();
		}

	}


	private void setAddDeleteAttendance(){
		btShowAllAtt.setDisable(true);
		CustomersAttendanceSetAllEnable();
		CustomersSetAllClear();
		CustomersSetAllDisable();
		//TableCustomerAttendance.setDisable(false);
		isCustomersEditButtonClick = false;
		isCustomersAddNewButtonClick = false;

	}

	public String AMPM(){
		if(rdAM.isSelected())
		{
			return "AM";
		}
		else{
			return "PM";
		}
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
	private void launchEmployeeMainMenu(Event event) throws IOException{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent Main_Menu = FXMLLoader.load(getClass().getResource("Main_Menu_Employee.fxml"));
		Scene MainMenu = new Scene(Main_Menu);
		Stage mainMenu = (Stage) ((Node) event.getSource()).getScene().getWindow();
		mainMenu.hide();
		mainMenu.setScene(MainMenu);
		mainMenu.setTitle("Main Menu");
		mainMenu.show();
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
	private void setCustomerAttendanceDeleteButtonClick(Event event){
		if(TableCustomerAttendance.getSelectionModel().getSelectedItem()!=null){
			Menu_Customer_AttendanceModel getSelectedRow = TableCustomerAttendance.getSelectionModel().getSelectedItem();
			String sqlQuery = "delete from Customers_Attendance where ID = "+getSelectedRow.getCustomers_ID()+ " AND Date = '"+getSelectedRow.getCustomers_Date()+ "' AND AMPM = '"+getSelectedRow.getCustomers_AMPM()+ "';";


			try {
				connection = SqliteConnection.Connector();
				statement = connection.createStatement();

				statement.executeUpdate(sqlQuery);

				TableCustomerAttendance.setItems(Customers_Table_Attendance_Screen.getDataFromSqlAndAddToObservableList("SELECT Customers_Attendance.*, Customers.ID, Customers.First_Name, Customers.Last_Name FROM"
						+ " Customers_Attendance INNER JOIN Customers ON Customers_Attendance.ID=Customers.ID;"));
				statement.close();
				connection.close();

				CustomersAttendanceSetAllDisable();
				btShowAllAtt.setDisable(false);

			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else{
			NotificationType notificationType = NotificationType.ERROR;
			TrayNotification tray = new TrayNotification();
			tray.setTitle("No Attendance Selected");
			tray.setMessage("To delete, please select an attendance");
			tray.setNotificationType(notificationType);
			tray.showAndDismiss(Duration.millis(5000));
		}     	 	
	}

	@FXML
	private void setShowAllAttClick(Event event){
		TableCustomerAttendance.setItems(Customers_Table_Attendance_Screen.getDataFromSqlAndAddToObservableList("SELECT Customers_Attendance.*, Customers.ID, Customers.First_Name, Customers.Last_Name FROM"
				+ " Customers_Attendance INNER JOIN Customers ON Customers_Attendance.ID=Customers.ID;"));
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
			txtFirst_Name.requestFocus();
			
			//txtFirst_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtLast_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtAddress.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtEmail.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtPhone.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			dtDOB.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			
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
			txtLast_Name.requestFocus();
			
			txtFirst_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			//txtLast_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtAddress.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtEmail.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtPhone.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			dtDOB.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
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
			txtEmail.requestFocus();
			
			txtFirst_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtLast_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtAddress.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			//txtEmail.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtPhone.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			dtDOB.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
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
			txtPhone.requestFocus();
			
			txtFirst_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtLast_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtAddress.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtEmail.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			//txtPhone.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			dtDOB.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
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
			
			txtFirst_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtLast_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtAddress.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtEmail.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtPhone.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			//dtDOB.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
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
			txtAddress.requestFocus();
			
			txtFirst_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtLast_Name.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			//txtAddress.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtEmail.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			txtPhone.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			dtDOB.setStyle("-fx-border-color: ccc; -fx-border-width: 1px ;");
			return false;
		} 
	}


	private boolean validateAttendanceDate(){
		if(dtAttendance.getValue() != null){
			return true;
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Date");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter an Attendance Date");
			alert.showAndWait();

			return false;
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
		else if(txtPhone.getText().length()>12 ){
			txtPhone.setText(txtPhone.getText().substring(0,12));
			txtPhone.positionCaret(12);
		}
	}

	@FXML
	public void setOnCustomerSelected(MouseEvent event) throws IOException{
		if(TableCustomers.getSelectionModel().getSelectedItem()!=null) {
			Menu_CustomerModel getSelectedRow = TableCustomers.getSelectionModel().getSelectedItem();setAddDeleteAttendance();
			TableCustomerAttendance.setItems(Customers_Table_Attendance_Screen.getDataFromSqlAndAddToObservableList("SELECT Customers_Attendance.*, Customers.ID, Customers.First_Name, Customers.Last_Name FROM"
					+ " Customers_Attendance INNER JOIN Customers ON Customers_Attendance.ID=Customers.ID WHERE Customers_Attendance.ID = " + getSelectedRow.getCustomers_ID() + ";"));
		}
		else{
			NotificationType notificationType = NotificationType.ERROR;
			TrayNotification tray = new TrayNotification();
			tray.setTitle("No Customer Selected");
			tray.setMessage("Please select a customer");
			tray.setNotificationType(notificationType);
			tray.showAndDismiss(Duration.millis(5000));
		}
	}
}
