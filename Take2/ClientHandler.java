import java.net.*;
import java.io.*;

class ClientHandler extends Thread {
    private Socket client;
    private String name = null;
    private BufferedReader fromClient;
    private PrintWriter toClient;
    private String data;

    public ClientHandler(Socket client) {
        this.client = client;
        try {
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toClient = new PrintWriter(client.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("Error somewhere.");
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            if (name == null) {
                enterClientName();
            }
        }
    }

    public void enterClientName() {
        try {
            toClient.println("   Please enter a name!");
            name = fromClient.readLine();
            toClient.println(" Welcome, " + name);

        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }

}