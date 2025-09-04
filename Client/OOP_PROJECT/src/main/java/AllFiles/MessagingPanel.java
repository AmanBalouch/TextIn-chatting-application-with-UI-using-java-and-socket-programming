package AllFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MessagingPanel extends JPanel {
    private ArrayList<String> messageList;
    private Serialize serialize;
    private int userID;
    private MessageClient messageClient;
    private JTextField MessageTextField;
    private JPanel MessagingAreaPanel;
    private JScrollPane MessagingPanelScrollPane;
    private JButton SendButton;
    private JPanel TextFieldAndSendButtonPanel;

    public MessagingPanel(int port,int userID) {
        serialize=new Serialize(userID+"MessageList.rar");
        this.userID=userID;
        messageClient=new MessageClient(port);
        String myUserID=SignUpClient.getuserID()!=null?SignUpClient.getuserID():LogginClient.getuserID();
        messageClient.sendMessageToServer(myUserID);
        MessageTextField = new JTextField();
        MessagingAreaPanel = new JPanel();
        MessagingPanelScrollPane = new JScrollPane();
        SendButton = new JButton();
        TextFieldAndSendButtonPanel = new JPanel();

        // Set properties for MessagingPanel (this)
        this.setBackground(new java.awt.Color(204, 255, 204));
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 0), 3));

        // Set properties for TextFieldAndSendButtonPanel
        TextFieldAndSendButtonPanel.setBackground(new java.awt.Color(204, 255, 204));
        SendButton.setText("Send");

        // Layout for TextFieldAndSendButtonPanel
        javax.swing.GroupLayout TextFieldAndSendButtonPanelLayout = new javax.swing.GroupLayout(TextFieldAndSendButtonPanel);
        TextFieldAndSendButtonPanel.setLayout(TextFieldAndSendButtonPanelLayout);
        TextFieldAndSendButtonPanelLayout.setHorizontalGroup(
            TextFieldAndSendButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TextFieldAndSendButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SendButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TextFieldAndSendButtonPanelLayout.setVerticalGroup(
            TextFieldAndSendButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TextFieldAndSendButtonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TextFieldAndSendButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SendButton))
                .addContainerGap())
        );

        // Set properties for MessagingAreaPanel
        MessagingAreaPanel.setBackground(new java.awt.Color(204, 255, 204));
        MessagingAreaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 0)));
        MessagingAreaPanel.setPreferredSize(new java.awt.Dimension(540, 625));


        //Layout
        MessagingAreaPanel.setLayout(new BoxLayout(MessagingAreaPanel, BoxLayout.Y_AXIS));

        // Set MessagingPanelScrollPane viewport
        MessagingPanelScrollPane.setViewportView(MessagingAreaPanel);
        MessagingPanelScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        MessagingPanelScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        // Layout for MessagingPanel (this)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldAndSendButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MessagingPanelScrollPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MessagingPanelScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldAndSendButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Add functionality to SendButton
        SendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = MessageTextField.getText();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    MessageTextField.setText("");
                    messageList.add("S"+message);
                    serialize.Serialization(messageList);
                }
            }
        });
        
        new Thread(() -> {
            while (true) {
                try {
                    String receivedMessage = messageClient.recieveMessageFromServer();
                    if (receivedMessage != null && !receivedMessage.isEmpty()) {
                        SwingUtilities.invokeLater(() -> receiveMessage(receivedMessage));
                        messageList.add("R"+receivedMessage);
                        serialize.Serialization(messageList);
                    }
                    Thread.sleep(100); 
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.err.println("Message receiving thread interrupted.");
                    break;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        if(SignUpClient.getuserID()!=null){
            messageList=new ArrayList<String>();
        }
        else{
            messageList=(ArrayList<String>)serialize.Deserialization();
            if (messageList == null) {
                messageList = new ArrayList<String>();
            }
            for(String message:messageList){
                if(message.charAt(0)=='S'){
                    message=message.substring(1);
                    sendMessageGui(message);
                }
                else if(message.charAt(0)=='R'){
                    message=message.substring(1);
                    recieveMessageGui(message);
                }
                else;
            }
        }
    }
    

    public void sendMessage(String message) {
        String myUserID;
        if(SignUpClient.getuserID() != null){
            myUserID=SignUpClient.getuserID();
        }
        else{
            myUserID=LogginClient.getuserID();
        }
        messageClient.sendMessageToServer(userID+" "+myUserID+" "+ message);
        sendMessageGui(message);
    }
    
    public void receiveMessage(String message) {
        String[] parts = message.split(" ", 2); 
        if (parts.length < 2) return;

        int senderUserID = Integer.parseInt(parts[0]);
        String actualMessage = parts[1];

        if (senderUserID == this.userID) { 
            recieveMessageGui(actualMessage);
        }
    }
    
    private void sendMessageGui(String message){
        MessagingAreaPanel.add(Box.createRigidArea(new java.awt.Dimension(0, 5)));
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
        messagePanel.setOpaque(false);
        messagePanel.add(Box.createHorizontalGlue());
        
        JLabel messageLabel = new JLabel(message);
        messageLabel.setBackground(new java.awt.Color(255, 255, 255));
        messageLabel.setOpaque(true);
        messagePanel.add(messageLabel);
        
        MessagingAreaPanel.setPreferredSize(new java.awt.Dimension(
        MessagingAreaPanel.getPreferredSize().width,
        MessagingAreaPanel.getPreferredSize().height+5+ messagePanel.getPreferredSize().height
        ));
        
        MessagingAreaPanel.add(messagePanel);
        JPanel mainFrameMainMessagingPanel=MainFrame.getMainFrameMainMessagingPanel();
        mainFrameMainMessagingPanel.revalidate();
        mainFrameMainMessagingPanel.repaint(); 
        
        MessagingAreaPanel.revalidate();
        MessagingAreaPanel.repaint();
    }
    
    private void recieveMessageGui(String message){
        MessagingAreaPanel.add(Box.createRigidArea(new java.awt.Dimension(0, 5)));
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
        messagePanel.setOpaque(false);
        messagePanel.add(Box.createHorizontalGlue());
        
        JLabel messageLabel = new JLabel(message);
        messageLabel.setBackground(new java.awt.Color(173, 216, 230));
        messageLabel.setOpaque(true);
        messagePanel.add(messageLabel);
        
        MessagingAreaPanel.setPreferredSize(new java.awt.Dimension(
        MessagingAreaPanel.getPreferredSize().width,
        MessagingAreaPanel.getPreferredSize().height+5+ messagePanel.getPreferredSize().height
        ));
        
        MessagingAreaPanel.add(messagePanel);
        JPanel mainFrameMainMessagingPanel=MainFrame.getMainFrameMainMessagingPanel();
        mainFrameMainMessagingPanel.revalidate();
        mainFrameMainMessagingPanel.repaint();
        
        MessagingAreaPanel.revalidate();
        MessagingAreaPanel.repaint();
    }
}
