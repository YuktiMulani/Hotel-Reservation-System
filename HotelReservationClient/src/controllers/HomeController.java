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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
	private Socket clientsocket;
	
	public void setClientsocket(Socket clientsocket) {
		this.clientsocket = clientsocket;
	}

	@FXML
	private Button phone;
	@FXML
	private Button kiosk;
	
	@FXML
	public void phonebuttonpressed(ActionEvent event) {
		final String SERVER_ADDRESS = "localhost"; // Server address
        final int SERVER_PORT = 5000;
        try (Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);){
        	// Connect to the server
        	writer.println(" I am initialising");
        	//receive
        	String line;
        	line = reader.readLine();
        	System.out.println(line);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
            // Load the FXML file for adding a part
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
            Parent root = loader.load();

            // Set the Inventory instance in AddPartController
            LoginController controller = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle error loading FXML file
        }
		
	}
	
	@FXML
	public void kioskbuttonpressed(ActionEvent event) {
		

		try {
            // Load the FXML file for adding a part
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainMenu.fxml"));
            Parent root = loader.load();

            // Set the Inventory instance in AddPartController
            MainMenuController controller = loader.getController();
 
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle error loading FXML file
        }
		
	}
	
}
