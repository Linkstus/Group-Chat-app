
/*
    Course Number: CS 4345
    Semester/Year: Spring(2019)
    Assignment Id: Assignment 3
    Names: James Jackson, Johnnie Oldfield
*/

import java.io.*;
import java.util.*;
import java.net.*;

// Thread that writes messages to server
public class Writer extends Thread {
	private PrintWriter writer;
	private Socket socket;
	private Client client;

	public Writer(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;

		try {
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);
		} catch (IOException e) {
			System.out.println("Error in writer.");
		}
	}

	public void run() {
		// Get name from user and set it
		Console console = System.console();
		String name = console.readLine("\nEnter your name: ");
		client.setName(name);
		writer.println(name);

		String line = "";
		do {
			line = console.readLine(client.getName() + ": ");
			writer.println(line);
		} while (!line.equals("/q"));

		try {
			socket.close();
		} catch (IOException ex) {
			System.out.println("Error in writer.");
		}
	}
}