package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.listener.ImagePanel;
import gui.listener.MouseListener;
import main.Main;

@SuppressWarnings("serial")
public class ClientObject extends JPanel {

    private int x;
    private int y;
    private int height;
    private int width;

    private JPanel parent;
    public JPanel x_button;
    private JLabel usernameLabel;

    public Color color;
    private Color colorDefault = new Color(110, 110, 110);
    private Color colorSelected = new Color(120, 120, 120);
    
    public ImagePanel circle;

    public String username;

    public ClientObject(int y, String username) {
        this.parent = ClientFrame.panel;
        this.y = y;
        this.username = username;

        this.x = 30;
        this.height = 40;
        this.width = 250;

        this.setLayout(null);
        this.setup();
    }

    private void setup() {
        this.setBounds(this.x, this.y, this.width, this.height);
        this.setBackground(this.colorDefault);

        this.usernameLabel = new JLabel();
        this.usernameLabel.setText(this.username);
        this.usernameLabel.setForeground(new Color(190, 190, 190));
        this.usernameLabel.setFont(new Font(this.usernameLabel.getFont().getName(), Font.BOLD, 22));
        this.usernameLabel.setBounds(10, 0, this.width, this.height);

        this.circle = new ImagePanel("circle.png", 180, 12);
        
        this.add(this.circle);
        this.add(this.usernameLabel);
        this.parent.add(this);

        this.addMouseListener(new MouseListener(this));
        
        x_button = new ClientObjectRemoveButton(this, 215, 9);
    }

    public void mouseClick() {
        Main.connectionHandler.userSelected = this.username;
        this.setBackground(this.colorSelected);
        //ClientFrame.textPanel.setSelectedUser(this.username);
        Main.frame.rebuild();

        for(ClientObject obj : ClientFrame.clients) {
            if(obj != this) obj.unselect();
        }
    }

    public void unselect() {
        this.setBackground(this.colorDefault);
    }

    public void setUserName(String username) {
        this.username = username;
        this.usernameLabel.setText(this.username);
    }
}