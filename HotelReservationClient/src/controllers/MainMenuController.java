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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

	@FXML
	private Button bookRoom;
	@FXML
	private Button billservice;
	@FXML
	private Button currentbookings;
	@FXML
	private Button availableRooms;
	@FXML
	private Button exitButton;
	
	public void bookroombuttonpressed() {
		try {
            // Load the FXML file for adding a part
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BookRoom.fxml"));
            Parent root = loader.load();

            // Set the Inventory instance in AddPartController
            RoomBookingController controller = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle error loading FXML file
        }
	}
	
	public void billservicebuttpnpressed() {
		try {
            // Load the FXML file for adding a part
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BillService.fxml"));
            Parent root = loader.load();

            // Set the Inventory instance in AddPartController
            BillServiceController controller = loader.getController();
 
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle error loading FXML file
        }
	}
	
	public void currentbookingsButtonPressed() {
		try {
            // Load the FXML file for adding a part
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CurrentBookings.fxml"));
            Parent root = loader.load();

            // Set the Inventory instance in AddPartController
            BookingsController controller = loader.getController();
 
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle error loading FXML file
        }
	}
	
	public void availableButtonPressed() {
		try {
            // Load the FXML file for adding a part
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AvailabLeRooms.fxml"));
            Parent root = loader.load();

            // Set the Inventory instance in AddPartController
            AvailableRoomsController controller = loader.getController();
 
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle error loading FXML file
        }
	}
	
	public void exitButtonPressed() {
		String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
		String username = "root";
		String password = "Yukti@6713";
		String sql = "UPDATE Rooms SET Status = 'Available';";
		String sql2="DELETE from Reservations;";
		try(Connection conn=DriverManager.getConnection(url,username,password);
				PreparedStatement preparedStatement = conn.prepareStatement(sql)){
					int rowsAffected = preparedStatement.executeUpdate();
					System.out.println(rowsAffected+"rows affected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection conn2=DriverManager.getConnection(url,username,password);
				PreparedStatement ps=conn2.prepareStatement(sql2)){
			int rows=ps.executeUpdate();
			System.out.println(rows+"rows affected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.exit();
	}
}
