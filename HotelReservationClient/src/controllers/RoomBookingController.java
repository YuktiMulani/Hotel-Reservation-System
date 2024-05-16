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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Reservation;
import models.Room;


public class RoomBookingController {
	private List<String>SingleRoomids=new ArrayList<>();
	private List<String>DoubleRoomids=new ArrayList<>();
	private List<String>DeluxeRoomids=new ArrayList<>();
	private List<String>PenthouseRoomids=new ArrayList<>();
private List<String>lsr=new ArrayList<>();
private List<String>ldr=new ArrayList<>();
private List<String>ldlr=new ArrayList<>();
private List<String>lpr=new ArrayList<>();
	@FXML
	private TextField peoplecountfield;
	@FXML
	private TextField dayscountfield;
	@FXML
	private Button donebutton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label roomsAvailabel;

	@FXML
	private DatePicker checkIn;
	@FXML
	private DatePicker CheckOut;
	@FXML
	private CheckBox cb1;
	@FXML
	private CheckBox cb2;
	@FXML
	private CheckBox cb3;
	@FXML
	private CheckBox cb4;
	@FXML
	private Label ratelabel;
	@FXML
	private Label nosr;
	@FXML
	private Label nodr;
	@FXML
	private Label nopr;
	@FXML
	private Label noder;
	
	@FXML
	private Spinner<Integer> sp1;
	@FXML
	private Spinner<Integer> sp2;
	@FXML
	private Spinner<Integer> sp3;
	@FXML
	private Spinner<Integer> sp4;

	private double SingleRoomRate=0;
	private double doubleRoomRate=0;
	private double deluxeRoomRate=0;
	private double penthouseRate=0;
	
	
	@FXML
    public void initialize() {
		// Set the spinner's value range from 1 to 10
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        sp1.setValueFactory(valueFactory);
        sp2.setValueFactory(valueFactory2);
        sp3.setValueFactory(valueFactory3);
        sp4.setValueFactory(valueFactory4);
        sp1.setVisible(false);
        sp2.setVisible(false);
        sp3.setVisible(false);
        sp4.setVisible(false);
        nosr.setVisible(false);
        nodr.setVisible(false);
        nopr.setVisible(false);
        noder.setVisible(false);
		ratelabel.setText("0");
		
		String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
		String username = "root";
		String password = "Yukti@6713";
		
		try {
		    Connection connection = DriverManager.getConnection(url, username, password);
		    // Use the connection object for database operations
		    Statement stmt = connection.createStatement();
		    //get single rooms
		    String query="SELECT RoomID, RoomType, Status FROM Rooms WHERE Status = 'Available';";
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		    	String roomid=rs.getString("RoomID");
		        String roomType = rs.getString("RoomType");
		        String status=rs.getString("Status");
		        if(roomType.equals("Single Room")&&status.equals("Available")) {
		        	lsr.add(roomType);
		        	SingleRoomids.add(roomid);
		        }else if(roomType.equals("Double Room")&&status.equals("Available")) {
		        	ldr.add(roomType);
		        	DoubleRoomids.add(roomid);
		        }else if(roomType.equals("Deluxe Room")&&status.equals("Available")) {
		        	ldlr.add(roomType);
		        	DeluxeRoomids.add(roomid);
		        }else if(roomType.equals("Penthouse")&&status.equals("Available")) {
		        	lpr.add(roomType);
		        	PenthouseRoomids.add(roomid);
		        }
		        
		        
		    }
		    System.out.println(SingleRoomids.size());
		    System.out.println(lsr);
	        System.out.println(ldr);
	        System.out.println(ldlr);
	        System.out.println(lpr);
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		roomsAvailabel.setText("Single Rooms("+lsr.size()+") Double Rooms("+ldr.size()+") Delux Room("+ldlr.size()+") Penthouses ("+lpr.size()+")");
		dayscountfield.textProperty().addListener((oldValue,observable,newValue)->{
			if (!newValue.matches("\\d*")) {
	            // If the new value does not match the pattern of digits, show an alert
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Invalid Input");
	            alert.setHeaderText(null);
	            alert.setContentText("Please enter a valid integer for the number of days you plan to stay.");
	            alert.showAndWait();

	            // Replace the invalid value with an empty string
	            dayscountfield.setText(newValue.replaceAll("[^\\d]", ""));
	        }
			SingleRoomRate=(100*sp1.getValue());
			doubleRoomRate=(150*sp2.getValue());
			deluxeRoomRate=(200*sp3.getValue());
			penthouseRate=(500*sp4.getValue());
			ratelabel.setText(String.valueOf(SingleRoomRate+doubleRoomRate+deluxeRoomRate+penthouseRate));
		});
		peoplecountfield.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	            // If the new value does not match the pattern of digits, show an alert
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Invalid Input");
	            alert.setHeaderText(null);
	            alert.setContentText("Please enter a valid integer for the number of people.");
	            alert.showAndWait();

	            // Replace the invalid value with an empty string
	            peoplecountfield.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
    
	}
	
