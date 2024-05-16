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
package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCode {
	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(5000)) {

			System.out.println("Server is up and running....");
			while (true) {
				Socket clientSocket = ss.accept();// listener function to listen for the clients
				CommunicatoinThreadForServer test = new CommunicatoinThreadForServer(clientSocket);
				test.start();
				System.out.println("Client is connected....");


			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
