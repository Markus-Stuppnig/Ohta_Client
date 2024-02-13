package gui.scrollpanel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.ClientFrame;
import main.Main;

@SuppressWarnings("serial")
public class ScrollPanel extends JPanel {

	private int x;
	private int y;
	private int height;
	private int width;
	
	private String userChat;
	
	private JPanel parent;
	
	private JLabel selectedUser;
	
	private ArrayList<JLabel> labels;
	
	public ScrollPanel() {
		this.parent = ClientFrame.panel;
		
		this.x = 330;
        this.y = 100;
        this.height = 620;
        this.width = 720;
		
		this.userChat = null;
		
		this.labels = new ArrayList<JLabel>();
		
		this.setup();
	}
	
	public void setup() {
		this.setBounds(this.x, this.y, this.width, this.height);
        this.setBackground(new Color(120, 80, 80));

        this.selectedUser = new JLabel();
        this.selectedUser.setText(this.userChat);
        this.selectedUser.setForeground(new Color(190, 190, 190));
        this.selectedUser.setFont(new Font(this.selectedUser.getFont().getName(), Font.BOLD, 30));

        this.add(this.selectedUser);
        this.parent.add(this);
	}
	
	public void setUserChat(String userChat) {
		this.userChat = userChat;
		
		this.selectedUser.setText(this.userChat);
		
		ArrayList<String> chat = new ArrayList<String>();
		if(Main.chathistory != null) {
			chat = Main.chathistory.getArray(this.userChat);
			
			for(int i = 0; i < chat.size(); i++) {
				this.newLabel(chat.get(i));
			}
		}
		
		System.out.println(Main.chathistory.getYFile().getFile().getAbsolutePath());
	}
	
	private void newLabel(String text) {
		
		int y = 20;
		if(!this.labels.isEmpty()) this.labels.get(this.labels.size() - 1).getY();
		
		JLabel label = new JLabel();
		label.setText(text);
		label.setForeground(new Color(190, 190, 190));
		label.setBounds(20, y + 30, 10, 10);
		label.setFont(new Font(this.selectedUser.getFont().getName(), Font.BOLD, 10));
		
		this.add(label);
		this.labels.add(label);
	}
}