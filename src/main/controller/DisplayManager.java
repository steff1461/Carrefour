package main.controller;

import main.model.fire.Fire;
import javax.swing.*;
import java.awt.*;

class DisplayManager {

    private Fire feux;
    private final FireBuilder builder=new FireBuilder();
    private JPanel panFire;
    private EnumFirePosition firePosition;

    DisplayManager(JPanel panFire, EnumFirePosition firePosition){

    setPanFire(panFire);
    setFirePosition(firePosition);
    }

    void addListeners(Fire feux){

     setFeux(feux);
    }

    public void notifyView() {

        JPanel fireView= builder.buildFire(getFeux(),getFirePosition());

        getPanFire().removeAll();
        getPanFire().setSize(fireView.getWidth(),fireView.getHeight());
        getPanFire().setLayout(new BorderLayout());
        getPanFire().add(fireView,BorderLayout.CENTER);
        getPanFire().updateUI();
    }

    private Fire getFeux() {
        return feux;
    }

    private void setFeux(Fire feux) {
        this.feux = feux;
    }

    private JPanel getPanFire() {
        return panFire;
    }

    private void setPanFire(JPanel panFire) {
        this.panFire = panFire;
    }

    public EnumFirePosition getFirePosition() { return firePosition; }

    public void setFirePosition(EnumFirePosition firePosition) { this.firePosition = firePosition; }
}
