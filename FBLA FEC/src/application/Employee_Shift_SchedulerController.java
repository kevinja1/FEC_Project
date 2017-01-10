package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ListView;

import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Employee_Shift_SchedulerController implements Initializable {
	
	public Employee_Shift_SchedulerModel Scheduler_Table = new Employee_Shift_SchedulerModel();
	@FXML
	private ListView listSun;
	@FXML
	private ListView listMon;
	@FXML
	private ListView listTues;
	@FXML
	private ListView listWed;
	@FXML
	private ListView listThurs;
	@FXML
	private ListView listFri;
	@FXML
	private ListView listSat;
	@FXML
	private Label lblSun;
	@FXML
	private Label lblMon;
	@FXML
	private Label lblTues;
	@FXML
	private Label lblWed;
	@FXML
	private Label lblThurs;
	@FXML
	private Label lblFri;
	@FXML
	private Label lblSat;
	@FXML
	private TableView<Main_Menu_EmployeeModel> TableEmployees;
	@FXML
	private TableColumn<Employee_Shift_SchedulerModel, String> EmployeesFirst_Name;
	@FXML
	private TableColumn<Employee_Shift_SchedulerModel, String> EmployeesLast_Name;
	@FXML
	private TableColumn<Employee_Shift_SchedulerModel, String> EmployeesID;
	@FXML
	private TextField txtSearch;
	@FXML
	private RadioButton rdAM_Shift;
	@FXML
	private RadioButton rdPM_Shift;

	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
        //Get data from adminTableData ObservableList and set this data on JavaFX table column

        EmployeesFirst_Name.setCellValueFactory(new PropertyValueFactory<Employee_Shift_SchedulerModel,String>("EmployeesFirst_Name")); 
        EmployeesLast_Name.setCellValueFactory(new PropertyValueFactory<Employee_Shift_SchedulerModel,String>("EmployeesLast_Name"));
        EmployeesID.setCellValueFactory(new PropertyValueFactory<Employee_Shift_SchedulerModel,String>("EmployeesID"));
        
        
        TableEmployees.setItems(Scheduler_Table.getDataFromSqlAndAddToObservableList("SELECT * FROM EMPLOYEES"));
    }
}