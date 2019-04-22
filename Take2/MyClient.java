import java.net.*;
import java.io.*;
import java.util.*;

class MyClient {
    static BufferedReader fromServer;
    static PrintWriter toServer;
    static String data;
    static Scanner user = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 49153);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toServer = new PrintWriter(client.getOutputStream(), true);
            enterUserName();
            connected();
        } catch (Exception e) {
            System.out.println("Error while connecting.");
            e.printStackTrace();
        }
    }

    public static void enterUserName() {
        try {
            data = fromServer.readLine();
            System.out.printf("Server: \n%s\n", data);
            data = user.nextLine();
            toServer.println(data);
            System.out.printf("Server: \n%s\n", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connected() {
        try {
            while (true) {

            }

        } catch (Exception e) {
            System.out.println("Error in connected.");
            e.printStackTrace();
        }
    }
}