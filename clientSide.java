import java.net.*;
import java.io.*;
import java.util.*;

class clientSide {
    static BufferedReader fromServer;
    static PrintWriter toServer;
    static Scanner serverRead = new Scanner(System.in);;
    static boolean close = false;
    static String data = " ";

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("127.0.1", 49153);

            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            toServer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            data = fromServer.readLine();

            System.out.printf("Server: \n%s\n", data);

            chat();

            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Sorry having issues connecting to server.");
            e.printStackTrace();
        }
    }

    public static void chat() {
        try {
            while (!close) {
                data = serverRead.nextLine();

                if (data.equals("close")) {
                    close = true;
                    toServer.println("Closed");
                }

                data = serverRead.nextLine();
                toServer.println(data);
                data = fromServer.readLine();
                System.out.printf("Server: \n%s\n", data);
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}