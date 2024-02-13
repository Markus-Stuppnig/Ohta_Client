package gui;

import java.awt.Color;

import javax.swing.JPanel;

import gui.listener.MouseListener;
import main.Main;

@SuppressWarnings("serial")
public class ClientObjectRemoveButton extends JPanel {

	private int x;
    private int y;
    private int height;
    private int width;
    
	private ClientObject parent;
	
	public ClientObjectRemoveButton(ClientObject parent, int x, int y) {
		this.parent = parent;
		
		this.y = y;
        this.x = x;
        this.height = 24;
        this.width = 24;
        
		this.setLayout(null);
		this.setup();
	}
	
	private void setup() {
        this.setBounds(this.x, this.y, this.width, this.height);
        this.setBackground(new Color(230, 150, 150));

        this.parent.add(this);

        this.addMouseListener(new MouseListener(this));
    }

    public void mouseClick() {
        Main.frame.removeChat(this.parent);
        System.out.println("click");
    }
}
