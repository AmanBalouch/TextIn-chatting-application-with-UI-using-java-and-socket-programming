package AllFiles;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
public class SignUpClient extends Client{
    private String username;
    private String password;
    private static String userID;
    
    public SignUpClient(String u,String p,int port){
        super(port);
        username=u;
        password=new GeneratePasswordManually(p).generatePassword();
    }
    
    public SignUpClient(String u,int port){
        super(port);
        username=u;
        password=new GenerateAutoPassword().generatePassword();
    }
    
    public boolean writeUserNamePassInFile(){
        try{
            super.sendMessage("signup"+username+"  "+password);
            String reply=super.recieveMessage();
            System.out.println(reply);
            userID=reply;
            return true;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public static String getuserID(){
        return userID;
    }
}
