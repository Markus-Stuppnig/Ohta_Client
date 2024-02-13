package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TextPanel extends JPanel {

    private int x;
    private int y;
    private int height;
    private int width;

    private JPanel parent;
    private JLabel selectedUser;

    public String username;

    public TextPanel(String username) {
        this.parent = ClientFrame.panel;
        this.username = username;

        this.x = 330;
        this.y = 100;
        this.height = 620;
        this.width = 720;

        this.setup();
    }

    private void setup() {
        this.setBounds(this.x, this.y, this.width, this.height);
        this.setBackground(new Color(80, 80, 80));

        this.selectedUser = new JLabel();
        this.selectedUser.setText(this.username);
        this.selectedUser.setForeground(new Color(190, 190, 190));
        this.selectedUser.setFont(new Font(this.selectedUser.getFont().getName(), Font.BOLD, 30));

        this.add(this.selectedUser);
        this.parent.add(this);
    }

    public void setSelectedUser(String user) {
        this.username = user;
        this.selectedUser.setText(this.username);
    }
}