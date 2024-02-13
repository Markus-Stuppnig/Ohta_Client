package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import gui.ClientFrame;
import listener.Listener;
import listener.MainScanner;
import wyoni.object.YObject;

public class Main {

    public static final String name = "Client";
    public static final double version = 0.4;

    public static final String ip_adress = "195.201.1.166";
    //public static final String ip_adress = "91.115.62.172";
    public static final int port = 4200;

    public static ConnectionHandler connectionHandler = new ConnectionHandler();
    public static Thread listenerThread = new Thread(new Listener());
    public static Thread mainScanner = new Thread(new MainScanner());
    public static YObject chathistory;

    public static ClientFrame frame;

    public static void main(String[] args) {

        mainScanner.start();
    }


    public static void setup(String username) {
        //Connection to Server
        if(connectionHandler.connectTo(username, ip_adress, port)) {
            startFrame();
            listenerThread.start();
            connectionHandler.usersCurrentlyOnline = new ArrayList<String>();
            connectionHandler.sendMessage("Request-Users-Online");
        }
        //Fail
        else {
            System.out.println("Error: Connection failed");
            shutdown();
        }
    }

    public static void startFrame() {
        frame = new ClientFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Shutting down from Client GUI X");
                connectionHandler.sendMessage("Disconnect");
                Main.shutdown();
            }
        });
    }


    public static void shutdown() {
        System.out.println("Shutdown initiated");
        listenerThread.interrupt();
        mainScanner.interrupt();
        System.exit(0);

    }

}