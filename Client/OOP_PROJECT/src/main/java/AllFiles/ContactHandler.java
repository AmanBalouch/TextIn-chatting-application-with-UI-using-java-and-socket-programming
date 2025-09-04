package AllFiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ContactHandler class to manage a list of contacts.
 */
public class ContactHandler implements Serializable{
    private ArrayList<Contact> contacts;

    public ContactHandler() {
        this.contacts = new ArrayList<>();
    }
    
    public ContactHandler(ArrayList<Contact> c) {
        this.contacts = c;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public boolean removeContact(int userID) {
        for (Contact contact : contacts) {
            if (contact.getUserID() == userID) {
                contacts.remove(contact);
                return true;
            }
        }
        return false;
    }

    public Contact findContactByID(int userID) {
        for (Contact contact : contacts) {
            if (contact.getUserID() == userID) {
                return contact;
            }
        }
        return null;
    }

    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
