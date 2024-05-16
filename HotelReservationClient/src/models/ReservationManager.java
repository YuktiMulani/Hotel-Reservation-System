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
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class ReservationManager {
	private int bookingid;
	private String name;
	private String roomtype;
	private int noofrooms;
	private int noofdays;
	private String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
	private String username = "root";
	private String password = "Yukti@6713";
	
	
	public ReservationManager() {
	}


	public ReservationManager(int bookingid, String name, String roomtype, int noofrooms, int noofdays) {
		this.bookingid = bookingid;
		this.name = name;
		this.roomtype = roomtype;
		this.noofrooms = noofrooms;
		this.noofdays = noofdays;
	}


	public int getBookingid() {
		return bookingid;
	}


	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getNoofrooms() {
		return noofrooms;
	}


	public void setNoofrooms(int noofrooms) {
		this.noofrooms = noofrooms;
	}


	public int getNoofdays() {
		return noofdays;
	}


	public void setNoofdays(int noofdays) {
		this.noofdays = noofdays;
	}


	public void addReservation(Room room,Reservation res,GuestInfo guest) {
		String query="insert into Reservations values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try (Connection conn=DriverManager.getConnection(url,username,password);
			// Use the connection object for database operations
			PreparedStatement pstmt = conn.prepareStatement(query)){
				pstmt.setInt(1, res.getBookingID());
	            pstmt.setString(2, room.getRoom_id());
	            pstmt.setInt(3, guest.getGuestID());
	            pstmt.setString(4, guest.getTitle());
	            pstmt.setString(5, guest.getFirstname());
	            pstmt.setString(6, guest.getLastname());
	            pstmt.setString(7, guest.getAddress());
	            pstmt.setObject(8, guest.getPhone());
	            pstmt.setString(9, guest.getEmail());
	            pstmt.setInt(10, res.getNumberOfGuests());
	            pstmt.setString(11, room.getRoom_type());
	            pstmt.setDate(12, (java.sql.Date) res.getCheckInDate());
	            pstmt.setDate(13, (java.sql.Date) res.getCheckoutDate());
	            pstmt.setInt(14, res.getNumberOfDays());
	            pstmt.setDouble(15, room.getRate());
	            pstmt.setInt(16, res.getNumberOfRooms());
	            int rowsInserted = pstmt.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("A new reservation was inserted successfully!");
	            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getRoomtype() {
		return roomtype;
	}


	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}
}
