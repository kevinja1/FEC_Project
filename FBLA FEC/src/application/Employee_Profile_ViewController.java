package application;

import javafx.fxml.FXML;

import javafx.scene.text.Text;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Employee_Profile_ViewController {
	@FXML
	private ImageView profilePic;
	@FXML
	private Text txtName;
	@FXML
	private Text txtPhone;
	@FXML
	private Text txtEmail;
	@FXML
	private Text txtDOB;
	@FXML
	private Text txtID;
	@FXML
	private Text txtAddress;
	@FXML
	private Label label;
	
	private Employee_Profile_ViewModel Info;

    public void setCurrentInfo(Employee_Profile_ViewModel currentInfo) {
        txtID.setText("Employee ID: " + currentInfo.getId());
        txtName.setText(currentInfo.getName());
        txtPhone.setText(currentInfo.getPhone());
        txtDOB.setText(currentInfo.getDOB());
        txtAddress.setText(currentInfo.getAddress());
        txtEmail.setText(currentInfo.getEmail());
      
       
        profilePic.setImage(new Image("/application/default-user-icon.png"));

        this.Info = currentInfo;

    }


}
