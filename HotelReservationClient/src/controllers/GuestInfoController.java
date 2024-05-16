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

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.GuestInfo;
import models.Reservation;
import models.ReservationManager;
import models.Room;

public class GuestInfoController {
	private Room room;
	private Reservation res;
	
	public Reservation getRes() {
		return res;
	}

	public void setRes(Reservation res) {
		this.res = res;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	@FXML
	private TextField titlefield;
	@FXML
	private TextField firstnamefield;
	@FXML
	private TextField lastnamefield;
	@FXML
	private TextField addressfield;
	@FXML
	private TextField phonefield;
	@FXML
	private TextField emailfield;
	@FXML
	private Button savebutton;
	@FXML
	private Button cancelButton;
	@FXML
	public void initialize() {
		phonefield.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	            // If the new value does not match the pattern of digits, show an alert
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Invalid Input");
	            alert.setHeaderText(null);
	            alert.setContentText("Please enter a valid integer for phone number.");
	            alert.showAndWait();

	            // Replace the invalid value with an empty string
	            phonefield.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
	}
	@FXML
	public void savebuttonPressed() {
		Random rand=new Random();
		String email = emailfield.getText().trim();
	    int guestid=rand.nextInt(101);
	    String title=titlefield.getText();
	    String firstname=firstnamefield.getText();
	    String lastname=lastnamefield.getText();
	    String addr=addressfield.getText();
	    BigInteger phone=new BigInteger(phonefield.getText());
        if (isValidEmail(email)) {
        	GuestInfo guest=new GuestInfo(guestid,title,firstname,lastname,addr,phone,email);
            ReservationManager rm=new ReservationManager();
            rm.addReservation(room, res, guest);
            String roomids=room.getRoom_id();
            updateRoomStatus(roomids);
            Stage stage = (Stage) savebutton.getScene().getWindow();
            stage.close();
        } else {
            // Email is not valid, show an alert to the user
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Email");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
        }
        
        
	}
	
	@FXML
	public void cancelButtonPressed() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
		
	}
	
	// Method to check if the email is valid using regular expressions
    private boolean isValidEmail(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void updateRoomStatus(String roomids) {
    	// Split the string by space to get individual room IDs
        List<String> roomIds = Arrays.asList(roomids.split("\\s+"));
        String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
		String username = "root";
		String password = "Yukti@6713";
		String sql = "UPDATE Rooms SET Status = 'Occupied' WHERE RoomID IN (";
        for (int i = 0; i < roomIds.size(); i++) {
            if (i > 0) {
                sql += ", ";
            }
            sql += "?";
        }
        sql += ");";
        try (Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	// Set room IDs as parameters
            for (int i = 0; i < roomIds.size(); i++) {
                preparedStatement.setString(i + 1, roomIds.get(i));
            }

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " rows updated.");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
