package com.sdz.model.light;

import com.sdz.model.stateLight.I_stateLight;
import com.sdz.model.stateLight.OffState;

public abstract  class Light {

    private final EnumColor color;
    private I_stateLight state=new OffState();

    public Light(EnumColor color){

        this.color=color;
    }

    public EnumColor getColor() {

        return color;
    }

    public  I_stateLight getState(){

        return state;
    }

    public synchronized void setState(I_stateLight state){

        this.state=state;
    }
}
