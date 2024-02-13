package gui.listener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{

    private BufferedImage image;
    private int x, y;
    
    public boolean shouldRender;

    public ImagePanel(String path, int x, int y) {
    	this.x = x;
    	this.y = y;
    	
    	this.setBounds(this.x, this.y, 18, 18);
    	this.setBackground(new Color(110, 110, 110));
    	this.shouldRender = false;
    	           
		try {
			image = ImageIO.read(this.getClass().getClassLoader().getResource(path));
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.shouldRender) g.drawImage(image, 0, 0, 20, 20, this);
    }
}