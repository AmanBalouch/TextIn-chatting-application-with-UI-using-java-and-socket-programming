import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clientHandler implements Runnable {
    private Socket socket;
    private chatServer server;
    private PrintWriter out;
    private BufferedReader in;
    private int position; // Store the position of the client

    public clientHandler(Socket socket, chatServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Pehla message client ka ID position ke liye
            String positionMessage = in.readLine();
            position = Integer.parseInt(positionMessage); // Parse position
            server.addClientToPosition(this, position); // Add to specified position

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received from client at position " + position + ": " + message);
                server.sendMessageToClient(message);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Client disconnected or sent invalid data: " + e.getMessage());
        } finally {
            server.removeClient(this);
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    // toString method to represent the clientHandler's information as a string
    @Override
    public String toString() {
        return "clientHandler{" +
                "socketAddress=" + socket.getInetAddress() +
                ", position=" + position +
                '}';
    }
}
