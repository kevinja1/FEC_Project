package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class Menu_Customer_AttendanceModel {
	private final SimpleIntegerProperty Customers_ID;
	private final SimpleStringProperty Customers_Date;
	private final SimpleStringProperty Customers_AMPM;
	
	
	
    Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    public Menu_Customer_AttendanceModel(int CustomersID, String CustomersDate, String CustomersAMPM){

		
		this.Customers_Date = new SimpleStringProperty(CustomersDate);
		this.Customers_AMPM = new SimpleStringProperty(CustomersAMPM);		
		this.Customers_ID = new SimpleIntegerProperty(CustomersID);
	}
	
	public Menu_Customer_AttendanceModel(){

		this.Customers_Date = new SimpleStringProperty("");
		this.Customers_AMPM = new SimpleStringProperty("");		
		this.Customers_ID = new SimpleIntegerProperty(0);
	}
	

	 public ObservableList getDataFromSqlAndAddToObservableList(String query){
	        ObservableList<Menu_Customer_AttendanceModel> customerAttendanceTableData = FXCollections.observableArrayList();
	        try {
	        	connection = SqliteConnection.Connector();
	            statement = connection.createStatement();
	            resultSet = statement.executeQuery(query);
	            
	           
	            while(resultSet.next()){
	                customerAttendanceTableData.add(new Menu_Customer_AttendanceModel(
	                        resultSet.getInt("ID"),
	                        resultSet.getString("Date"),
	                        resultSet.getString("AMPM")
	                        ));
	            }
	            
	            statement.close();
	            resultSet.close();
	            connection.close();
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return customerAttendanceTableData;

	    }
	 
	 public String getCustomers_Date() {
	        return Customers_Date.get();
	    }

	    public SimpleStringProperty Customers_Date() {
	        return Customers_Date();
	    }

	    public void setCustomers_Date(String Customers_Date) {
	        this.Customers_Date.set(Customers_Date);
	    }
	    
	    public String getCustomers_AMPM() {
	        return Customers_AMPM.get();
	    }

	    public SimpleStringProperty Customers_AMPM() {
	        return Customers_AMPM();
	    }

	    public void setCustomers_AMPM(String Customers_AMPM) {
	        this.Customers_AMPM.set(Customers_AMPM);
	    }
   
}
