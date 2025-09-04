package AllFiles;


import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
public class Client implements ClientInterface {
    private static String serverIpAddress="192.168.100.144";
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    public Client(int port){
        try{
            socket=new Socket(serverIpAddress,port);
            in=new Scanner(socket.getInputStream());
            out=new PrintWriter(socket.getOutputStream(),true);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void sendMessage(String str){
        try{
            out.println(str);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public String recieveMessage(){
        try{
            String reply=in.nextLine();
            return reply;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public static String getServerIpAddress() {
        return serverIpAddress;
    }

    public static void setServerIpAddress(String serverIpAddress) {
        Client.serverIpAddress = serverIpAddress;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
    
    @Override
    public String toString() {
        return "Client{" +
               "serverIpAddress='" + serverIpAddress + '\'' +
               ", socket=" + (socket != null ? socket.toString() : "Not Connected") +
               ", in=" + (in != null ? in.toString() : "Not Initialized") +
               ", out=" + (out != null ? out.toString() : "Not Initialized") +
               '}';
    }
}
