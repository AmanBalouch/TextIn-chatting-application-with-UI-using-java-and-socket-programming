/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AllFiles;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class Contact implements Serializable{
    private int userID; 
    private String name;

    public Contact(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    
    public int getUserID() {
        return userID;
    }

    
    public void setUserID(int userID) {
        this.userID = userID;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                '}';
    }
}
