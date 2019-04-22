
/*
    Course Number: CS 4345
    Semester/Year: Spring(2019)
    Assignment Id: Assignment 3
    Names: James Jackson, Johnnie Oldfield
*/

import java.io.*;
import java.util.*;
import java.net.*;

// Thread class used to read messages
public class Reader extends Thread {
	private BufferedReader reader;
	private Socket socket;
	private Client client;

	public Reader(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;

		try {
			InputStream in = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
		} catch (IOException e) {
			System.out.println("Error in Reader.");
		}
	}

	public void run() {
		while (true) {
			try {
				// Read the message
				String line = reader.readLine();
				System.out.println("\n" + line);
				if (client.getName() != null) {
					System.out.print(client.getName() + ": ");
				}

			} catch (IOException e) {
				System.out.println("Error in Reader.");
				break;
			}
		}
	}
}