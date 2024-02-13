package gui.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gui.InputField;
import gui.InputField_Chats;

public class KeyListener extends KeyAdapter {

    private Object obj;

    public KeyListener(Object obj) {
        this.obj = obj;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(this.obj instanceof InputField) {
                ((InputField) this.obj).keyEnter();
            }
            if(this.obj instanceof InputField_Chats) {
                ((InputField_Chats) this.obj).keyEnter();
            }
        }
    }
    
    /*
    private void keyEnter() {
    	System.out.println("hallo");
    }*/
}