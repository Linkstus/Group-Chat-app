import java.net.*;
import java.io.*;

class Myserver {
    static ClientManager manager = new ClientManager();
    static Thread isOpenThread = new Thread(manager);
    static ServerSocket serverSocket;
    static Client clientSocket;
    static PrintWriter toClient;
    static Client tempClient;
    static BufferedReader fromClient;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(49153);
            System.out.println("Server is open!");
            clientSocket = new Client(serverSocket.accept());
            fromClient = new BufferedReader(new InputStreamReader(clientSocket.getSocketId().getInputStream()));
            manager.addClient(clientSocket);
            newClient();

            while (manager.getNumSockets() > 0) {
                if (fromClient.readLine().equals("Close"))
                    manager.removeClient(clientSocket);
            }

        } catch (Exception e) {
            System.out.println("There was an error connecting to the web!");

        }
    }

    public static void newClient() {
        try {
            toClient = new PrintWriter(clientSocket.getSocketId().getOutputStream(), true);
            toClient.println("Please enter a Username: ");
            clientSocket.setUsername(fromClient.readLine());
            toClient.printf("Welcome, %s", clientSocket.getUsername());
        } catch (Exception e) {
            System.out.println("writer failed");
            e.printStackTrace();
        }
    }
}