	@FXML
	public void cb1Selected() {
		if(cb1.isSelected()) {
			nosr.setVisible(true);
			sp1.setVisible(true);
		}
		
	}
	@FXML
	public void cb2Selected() {
		if(cb2.isSelected()) {
			nodr.setVisible(true);
			sp2.setVisible(true);

		}
		
	}
	@FXML
	public void cb3Selected() {
		if (cb3.isSelected()) {
			noder.setVisible(true);
			sp3.setVisible(true);

		}
		
	}
	@FXML
	public void cb4Selected() {
		if(cb4.isSelected()) {
			nopr.setVisible(true);
			sp4.setVisible(true);
		}
		
	}
	@FXML
	public void doneButtonPressed() {
		int roomCapacity = sp1.getValue() + (2 * sp2.getValue()) + (2 * sp3.getValue()) + (4 * sp4.getValue());
		Random rand=new Random();
		Date checkindate=Date.valueOf(checkIn.getValue());
		Date checkoutdate=Date.valueOf(CheckOut.getValue());
		int bookingid=rand.nextInt(101);
		int guestCount=Integer.parseInt(peoplecountfield.getText());
		int days=Integer.parseInt(dayscountfield.getText());
		int numberofrooms=sp1.getValue()+sp2.getValue()+sp3.getValue()+sp4.getValue();
		if (guestCount > roomCapacity) {
	        // Alert the user that the selected rooms cannot accommodate all guests
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Room Capacity Exceeded");
	        alert.setContentText("Selected rooms cannot accommodate all guests.");
	        alert.showAndWait();
	        return; // Exit the method to prevent further execution
		}
		else {
			String roomid="";
			String roomtype="";
			if (cb1.isSelected()) {
				roomtype=roomtype+"Single_Room ";
			}
			if (cb2.isSelected()) {
				roomtype=roomtype+"Double_Room ";
			}
			if (cb3.isSelected()) {
				roomtype=roomtype+"Deluxe_Room ";
			}
			if (cb4.isSelected()) {
				roomtype=roomtype+"Penthouse ";
			}
			if(roomtype.contains("Single_Room")) {
				for(int i=0;i<sp1.getValue();i++) {
					roomid=roomid+String.valueOf(SingleRoomids.get(lsr.size()-1))+" ";
				}
			}
			if(roomtype.contains("Double_Room")) {
				for(int i=0;i<sp2.getValue();i++) {
					roomid=roomid+String.valueOf(DoubleRoomids.get(DoubleRoomids.size()-1))+" ";
				}
			}
			if(roomtype.contains("Deluxe_Room")) {
				for(int i=0;i<sp3.getValue();i++) {
					roomid=roomid+String.valueOf(DeluxeRoomids.get(DeluxeRoomids.size()-1))+" ";
				}
			}
			if(roomtype.contains("Penthouse")) {
				for(int i=0;i<sp4.getValue();i++) {
					roomid=roomid+String.valueOf(PenthouseRoomids.get(PenthouseRoomids.size()-1))+" ";
				}
				
			}
			double rate=Double.parseDouble(ratelabel.getText());
			
			Room room =new Room(roomid,roomtype,rate);
			Reservation res=new Reservation(bookingid,checkindate,checkoutdate,guestCount,days,numberofrooms);
			try {
	            // Load the FXML file for adding a part
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GuestInfo.fxml"));
	            Parent root = loader.load();

	            // Set the Inventory instance in AddPartController
	            GuestInfoController controller = loader.getController();
	            controller.setRoom(room);
	            controller.setRes(res);
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle error loading FXML file
	        }
			Stage stage = (Stage) donebutton.getScene().getWindow();
	        stage.close();
		}
		
	}
	
	@FXML
	public void cancelButtonPressed() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
		
	}
	
	
}
