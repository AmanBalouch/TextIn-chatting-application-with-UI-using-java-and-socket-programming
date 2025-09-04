💬 TextIn Chatting Application
A Java-based Chatting Application built with Socket Programming and Object-Oriented Programming (OOP) principles.
The project provides real-time messaging with Login/Signup authentication and is designed as a semester project for OOP.
📌 Features
🔐 User Authentication
Signup and Login system
💬 Real-Time Messaging using Java Sockets
👥 Multiple Clients Support (users on the same Wi-Fi)
📂 Server-Client Architecture
📑 Contact Management – add contacts using their IP address
🎯 OOP Concepts Applied
Classes & Objects
Inheritance
Polymorphism
Encapsulation
Abstraction
📊 UML Diagrams
UML diagrams are uploaded in this repository to explain:
System design
Class relationships
Flow of communication
⚙️ Project Structure
TextIn-Chatting-Application/
│── server/
│   ├── LoginServer.java
│   ├── MessagingServer.java
│
│── client/
│   ├── FrontEndMain.java
│
│── UML/
│   ├── ClassDiagram.png
│   ├── UseCaseDiagram.png
│
│── README.md
🚀 How to Run
1. Run the Servers
Inside the server folder, run:
LoginServer.java
MessagingServer.java
Both must be running before starting clients.
2. Run the Client (Front End)
Inside the client folder, run:
FrontEndMain.java
3. Connect Clients
Ensure:
Server is running on one PC
All clients are connected to the same Wi-Fi network
When adding a contact:
Enter the IP address of the other user
📡 Technology Stack
Language: Java
Networking: Socket Programming
Paradigm: Object-Oriented Programming (OOP)
🖥️ Example Flow
User signs up or logs in
User adds a contact by entering the other user’s IP
Both users can now exchange real-time messages via server
🚀 Future Enhancements
GUI-based chat application
File sharing between users
End-to-end encryption
Group chat support
🤝 Contributing
Contributions are welcome! Fork the repo, make improvements, and submit a pull request.
📜 License
This project is licensed under the MIT License – free to use, modify, and distribute.
