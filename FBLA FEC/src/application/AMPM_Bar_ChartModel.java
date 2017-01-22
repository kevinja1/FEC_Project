package application;

//importing all packages needed for this class
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Class for actual calculations with data obtained from the database
public class AMPM_Bar_ChartModel{
	
	//Total number of AM Customer Attendances and PM Customer Attendances, respectively
	private int numAM;
	private int numPM;
	
	Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
	//Calculates the Total Number of AM Customer Attendances
    public void calcNumAMPM(){
		
		//This query will executed to add all instances of Customer Attendances from the table, Customers_Attendance
    	String sqlQuery = "select * FROM Customers_Attendance";
		
    	//Establishes the connection with the Infinity_DB.db database
    	connection = SqliteConnection.Connector();
	    
    	try{
			 	statement = connection.createStatement();
			 	resultSet = statement.executeQuery(sqlQuery);
			 	
			 	//Goes through each Customer Attendance and if it is AM, increments the numAM variable, else increments the numPM variable
			 	while(resultSet.next()){
			 		if(resultSet.getString("AMPM").equals("AM")){			 			
			 			numAM++;
			 		}
			 		else{
			 			numPM++;
			 		}
			 	}
			 	
			 	//Properly closes the statement, resultSet, and connection
			 	statement.close();
			 	resultSet.close();
			 	connection.close();
			 	
		} 
    	catch (SQLException e){
			
			//Catches a SQL Exception and prints it to command line
			e.printStackTrace();		
		}
	}
	
    
    //getters
    public int getnumAM(){
    	return numAM;
    }
    
    public int getnumPM(){
    	return numPM;
    }
    
    //setters
    public void setnumAM(int numAM){
    	this.numAM = numAM;
    }
    
    public void setnumPM(int numPM){
    	this.numPM = numPM;
    }
}