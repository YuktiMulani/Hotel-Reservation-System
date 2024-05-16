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

public class Room {
	private String room_id;
	private String room_type;
	private double rate;
	
	public Room(String room_id, String room_type, double rate) {
		this.room_id = room_id;
		this.room_type = room_type;
		this.rate = rate;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
}

