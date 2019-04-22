import java.net.*;

class MyServer {
    static Thread clients;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(49153);
            System.out.println("Server is on!");

            while (true) {
                clients = new Thread(new ClientHandler(server.accept()));
                clients.start();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong on the server end!");
            e.printStackTrace();
        }
    }
}