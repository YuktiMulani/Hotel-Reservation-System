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

import java.util.Date;
public class Reservation {
	private int bookingID;
	private Date checkInDate;
	private Date checkoutDate;
	private int NumberOfGuests;
	private int NumberOfDays;
	private int NumberOfRooms;
	
	
	public Reservation(int bookingID, Date checkInDate, Date checkoutDate,int NumberOfGuests,int NumberOfDays,int NumberOfRooms) {
		this.bookingID = bookingID;
		this.checkInDate = checkInDate;
		this.checkoutDate = checkoutDate;
		this.NumberOfGuests=NumberOfGuests;
		this.NumberOfDays=NumberOfDays;
		this.NumberOfRooms=NumberOfRooms;
	}
	public int getNumberOfRooms() {
		return NumberOfRooms;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		NumberOfRooms = numberOfRooms;
	}
	public int getBookingID() {
		return bookingID;
	}
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	public int getNumberOfGuests() {
		return NumberOfGuests;
	}
	public void setNumberOfGuests(int numberOfGuests) {
		NumberOfGuests = numberOfGuests;
	}
	public int getNumberOfDays() {
		return NumberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		NumberOfDays = numberOfDays;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
	

}
