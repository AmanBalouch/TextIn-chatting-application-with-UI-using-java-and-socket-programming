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
public class MessageClient extends Client {

    // Constructor
    public MessageClient( int port) {
        super(port);
    }

    public void sendMessageToServer(String message) {
        try {
            super.sendMessage(message);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public String recieveMessageFromServer() {
        try {
            String message=super.recieveMessage();
            return message;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}