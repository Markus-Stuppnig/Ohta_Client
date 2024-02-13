package listener;

import java.util.ArrayList;

import main.Main;


public class Listener implements Runnable {
    
    private boolean messageWhileOffline = false;
    private boolean clientsWhileOflline = false;
    
    public void run() {
        while(true) {
            String message = Main.connectionHandler.getMessage();
            if(message != null) {
                if(message.equals("Still-Using-Connection")) {
                    Main.connectionHandler.sendMessage("Still-Using");
                }
                else if(message.startsWith("Update-Client-")) {  
                    if(message.substring("Update-Client-".length(), message.indexOf(":")).equals("Join")) Main.connectionHandler.usersCurrentlyOnline.add(message.substring(message.indexOf(":") + 1)); 
                    else if(message.substring("Update-Client-".length(), message.indexOf(":")).equals("Disconnect")) Main.connectionHandler.usersCurrentlyOnline.remove(message.substring(message.indexOf(":") + 1));
                    Main.frame.updateUsers();
                }
                else if(message.startsWith("Message-From-Client:")) {
                    String clientmessage =  message.substring("Message-From-Client:".length());
                    ArrayList<String> array;
                    
                    if(Main.chathistory.getArray(clientmessage.substring(0, clientmessage.indexOf(":"))) != null) array = Main.chathistory.getArray(clientmessage.substring(0, clientmessage.indexOf(":")));
                    else array = new ArrayList<String>();
                    
                    array.add(clientmessage);
                    Main.chathistory.putArray(clientmessage.substring(0, clientmessage.indexOf(":")), array);
                    System.out.println(clientmessage);
                }
                else if(message.equals("+Followed-Messages-Got-While_Offline") || messageWhileOffline) {
                    ArrayList<String> array;
                    if(message.equals("+Followed-Messages-Got-While_Offline")) {
                        messageWhileOffline = true;
                        continue;
                    }
                    else if(message.equals("-Followed-Messages-Got-While_Offline")) {
                        messageWhileOffline = false;
                        continue;
                    }
                    else if(Main.chathistory.getArray(message.substring(0, message.indexOf(":"))) != null) {
                        array = Main.chathistory.getArray(message.substring(0, message.indexOf(":")));
                    }
                    else {
                        array = new ArrayList<String>();
                    }
                    array.add(message);
                    Main.chathistory.putArray(message.substring(0, message.indexOf(":")), array);
                    System.out.println(message);
                }
                else if(message.equals("+Followed-Users-Online") || clientsWhileOflline) {
                    if(message.equals("+Followed-Users-Online")) {
                        clientsWhileOflline = true;
                        continue;
                    }
                    else if(message.equals("-Followed-Users-Online")) {
                        clientsWhileOflline = false;
                        Main.frame.updateUsers();
                        continue;
                    }
                    else if(message.startsWith("User-Online:")) {
                        Main.connectionHandler.usersCurrentlyOnline.add(message.substring(message.indexOf(":") + 1));
                    }
                }
                    
                
                else System.out.println("Message from Server: " + message);

            }


        }
    }
}