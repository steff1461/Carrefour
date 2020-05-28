package main.controller;

import main.model.light.Light;
import main.model.stateLight.OutState;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OutLightListener implements MouseListener {


    private  Light light;

    public OutLightListener(Light light){

        setLight(light);
    }

   private void setLight(Light light){

        this.light=light;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        light.setState(new OutState());
    }

    @Override
    public  void mousePressed(MouseEvent e) {

    light.setState(new OutState());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
