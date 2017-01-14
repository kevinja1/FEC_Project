package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer_Attendance_Line_ChartModel {
	
	Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
	public int calcNumAMSunday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numAM_Sunday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("AM") && resultSet.getString("Day_of_Week").equals("SUNDAY")){			 			
			 			numAM_Sunday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numAM_Sunday;	
	}
	
	public int calcNumAMMonday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numAM_Monday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("AM") && resultSet.getString("Day_of_Week").equals("MONDAY")){			 			
			 			numAM_Monday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numAM_Monday;	
	}
	
	public int calcNumAMTuesday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numAM_Tuesday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("AM") && resultSet.getString("Day_of_Week").equals("TUESDAY")){			 			
			 			numAM_Tuesday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numAM_Tuesday;	
	}
	
	public int calcNumAMWednesday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numAM_Wednesday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("AM") && resultSet.getString("Day_of_Week").equals("WEDNESDAY")){			 			
			 			numAM_Wednesday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numAM_Wednesday;	
	}
	
	public int calcNumAMThursday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numAM_Thursday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("AM") && resultSet.getString("Day_of_Week").equals("THURSDAY")){			 			
			 			numAM_Thursday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numAM_Thursday;	
	}
	
	public int calcNumAMFriday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numAM_Friday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("AM") && resultSet.getString("Day_of_Week").equals("FRIDAY")){			 			
			 			numAM_Friday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numAM_Friday;	
	}
	
	public int calcNumAMSaturday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numAM_Saturday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("AM") && resultSet.getString("Day_of_Week").equals("Saturday")){			 			
			 			numAM_Saturday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numAM_Saturday;	
	}
	
	public int calcNumPMSunday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numPM_Sunday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("PM") && resultSet.getString("Day_of_Week").equals("SUNDAY")){			 			
			 			numPM_Sunday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numPM_Sunday;	
	}
	
	public int calcNumPMMonday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numPM_Monday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("PM") && resultSet.getString("Day_of_Week").equals("MONDAY")){			 			
			 			numPM_Monday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numPM_Monday;	
	}
	
	public int calcNumPMTuesday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numPM_Tuesday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("PM") && resultSet.getString("Day_of_Week").equals("TUESDAY")){			 			
			 			numPM_Tuesday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numPM_Tuesday;	
	}
	
	public int calcNumPMWednesday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numPM_Wednesday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("PM") && resultSet.getString("Day_of_Week").equals("WEDNESDAY")){			 			
			 			numPM_Wednesday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numPM_Wednesday;	
	}
	
	public int calcNumPMThursday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numPM_Thursday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("PM") && resultSet.getString("Day_of_Week").equals("THURSDAY")){			 			
			 			numPM_Thursday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numPM_Thursday;	
	}
	
	public int calcNumPMFriday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numPM_Friday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("PM") && resultSet.getString("Day_of_Week").equals("FRIDAY")){			 			
			 			numPM_Friday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numPM_Friday;	
	}
	
	public int calcNumPMSaturday(){
		
		String sqlQuery = "select * FROM Customers_Attendance";
		connection = SqliteConnection.Connector();
		int numPM_Saturday = 0; 	
	    try {
			
	    	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("PM") && resultSet.getString("Day_of_Week").equals("Saturday")){			 			
			 			numPM_Saturday++;
			 		}
			 	}
			 	
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	    return numPM_Saturday;	
	}
	

	



  
}
