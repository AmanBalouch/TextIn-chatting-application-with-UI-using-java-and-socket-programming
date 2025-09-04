/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AllFiles;
import javax.swing.JOptionPane;
/**
 *
 * @author pc
 */
public class LogginClient extends Client{
    private String username;
    private String password;
    private static String userID=null;
    public LogginClient(String u,String p,String uID,int port){
        super(port);
        try {
            username=u;
            password=p;
            userID=uID;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean checkUserNamePassword(){
        try{
            super.sendMessage("loggin"+username+"  "+password+" "+userID);
            String reply=super.recieveMessage();
            return reply.equals("YES");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    public static String getuserID(){
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static void setUserID(String userID) {
        LogginClient.userID = userID;
    }

    @Override
    public String toString() {
        return "LogginClient{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", userID='" + userID + '\'' +
               '}';
    }
}

