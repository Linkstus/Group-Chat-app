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

		String line = "";
		do {
			line = console.readLine(name + ": ");
            writer.println(line);
        } while (!line.equals("/q"));

        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error in writer.");
        }
	}
}