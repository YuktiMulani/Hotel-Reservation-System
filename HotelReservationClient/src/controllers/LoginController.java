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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField useridfield;
	@FXML
	private Button loginbutton;
	@FXML
	private Button baclbutton;
	@FXML
	private PasswordField passwordfield;
	private String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
	private String username = "root";
	private String password = "Yukti@6713";
	
	@FXML
	private void loginbuttonpressed() {
		try(Connection conn=DriverManager.getConnection(url,username,password)){
			String query="Select * from Credentials where LoginID=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, useridfield.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
            	String password=resultSet.getString("Password");
            	System.out.println(passwordfield.getText());
            	System.out.println(password);
            	if(passwordfield.getText().equals(password)) {
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
            	else {
            		Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Invalid Password");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter the correct Password");
                    alert.showAndWait();
            	}
            	
            }
            else {
            	Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Username");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the correct Username");
                alert.showAndWait();
            }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	@FXML
	private void backbuttonpressed() {
		Stage stage = (Stage) baclbutton.getScene().getWindow();
        stage.close();
	}
}
