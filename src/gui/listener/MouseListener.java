package gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.ClientObject;
import gui.ClientObjectRemoveButton;

public class MouseListener extends MouseAdapter {

    private Object obj;

    public MouseListener(Object obj) {
        this.obj = obj;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 1) {
            if(this.obj instanceof ClientObject) {
                ((ClientObject) this.obj).mouseClick();
            }
            if(this.obj instanceof ClientObjectRemoveButton) {
                ((ClientObjectRemoveButton) this.obj).mouseClick();
            }
        }
    }
}