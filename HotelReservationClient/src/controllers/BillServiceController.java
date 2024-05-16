/**********************************************
Final Project #
Course:APD545 - Semester-5
Last Name:Mulani
First Name:Yukti 
ID:156809212
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature Yukti Manoj Mulani
Date:13th April 2024
**********************************************/
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BillServiceController {
	@FXML
	private TextField bookingidfield;
	@FXML
	private TextField guestnamefield;
	@FXML
	private TextField roomscountfield;
	@FXML
	private TextField roomtypefield;
	@FXML
	private TextField ratefield;
	@FXML
	private TextField discountfield;
	@FXML
	private TextField totalfield;
	@FXML
	private Label rateLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label roomLabel;
	@FXML
	private Label totalAmountLabel;
	@FXML
	private Label roomtypeLabel;
	@FXML
	private Label discountLabel;
	@FXML
	private Button donebutton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label utilityLabel;
	
	
	private String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
	private String username = "root";
	private String password = "Yukti@6713";
	private int daysCount=0;
	@FXML
	public void initialize() {
		//labels
		rateLabel.setVisible(false);
		nameLabel.setVisible(false);
		roomLabel.setVisible(false);
		roomtypeLabel.setVisible(false);
		discountLabel.setVisible(false);
		totalAmountLabel.setVisible(false);
		//textfields
		guestnamefield.setVisible(false);
		roomscountfield.setVisible(false);
		roomtypefield.setVisible(false);
		ratefield.setVisible(false);
		discountfield.setVisible(false);
		totalfield.setVisible(false);
		
		bookingidfield.textProperty().addListener((oldValue,observable,newValue)->{
			if(checkBookingID(bookingidfield.getText())) {
				//labels
				rateLabel.setVisible(true);
				nameLabel.setVisible(true);
				roomLabel.setVisible(true);
				roomtypeLabel.setVisible(true);
				discountLabel.setVisible(true);
				totalAmountLabel.setVisible(true);
				//textfields
				guestnamefield.setVisible(true);
				roomscountfield.setVisible(true);
				roomtypefield.setVisible(true);
				ratefield.setVisible(true);
				discountfield.setVisible(true);
				totalfield.setVisible(true);
			}
			else {
				utilityLabel.setText("Please Enter the correct BookingID");
			}
		});
		
		discountfield.textProperty().addListener((obs,oldValue,newValue)->{
			validateDiscount();
		});
	}
	
	private boolean checkBookingID(String bookingid) {
		try (Connection connection = DriverManager.getConnection(url,username,password)) {
            String sql = "SELECT * FROM Reservations WHERE BookingID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookingid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("Title");
                String firstName = resultSet.getString("Firstname");
                String lastName = resultSet.getString("Lastname");
                String roomType = resultSet.getString("RoomType");
                double rate = resultSet.getDouble("Rate");
                int noofrooms=resultSet.getInt("NumberOfRooms");
                guestnamefield.setText(title+" "+firstName+" "+lastName);
                roomtypefield.setText(roomType);
                roomscountfield.setText(String.valueOf(noofrooms));
                ratefield.setText(String.valueOf(rate));
                daysCount=resultSet.getInt("DaysCount");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error, display error message in UI
        }
		return false;
	}
	
	@FXML
	public void donebuttonPressed() {
		
		Stage stage = (Stage) donebutton.getScene().getWindow();
        stage.close();
	}
	@FXML
	public void cancelButtonPressed() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
	}
	@FXML
	public void updateTotal() {
		String numericPart = discountfield.getText().replaceAll("[^0-9.]", "");
		double disc=Double.parseDouble(numericPart);
		System.out.println(disc);
		double rate=Double.parseDouble(ratefield.getText());
		System.out.println(rate);
		double discRate=rate-(rate*(disc/100));
		System.out.println(discRate);
		ratefield.setText(String.valueOf(discRate));
		System.out.println(daysCount);
		totalfield.setText(String.valueOf(daysCount*discRate));
	}
	private void validateDiscount() {
		String numericPart = discountfield.getText().replaceAll("[^0-9.]", "");
		double discount=Double.parseDouble(numericPart);
		if(discount>25) {
			Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Discount");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid discount which is less than or equal to 25%.");
            alert.showAndWait();
		}
	}
	
}
