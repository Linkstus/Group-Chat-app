import java.net.*;
import java.io.*;
import java.util.*;

class Client {
    // Client has name, address, and port
    private String name;
    private String address;
	private int port;

    public Client(String address, int port){
        this.address = address;
        this.port = port;
    }

    public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
    public static void main(String[] args) {
        Client client = new Client("local host", 49153);
        try {
            // Create socket and client, print message
            Socket socket = new Socket(InetAddress.getLocalHost(), 49153);
            System.out.println("Connected to server");
            System.out.println("Welcome to the chat!  Here are some commands: \n'/q' to quit the chat.\n'/w' 'Name' to private message.");

            // Start threads
			new Reader(socket, client).start();
            new Writer(socket, client).start();

		} catch (UnknownHostException e) {
			System.out.println("Cannot connect to Server.");
		} catch (IOException e) {
			System.out.println("Error in Client.");
		}
    }
}