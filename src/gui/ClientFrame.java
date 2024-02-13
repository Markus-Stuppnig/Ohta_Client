package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.scrollpanel.ScrollPanel;
import main.Main;
import wyoni.object.YObject;

@SuppressWarnings("serial")
public class ClientFrame extends JFrame {

    public static boolean frameIsStarted = false;

    public static JPanel panel;
    public static InputField inputField;
    //public static TextPanel textPanel;
    public static ScrollPanel scrollPanel;
    public static InputField_Chats inputFieldChats;
    
    public static ArrayList<ClientObject> clients = new ArrayList<ClientObject>();

    private int sizeX, sizeY;
    public static Color color = new Color(100, 100, 100);
    
    public static YObject savedUsers = new YObject(System.getenv("APPDATA") + "/Messenger/ListedChats/" + Main.connectionHandler.username + "_chats.json", false);

    public ClientFrame() {
        this.initGui();
        frameIsStarted = true;
    }

    public void initGui() {
        //Size
        this.sizeX = 1100;
        this.sizeY = 800;

        //init
        this.setTitle(Main.name + " v" + Main.version);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(sizeX, sizeY);
        this.setMinimumSize(new Dimension(sizeX, sizeY));
        this.setResizable(false);
        this.setLayout(null);

        //Main-Panel
        panel = new JPanel();
        panel.setBackground(color);
        panel.setLayout(null);

        //Components
        inputField = new InputField();
        //textPanel = new TextPanel("No User selected");
        scrollPanel = new ScrollPanel();
        scrollPanel.setUserChat("a");
        inputFieldChats = new InputField_Chats();
        
        ArrayList<String> chats = savedUsers.getArray("chats");
        if(chats != null) {
	        for(String chat : chats) {
	        	clients.add(new ClientObject((clients.size() + 1) * 50 + 60, chat));
	        }
        }
        this.updateUsers();

        //End
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
    }

    public void updateUsers() {
        for(Component comp : panel.getComponents()) {
            
            //Do nothing
            if(comp instanceof InputField) {
            }
            else if(comp instanceof ClientObject) {
            	if(Main.connectionHandler.usersCurrentlyOnline != null) {
	            	if(Main.connectionHandler.usersCurrentlyOnline.contains(((ClientObject) comp).username)) {
	            		((ClientObject) comp).circle.shouldRender= true;
	            	}else if(((ClientObject) comp).circle.shouldRender) {
	            		((ClientObject) comp).circle.shouldRender = false;
	            	}
            	}
            }
            else if(comp instanceof InputField_Chats) {
            }
            
            else if(comp instanceof TextPanel) {
                if(Main.connectionHandler.userSelected != null) {
                	if(!Main.connectionHandler.userSelected.equals("")) ((TextPanel) comp).setSelectedUser(Main.connectionHandler.userSelected);
                }
            }
            
            else if(comp instanceof ScrollPanel) {
            	
            }
            else {
                panel.remove(comp);
            }
        }

        /*
        //Register all users
        ArrayList<String> users = Main.connectionHandler.usersCurrentlyOnline;
        
        System.out.print("\n");
        for(String s : users) System.out.print(s);
        System.out.print("\n");
        System.out.println("Wenn das nicht da steht Fehler");
        
        int z = 0;
        for(int i = 0; i < users.size(); i++) {
            z = i;
            if(clients.size() > i) {
                clients.get(i).setUserName(users.get(i));
                System.out.println("Setting: " + users.get(i));
            }else {
                System.out.println("Adding: " + users.get(i));
                ClientObject cO = new ClientObject(i * 50 + 130, users.get(i));
                ClientFrame.clients.add(cO);
            }
        }
        
        int a = clients.size();
        int b = users.size();
        for(int i = 0; i < a - b; i++) {
            panel.remove(clients.get(z + 1));
            System.out.println("Removing: " + clients.get(z + 1));
            clients.remove(z + 1);
        }
        */

        //Important
        this.rebuild();
    }
    
    public void addChat(String user) {
    	clients.add(new ClientObject((clients.size() + 1) * 50 + 130, user));
    	this.rebuild();
    	
    	//save
    	ArrayList<String> objects = savedUsers.getArray("chats");
    	if(objects == null) objects = new ArrayList<String>();
    	objects.add(user);
    	savedUsers.putArray("chats", objects);
    }
    
    public void removeChat(ClientObject obj) {
    	panel.remove(obj);
    	clients.remove(obj);
    	this.rebuild();
    	
    	ArrayList<String> objects = savedUsers.getArray("chats");
    	objects.remove(obj.username);
    	savedUsers.putArray("chats", objects);
    }
    
    public void rebuild() {
    	this.repaint();
        this.revalidate();
    }
}