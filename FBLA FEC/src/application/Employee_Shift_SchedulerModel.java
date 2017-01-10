package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employee_Shift_SchedulerModel {
	private final SimpleIntegerProperty EmployeesID;
	private final SimpleStringProperty EmployeesFirst_Name;
	private final SimpleStringProperty EmployeesLast_Name;
	
	Connection connection;
	
    private Statement statement;
    private ResultSet resultSet;
    
    public Employee_Shift_SchedulerModel(int EmployeesID, String EmployeesFirst_Name, String EmployeesLast_Name){

		this.EmployeesID = new SimpleIntegerProperty(EmployeesID);
		this.EmployeesFirst_Name = new SimpleStringProperty(EmployeesFirst_Name);
		this.EmployeesLast_Name = new SimpleStringProperty(EmployeesLast_Name);
	}
	
	public Employee_Shift_SchedulerModel(){

		this.EmployeesID = new SimpleIntegerProperty(0);
		this.EmployeesFirst_Name = new SimpleStringProperty("");
		this.EmployeesLast_Name = new SimpleStringProperty("");
	}
	
	public ObservableList getDataFromSqlAndAddToObservableList(String query){
        ObservableList<Employee_Shift_SchedulerModel> employeeTableData = FXCollections.observableArrayList();
        try {
        	connection = SqliteConnection.Connector();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query); 
           
            while(resultSet.next()){
                employeeTableData.add(new Employee_Shift_SchedulerModel(
                        resultSet.getInt("ID"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name")
                        ));
            }
            
            statement.close();
            resultSet.close();
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeTableData;

    }
	
	 public Integer getEmployeesID() {
	        return EmployeesID.get();
	    }

	    public SimpleIntegerProperty EmployeesID() {
	        return EmployeesID();
	    }

	    public void setEmployeesID(Integer EmployeesID) {
	        this.EmployeesID.set(EmployeesID);
	    }
	    
	    public String getEmployeesFirst_Name() {
	        return EmployeesFirst_Name.get();
	    }

	    public SimpleStringProperty EmployeesFirst_Name() {
	        return EmployeesFirst_Name();
	    }

	    public void setEmployeesFirst_Name(String EmployeesFirst_Name) {
	        this.EmployeesFirst_Name.set(EmployeesFirst_Name);
	    }
	    
	    public String getEmployeesLast_Name() {
	        return EmployeesLast_Name.get();
	    }

	    public SimpleStringProperty EmployeesLast_Name() {
	        return EmployeesLast_Name();
	    }

	    public void setEmployeesLast_Name(String EmployeesLast_Name) {
	        this.EmployeesLast_Name.set(EmployeesLast_Name);
	    }
	    
}