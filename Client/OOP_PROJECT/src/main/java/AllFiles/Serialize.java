package AllFiles;

import java.io.*;

public class Serialize {
    private String fileAddress;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Serialize(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public void Serialization(Object objectToSerialize) {
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileAddress));
            objectOutputStream.writeObject(objectToSerialize);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object Deserialization() {
        Object deserializedObject = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileAddress));
            deserializedObject = objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Object has been deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedObject;
    }
    public String getFileAddress() {
        return fileAddress;
    }
    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }
    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }
    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }
    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }
    @Override
    public String toString() {
        return "Serialize{" +
               "fileAddress='" + fileAddress + '\'' +
               ", objectOutputStream=" + (objectOutputStream != null ? objectOutputStream.toString() : "Not Initialized") +
               ", objectInputStream=" + (objectInputStream != null ? objectInputStream.toString() : "Not Initialized") +
               '}';
    }
}

