import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        try {
            LogginServer server=new LogginServer(5001);
            System.out.println(server.toString());
            while(true){
                server.checkLogginPasswordOrWriteLogginPassInFile();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
