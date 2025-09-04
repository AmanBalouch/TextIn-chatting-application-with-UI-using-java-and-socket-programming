import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class chatServer {
    private ServerSocket serverSocket;
    private ArrayList<clientHandler> clients = new ArrayList<>();

    public chatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
    }

    public void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());
                // Create a client handler
                clientHandler clientHandler = new clientHandler(socket, this);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                System.out.println("Error accepting client: " + e.getMessage());
            }
        }
    }

    public synchronized void addClientToPosition(clientHandler client, int position) {
        synchronized (clients) {
            // Adjust the size of the list if necessary
            while (position >= clients.size()) {
                clients.add(null);
            }
            clients.set(position, client); // Set the client at the specified position
        }
    }

    public synchronized void sendMessageToClient(String message) {
        String[] parts = message.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Invalid message format. Expected: <ID> <message>");
            return;
        }

        int recipientID;
        try {
            recipientID = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid recipient ID format.");
            return;
        }

        String actualMessage = parts[1];
        actualMessage=actualMessage.trim();
        synchronized (clients) {
            if (recipientID < 0 || recipientID >= clients.size() || clients.get(recipientID) == null) {
                System.out.println("Recipient ID out of range or disconnected.");
                return;
            }

            clientHandler recipient = clients.get(recipientID);
            recipient.sendMessage(actualMessage);
        }
    }

    public synchronized void removeClient(clientHandler clientHandler) {
        synchronized (clients) {
            int index = clients.indexOf(clientHandler);
            if (index != -1) {
                clients.set(index, null); // Leave the slot null for reconnection
            }
        }
    }

    // toString method to represent the server's information as a string
    @Override
    public String toString() {
        return "chatServer{" +
                "serverSocket=" + serverSocket +
                ", numberOfClients=" + clients.size() +
                '}';
    }
}
