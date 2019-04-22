
/*
    Course Number: CS 4345
    Semester/Year: Spring(2019)
    Assignment Id: Assignment 3
    Names: James Jackson, Johnnie Oldfield
*/

import java.net.*;
import java.io.*;
import java.util.*;

// Handler thread so sever can handle multiple clients
class ClientHandler extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter writer;
    private String ClientName;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            OutputStream out = socket.getOutputStream();
            writer = new PrintWriter(out, true);

            // Get user name and add it to the set
            String name = reader.readLine();
            server.addName(name);

            // Tell all users a new user has joined the chat
            String message = name + " has joined.";
            server.broadcast(message, this);

            // Run until terminated with '/q'
            String line = "";
            do {
                line = reader.readLine();
                // Use '/w' to private messsage a specific user
                if (line.contains("/w")) {
                    // Split line into '/w/', 'username', 'message'
                    String[] arr = line.split(" ");
                    // allows us to break long messages up into smaller ones
                    String msgs = processMessage(arr);
                    String msg = String.format(name + "(Whispered to you): " + msgs);
                    System.out.println(msg);
                    server.whisper(msg, server.getHandlers().get(server.getNames().indexOf(ClientName)));
                }
                // Else broadcast message to all users
                else {
                    message = name + ": " + line;
                    server.broadcast(message, this);
                }
            } while (!line.equals("/q"));

            // Remove user and close socket. Notify chat that user has left.
            server.removeUser(name, this);
            socket.close();
            message = name + " has left.";
            server.broadcast(message, this);

        } catch (IOException e) {
            System.out.println("Error in ClientHandler" + e.getMessage());
            e.printStackTrace();
        }
    }

    // Send message
    public void sendMessage(String message) {
        writer.println(message);
    }

    // Handles larger messages
    public String processMessage(String arr[]) {
        ArrayList<String> message = new ArrayList<>(Arrays.asList(arr));
        message.remove(0);
        ClientName = message.get(0);
        message.remove(0);
        String temp = "";

        for (int i = 0; i < message.size(); i++) {
            temp += " ";
            temp += message.get(i);
        }

        return temp;
    }
}