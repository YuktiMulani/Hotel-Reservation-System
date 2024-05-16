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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.ReservationManager;


public class BookingsController {
	@FXML
	private TableView<ReservationManager> tableview;
	@FXML
	private TableColumn<ReservationManager,Integer> bookingidColumn;
	@FXML
	private TableColumn<ReservationManager,String> nameColumn;
	@FXML
	private TableColumn<ReservationManager,String> roomTypeColumn;
	@FXML
	private TableColumn<ReservationManager,Integer> noofroomsColumn;
	@FXML 
	private TableColumn<ReservationManager,Integer> daysColumn;
	@FXML
	private Button backButton;
	@FXML
	private Label noofbookings;
	
	@FXML
	public void initialize() {
		String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
		String username = "root";
		String password = "Yukti@6713";
		String query="Select * from Reservations;";
		try(Connection conn=DriverManager.getConnection(url,username,password)){
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet rs=preparedStatement.executeQuery();
			// Create observable lists
	        ObservableList<ReservationManager> reservations = FXCollections.observableArrayList();
			while(rs.next()) {
				int bookingID = rs.getInt("BookingID");
	            String firstName = rs.getString("Firstname");
	            String lastName = rs.getString("Lastname");
	            String fullName = firstName + " " + lastName;
	            String roomType = rs.getString("RoomType");
	            int numberOfRoom = rs.getInt("NumberOfRooms");
	            int numberOfDay = rs.getInt("DaysCount");

	            ReservationManager rm=new ReservationManager(bookingID,fullName,roomType,numberOfRoom,numberOfDay);
	            reservations.add(rm);
			}
			 // Bind observable lists to table columns
			 bookingidColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getBookingid()).asObject());
            nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
            roomTypeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomtype()));
            noofroomsColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNoofrooms()).asObject());
            daysColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNoofdays()).asObject());
            
//            // Set table items
           tableview.setItems(reservations);
         // Update label to display number of bookings
            noofbookings.setText(String.valueOf(reservations.size()));

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
        
     
		
	}
	@FXML
	public void BackButtonPressed() {
		Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
	}
}
