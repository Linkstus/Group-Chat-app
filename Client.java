import java.net.*;

class Client {
    private Socket clientSocket;
    private String data;
    private String name;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void setMsg(String data) {
        this.data = data;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getMsg() {
        return data;
    }

    public Socket getSocketId() {
        return clientSocket;
    }

    public String getUsername() {
        return name;
    }

}