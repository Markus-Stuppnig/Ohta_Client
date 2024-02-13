package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import listener.Listener;
import wyoni.object.YObject;


public class ConnectionHandler {
    
    private Socket socket;    
    private int assigned_port;
    
    public Listener listener;
    public BufferedReader reader;
    
    public String userSelected;
    public String username;
    public ArrayList<String> usersCurrentlyOnline;

    
    public boolean connectTo(String username, String ip, int port) {
        try {
            //Save connection
            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            
            //Login Message to Server            
            sendMessage("Connect-To-Network-With-Username:" + username);
            String message = getMessage();
            System.out.println("Got: " + message);
            socket.close();
            if(message.startsWith("Acknowledge-With-Port:")) {
                
                //Send message to sort that your server gave you and save the connection details
                assigned_port = Integer.parseInt(message.substring("Acknowledge-With-Port:".length()));
                socket = new Socket(ip, assigned_port);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                sendMessage("Login-In-With-Username:" + username);
                System.out.println(getMessage());
                Main.chathistory = new YObject(System.getenv("APPDATA") + "/Messenger/" + username + "_chathistory_messages.json" , false);
            
            }else if(message.startsWith("Error-405")) {
                System.out.println("Error-405");
                return false;
            }
            return true;
            
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Sends message to selected client
    public void sendMessagetoClient(String message) {
        if(userSelected != null) {
            ArrayList<String> array;
            if(Main.chathistory.getArray(Main.connectionHandler.userSelected) != null) {
                array = Main.chathistory.getArray(Main.connectionHandler.userSelected);
            }
            else {
                array = new ArrayList<String>();
            }
            array.add(Main.connectionHandler.username + ":" + message);
            Main.chathistory.putArray(Main.connectionHandler.userSelected, array);
            this.sendMessage("Message:" + userSelected + ":" + message);
        } else System.out.println("Error: No user selected");
    }
       
    //Sends a message to server
    public void sendMessage(String message) {
        try {
            socket.getOutputStream().write(new String(message + "\n").getBytes());
            socket.getOutputStream().flush();                
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: sendMessage failed");
            try {
                socket.close();
                Main.shutdown();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
        }
    }
    
    //Gets the message from Server
    public String getMessage() {
        synchronized(this) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: getMessage failed");
                try {
                    socket.close();
                    Main.shutdown();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }
}