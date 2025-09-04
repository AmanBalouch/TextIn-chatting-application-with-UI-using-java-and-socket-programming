import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        try {
            chatServer server = new chatServer(12345);
            server.start();
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
}
