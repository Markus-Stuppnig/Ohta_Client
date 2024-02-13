package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.KeyListener;
import main.Main;

@SuppressWarnings("serial")
public class InputField_Chats extends JTextField {

	private int x;
    private int y;
    private int height;
    private int width;

    private JPanel parent;
    public String placeHolder;

    public InputField_Chats() {
        this.parent = ClientFrame.panel;

        //TODO: PlaceHolder
        this.placeHolder = "";

        this.x = 0;
        this.y = 550;
        this.height = 50;
        this.width = 300;

        this.setup();
    }

    private void setup() {
    	//ClientObjectRemoveButton x_button = new ClientObjectRemoveButton();
    	
        this.setBounds(this.x, this.y, this.width, this.height);
        this.setBackground(new Color(170, 170, 170));
        this.setText(this.placeHolder);

        this.parent.add(this);

        this.addKeyListener(new KeyListener((Object) this));
    }

    public void keyEnter() {
    	if(this.getText().equals(Main.connectionHandler.username)) return;
    	for(ClientObject cO : ClientFrame.clients) if(cO.username.equals(this.getText())) return;
    		
        Main.frame.addChat(this.getText());
        this.setText("");
    }
}