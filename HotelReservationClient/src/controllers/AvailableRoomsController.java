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

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Room;

public class AvailableRoomsController {
	@FXML
	private Label noofrooms;
	@FXML
	private TableColumn<Room,String> roomidColumn;
	@FXML
	private TableColumn<Room,String> roomtypeColumn;
	@FXML
	private TableView<Room> tableview;
	@FXML
	private Button backButton;
	@FXML
	public void initialize() {
		String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
		String username = "root";
		String password = "Yukti@6713";
		String query="Select * from rooms where Status='Available';";
		try(Connection conn=DriverManager.getConnection(url,username,password)){
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet rs=preparedStatement.executeQuery();
			// Create observable lists
	        ObservableList<Room> rooms = FXCollections.observableArrayList();
	        while(rs.next()) {
	        	String roomid=(rs.getString("RoomID"));
	        	String roomtype=rs.getString("RoomType");
	        	double rate=rs.getDouble("Rate");
	        	Room room=new Room(roomid,roomtype,rate);
	        	rooms.add(room);
	        }
	        roomidColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoom_id()));
	        roomtypeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoom_type()));
	        tableview.setItems(rooms);
	     // Update label to display number of rooms
	        noofrooms.setText(String.valueOf(rooms.size()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void backButtonPressed() {
		Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
	}
}
