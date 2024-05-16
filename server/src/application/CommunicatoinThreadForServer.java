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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicatoinThreadForServer extends Thread {

	private Socket clientSocket;

	public CommunicatoinThreadForServer(Socket socket) {
		clientSocket = socket;
	}

	@Override
	public void run() {

		try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);) {

			while (true) {
				String ping = input.readLine();
				if (ping.equals("exit")) {
					break;
				}

				output.println("The Ping from the Server is Coming back witht he same message " + ping);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
