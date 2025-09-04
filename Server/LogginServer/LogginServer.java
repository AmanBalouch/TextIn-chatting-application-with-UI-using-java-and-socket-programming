import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LogginServer {
    private ServerSocket serverSocket;
    private Scanner in;
    private PrintWriter out;

    public LogginServer(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }
    
    public void checkLogginPasswordOrWriteLogginPassInFile(){
        Socket socket;
        try {
            socket = serverSocket.accept();
            System.out.println("Connection Established.");
            in=new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            String str=in.nextLine();
            System.out.println(str);
            if(str.startsWith("loggin")){
                boolean checker=checkInFile(str.substring(6), "/Users/pc/Desktop/3rd Sem Lab/OOP LAb/Semester Project Chatting Application/Server/passwords.txt");
                PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
                if(checker)
                    out.println("YES");
                else
                    out.println("NO");
            }
            else if(str.startsWith("signup")){
                try (FileWriter fileWriter = new FileWriter("/Users/pc/Desktop/3rd Sem Lab/OOP LAb/Semester Project Chatting Application/Server/passwords.txt", true)){
                    String userID=findUserID();
                    fileWriter.write(str.substring(6)+" "+userID+"\n");
                    out.println(userID);
                }catch(Exception e){
                    System.out.println(e);
                    out.println("NO");
                }
            }   
            else
                System.out.println("Invalid.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkInFile(String input, String path){
        try{
            File myFile=new File(path);
            Scanner sc=new Scanner(myFile);
            while (sc.hasNextLine()) {
                String Line=sc.nextLine();
                if(Line.equals(input))
                    return true;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    private String findUserID(){
        int lineCount = 0;
        String userID = "";
        try {
            File myFile = new File("/Users/pc/Desktop/3rd Sem Lab/OOP LAb/Semester Project Chatting Application/Server/passwords.txt");
            
            if (!myFile.exists()) {
                System.out.println("File does not exist!");
                return "1";
            }
            
            Scanner sc = new Scanner(myFile);
            
            // Count the number of lines
            while (sc.hasNextLine()) {
                lineCount++;
                sc.nextLine();  
            }
            userID = String.valueOf(lineCount); 
            System.out.println("Generated User ID: " + userID);
            
        } catch (Exception e) {
            System.out.println("Error while generating User ID: " + e.getMessage());
            e.printStackTrace();
            userID = "1"; 
        }
        return userID;
    }
     @Override
    public String toString() {
        return "LogginServer{" +
                "serverSocket=" + serverSocket +
                ", in=" + in +
                ", out=" + out +
            '}';
    }
}

   