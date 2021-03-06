package main.model.light;

import main.model.stateLight.I_stateLight;
import main.model.stateLight.OffState;

import java.awt.*;

public abstract  class Light {

    private final Color color;
    private I_stateLight state=new OffState();

    public Light(Color color){

        this.color=color;
    }

    public Color getColor() {

        return color;
    }

    public  I_stateLight getState(){

        return state;
    }

    public synchronized void setState(I_stateLight state){

        this.state=state;
    }
}
