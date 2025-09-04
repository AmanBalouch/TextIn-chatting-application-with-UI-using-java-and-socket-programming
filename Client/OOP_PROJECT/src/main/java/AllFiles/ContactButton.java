package AllFiles;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
import javax.swing.*;
import java.awt.*;

public class ContactButton extends JButton{
    private Contact newContact;
    private MessagingPanel messagingPanel;

    public ContactButton(String cName,int uID, Icon icon) {
        newContact=new Contact(uID,cName);
        messagingPanel=new MessagingPanel(12345,newContact.getUserID());
        setText(newContact.getName());
        setIcon(icon);
        setBackground(new Color(204, 255, 204));
        setFont(new Font("Hoefler Text", Font.BOLD, 18));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setHorizontalAlignment(SwingConstants.LEFT);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setPreferredSize(new java.awt.Dimension(190, 40)); // Width: 200, Height: 40
        setMaximumSize(new java.awt.Dimension(190, 40));   // Ensure it does not exceed this size
        setMinimumSize(new java.awt.Dimension(190, 40));   // Ensure it does not shrink below this size
        addActionListener(evt -> ContactButtonButtonActionPerformed(evt));
    }

    public ContactButton(Contact c, Icon icon) {
        newContact=c;
        messagingPanel=new MessagingPanel(12345,newContact.getUserID());
        setText(newContact.getName());
        setIcon(icon);
        setBackground(new Color(204, 255, 204));
        setFont(new Font("Hoefler Text", Font.BOLD, 18));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setHorizontalAlignment(SwingConstants.LEFT);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setPreferredSize(new java.awt.Dimension(190, 40)); // Width: 200, Height: 40
        setMaximumSize(new java.awt.Dimension(190, 40));   // Ensure it does not exceed this size
        setMinimumSize(new java.awt.Dimension(190, 40));   // Ensure it does not shrink below this size
        addActionListener(evt -> ContactButtonButtonActionPerformed(evt));
        
    }
    
    public Contact getContact(){
        return newContact;
    }
    
    public void setContact(Contact c){
        this.newContact=c;
    }
    
    private void ContactButtonButtonActionPerformed(java.awt.event.ActionEvent evt){
        JPanel mainFrameMainMessagingPanel=MainFrame.getMainFrameMainMessagingPanel();
        mainFrameMainMessagingPanel.removeAll();
        
        messagingPanel.setSize(mainFrameMainMessagingPanel.getSize());
        mainFrameMainMessagingPanel.add(messagingPanel); 
        mainFrameMainMessagingPanel.revalidate();
        mainFrameMainMessagingPanel.repaint();
    }
}