import java.net.*;
import java.util.*;

class ClientManager extends Thread {
    private ArrayList<Client> clientSockets = new ArrayList<>();
    private Socket tempSocket;

    public ClientManager() {

    }

    public void addClient(Client client) {
        clientSockets.add(client);
    }

    public int getNumSockets() {
        return clientSockets.size();
    }

    // returns first socket in list
    public Client getSocket() {
        return clientSockets.get(0);
    }

    public void removeClient(Client client) {
        clientSockets.remove(client);
    }

    // returns specified socket in list
    public Client getSocket(int num) {
        return clientSockets.get(num);
    }

}