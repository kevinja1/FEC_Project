package application;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Customer_Attendance_Line_ChartController implements Initializable {
	
	@FXML
	private DatePicker dtWeek;
	
	@FXML
	private Label lblWeek;
	
	@FXML
	private Pane Screen;
	
	private String sunday, monday, tuesday, wednesday, thursday, friday, saturday;
	Customer_Attendance_Line_ChartModel linechart = new Customer_Attendance_Line_ChartModel();
	XYChart.Series<String, Integer> seriesAM;
	XYChart.Series<String, Integer> seriesPM;
	
	@FXML
	private LineChart<String,Integer> lineChartAttendance;
	
	@Override
	public void initialize(URL url, ResourceBundle rb){
		seriesAM = new XYChart.Series<>();
		
		seriesAM.setName("AM");
		
		seriesPM = new XYChart.Series<>();
		seriesPM.setName("PM");
		
		
		dtWeek.setEditable(false);
		lblWeek.setText("Week of " + LocalDate.now().toString());
		
		String now = LocalDate.now().getDayOfWeek().toString();
		
		//dtWeek.setPromptText(LocalDate.now().toString());

        
		if(now == "SUNDAY"){
			
			sunday = (LocalDate.now().toString());
			monday = (LocalDate.now().plusDays(1).toString());
			tuesday = (LocalDate.now().plusDays(2).toString());
			wednesday = (LocalDate.now().plusDays(3).toString());
			thursday = (LocalDate.now().plusDays(4).toString());
			friday = (LocalDate.now().plusDays(5).toString());
			saturday = (LocalDate.now().plusDays(6).toString());

		}
		else if(now == "MONDAY"){
			
			monday = (LocalDate.now().toString());
			tuesday = (LocalDate.now().plusDays(1).toString());
			wednesday = (LocalDate.now().plusDays(2).toString());
			thursday = (LocalDate.now().plusDays(3).toString());
			friday = (LocalDate.now().plusDays(4).toString());
			saturday = (LocalDate.now().plusDays(5).toString());
			sunday = (LocalDate.now().minusDays(1).toString());

		}
		else if(now == "TUESDAY"){
			
			tuesday = (LocalDate.now().toString());
			wednesday = (LocalDate.now().plusDays(1).toString());
			thursday = (LocalDate.now().plusDays(2).toString());
			friday = (LocalDate.now().plusDays(3).toString());
			saturday = (LocalDate.now().plusDays(4).toString());
			sunday = (LocalDate.now().minusDays(2).toString());
			monday = (LocalDate.now().minusDays(1).toString());

		}
		else if(now == "WEDNESDAY"){
			
			wednesday = (LocalDate.now().toString());
			thursday = (LocalDate.now().plusDays(1).toString());
			friday = (LocalDate.now().plusDays(2).toString());
			saturday = (LocalDate.now().plusDays(3).toString());
			sunday = (LocalDate.now().minusDays(3).toString());
			monday = (LocalDate.now().minusDays(2).toString());
			tuesday = (LocalDate.now().minusDays(1).toString());

		}
		else if(now == "THURSDAY"){
			
			thursday = (LocalDate.now().toString());
			friday = (LocalDate.now().plusDays(1).toString());
			saturday = (LocalDate.now().plusDays(2).toString());
			sunday = (LocalDate.now().minusDays(4).toString());
			monday = (LocalDate.now().minusDays(3).toString());
			tuesday = (LocalDate.now().minusDays(2).toString());
			wednesday = (LocalDate.now().minusDays(1).toString());

		}
		else if(now == "FRIDAY"){
			
			friday = (LocalDate.now().toString());
			saturday = (LocalDate.now().plusDays(1).toString());
			sunday = (LocalDate.now().minusDays(5).toString());
			monday = (LocalDate.now().minusDays(4).toString());
			tuesday = (LocalDate.now().minusDays(3).toString());
			wednesday = (LocalDate.now().minusDays(2).toString());
			thursday = (LocalDate.now().minusDays(1).toString());

		}
		else if(now == "SATURDAY"){
			
			saturday = (LocalDate.now().toString());
			sunday = (LocalDate.now().minusDays(6).toString());
			monday = (LocalDate.now().minusDays(5).toString());
			tuesday = (LocalDate.now().minusDays(4).toString());
			wednesday = (LocalDate.now().minusDays(3).toString());
			thursday = (LocalDate.now().minusDays(2).toString());
			friday = (LocalDate.now().minusDays(1).toString());
		}
		   
		seriesAM.getData().add(new XYChart.Data<>("Sunday", linechart.calcNumAMSunday(sunday)));
		seriesAM.getData().add(new XYChart.Data<>("Monday", linechart.calcNumAMMonday(monday)));
		seriesAM.getData().add(new XYChart.Data<>("Tuesday", linechart.calcNumAMTuesday(tuesday)));
		seriesAM.getData().add(new XYChart.Data<>("Wednesday", linechart.calcNumAMWednesday(wednesday)));
		seriesAM.getData().add(new XYChart.Data<>("Thursday", linechart.calcNumAMThursday(thursday)));
		seriesAM.getData().add(new XYChart.Data<>("Friday", linechart.calcNumAMFriday(friday)));
		seriesAM.getData().add(new XYChart.Data<>("Saturday", linechart.calcNumAMSaturday(saturday)));
		
		seriesPM.getData().add(new XYChart.Data<>("Sunday", linechart.calcNumPMSunday(sunday)));
		seriesPM.getData().add(new XYChart.Data<>("Monday", linechart.calcNumPMMonday(monday)));
		seriesPM.getData().add(new XYChart.Data<>("Tuesday", linechart.calcNumPMTuesday(tuesday)));
		seriesPM.getData().add(new XYChart.Data<>("Wednesday", linechart.calcNumPMWednesday(wednesday)));
		seriesPM.getData().add(new XYChart.Data<>("Thursday", linechart.calcNumPMThursday(thursday)));
		seriesPM.getData().add(new XYChart.Data<>("Friday", linechart.calcNumPMFriday(friday)));
		seriesPM.getData().add(new XYChart.Data<>("Saturday", linechart.calcNumPMSaturday(saturday)));
		
		
		
		lineChartAttendance.getData().addAll(seriesAM);   
		lineChartAttendance.getData().addAll(seriesPM);   
		
	}
	
	@FXML
	public void setDates()
	{
		String day_of_week = dtWeek.getValue().getDayOfWeek().toString();
		if(day_of_week == "SUNDAY"){
			
			sunday = (dtWeek.getValue().toString());
			monday = (dtWeek.getValue().plusDays(1).toString());
			tuesday = (dtWeek.getValue().plusDays(2).toString());
			wednesday = (dtWeek.getValue().plusDays(3).toString());
			thursday = (dtWeek.getValue().plusDays(4).toString());
			friday = (dtWeek.getValue().plusDays(5).toString());
			saturday = (dtWeek.getValue().plusDays(6).toString());

		}
		else if(day_of_week == "MONDAY"){
			
			monday = (dtWeek.getValue().toString());
			tuesday = (dtWeek.getValue().plusDays(1).toString());
			wednesday = (dtWeek.getValue().plusDays(2).toString());
			thursday = (dtWeek.getValue().plusDays(3).toString());
			friday = (dtWeek.getValue().plusDays(4).toString());
			saturday = (dtWeek.getValue().plusDays(5).toString());
			sunday = (dtWeek.getValue().minusDays(1).toString());

		}
		else if(day_of_week == "TUESDAY"){
			
			tuesday = (dtWeek.getValue().toString());
			wednesday = (dtWeek.getValue().plusDays(1).toString());
			thursday = (dtWeek.getValue().plusDays(2).toString());
			friday = (dtWeek.getValue().plusDays(3).toString());
			saturday = (dtWeek.getValue().plusDays(4).toString());
			sunday = (dtWeek.getValue().minusDays(2).toString());
			monday = (dtWeek.getValue().minusDays(1).toString());

		}
		else if(day_of_week == "WEDNESDAY"){
			
			wednesday = (dtWeek.getValue().toString());
			thursday = (dtWeek.getValue().plusDays(1).toString());
			friday = (dtWeek.getValue().plusDays(2).toString());
			saturday = (dtWeek.getValue().plusDays(3).toString());
			sunday = (dtWeek.getValue().minusDays(3).toString());
			monday = (dtWeek.getValue().minusDays(2).toString());
			tuesday = (dtWeek.getValue().minusDays(1).toString());

		}
		else if(day_of_week == "THURSDAY"){
			
			thursday = (dtWeek.getValue().toString());
			friday = (dtWeek.getValue().plusDays(1).toString());
			saturday = (dtWeek.getValue().plusDays(2).toString());
			sunday = (dtWeek.getValue().minusDays(4).toString());
			monday = (dtWeek.getValue().minusDays(3).toString());
			tuesday = (dtWeek.getValue().minusDays(2).toString());
			wednesday = (dtWeek.getValue().minusDays(1).toString());

		}
		else if(day_of_week == "FRIDAY"){
			
			friday = (dtWeek.getValue().toString());
			saturday = (dtWeek.getValue().plusDays(1).toString());
			sunday = (dtWeek.getValue().minusDays(5).toString());
			monday = (dtWeek.getValue().minusDays(4).toString());
			tuesday = (dtWeek.getValue().minusDays(3).toString());
			wednesday = (dtWeek.getValue().minusDays(2).toString());
			thursday = (dtWeek.getValue().minusDays(1).toString());

		}
		else if(day_of_week == "SATURDAY"){
			
			saturday = (dtWeek.getValue().toString());
			sunday = (dtWeek.getValue().minusDays(6).toString());
			monday = (dtWeek.getValue().minusDays(5).toString());
			tuesday = (dtWeek.getValue().minusDays(4).toString());
			wednesday = (dtWeek.getValue().minusDays(3).toString());
			thursday = (dtWeek.getValue().minusDays(2).toString());
			friday = (dtWeek.getValue().minusDays(1).toString());
		}
	}
	
		@FXML
		public void displayLines(Event event){
			
			setDates();
			seriesAM.getData().clear();
			seriesAM.getData().clear();
			
			seriesAM.getData().add(new XYChart.Data<>("Sunday", linechart.calcNumAMSunday(sunday)));
			seriesAM.getData().add(new XYChart.Data<>("Monday", linechart.calcNumAMMonday(monday)));
			seriesAM.getData().add(new XYChart.Data<>("Tuesday", linechart.calcNumAMTuesday(tuesday)));
			seriesAM.getData().add(new XYChart.Data<>("Wednesday", linechart.calcNumAMWednesday(wednesday)));
			seriesAM.getData().add(new XYChart.Data<>("Thursday", linechart.calcNumAMThursday(thursday)));
			seriesAM.getData().add(new XYChart.Data<>("Friday", linechart.calcNumAMFriday(friday)));
			seriesAM.getData().add(new XYChart.Data<>("Saturday", linechart.calcNumAMSaturday(saturday)));
			
			seriesPM.getData().add(new XYChart.Data<>("Sunday", linechart.calcNumPMSunday(sunday)));
			seriesPM.getData().add(new XYChart.Data<>("Monday", linechart.calcNumPMMonday(monday)));
			seriesPM.getData().add(new XYChart.Data<>("Tuesday", linechart.calcNumPMTuesday(tuesday)));
			seriesPM.getData().add(new XYChart.Data<>("Wednesday", linechart.calcNumPMWednesday(wednesday)));
			seriesPM.getData().add(new XYChart.Data<>("Thursday", linechart.calcNumPMThursday(thursday)));
			seriesPM.getData().add(new XYChart.Data<>("Friday", linechart.calcNumPMFriday(friday)));
			seriesPM.getData().add(new XYChart.Data<>("Saturday", linechart.calcNumPMSaturday(saturday)));
			
			dtWeek.setEditable(false);
			lblWeek.setText("Week of " + dtWeek.getValue().toString());
		}
		
		@FXML  
	    private void printChart(Event event) throws InvocationTargetException{
		   Printer printer = Printer.getDefaultPrinter();
		   PrinterJob job = PrinterJob.createPrinterJob();
		   
		   if(printer != null){
			   PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
			   double scaleX
		       = pageLayout.getPrintableWidth() / Screen.getBoundsInParent().getWidth();
			   double scaleY
		       = pageLayout.getPrintableHeight() / Screen.getBoundsInParent().getHeight();
			   Scale scale = new Scale(scaleX, scaleY);
		  	   Screen.getTransforms().add(scale);
		  	   
		  	 if(job.printPage(pageLayout, Screen)){
		        	job.endJob();
		        	Screen.getTransforms().remove(scale);        	
		    	}
		    	else{
		    		NotificationType notificationType = NotificationType.ERROR;
		            TrayNotification tray = new TrayNotification();
		            tray.setTitle("Printing error");
		            tray.setMessage("Try turning on your printers");
		            tray.setNotificationType(notificationType);
		            tray.showAndDismiss(Duration.millis(10000));
		    	}
		   }
		   else if(printer == null){
	    		//tray notification printer not found
	    		
			    NotificationType notificationType = NotificationType.ERROR;
	            TrayNotification tray = new TrayNotification();
	            tray.setTitle("Printer not found");
	            tray.setMessage("Please set default printer/turn printer on");
	            tray.setNotificationType(notificationType);
	            tray.showAndDismiss(Duration.millis(10000));
	    		
	    	}
	    	
	    	
	    }
}
