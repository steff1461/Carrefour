package com.sdz.controller;
import com.sdz.model.fire.Fire;
import com.sdz.model.light.EnumColor;
import com.sdz.model.light.Light;
import com.sdz.model.stateLight.OutState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StateController {

    private final FireReparator reparator;
    private final ArrayList<Fire> listFire=new ArrayList<>();
    private boolean isAnotherOut=false;

    public StateController(FireReparator reparator){

        this.reparator=reparator;
    }

    public  boolean checkIfOut(List<Color> lightsToOut)  {

        for (Fire feux: listFire) {

            for (Light light: feux.getLightlist()) {

                if (light.getState().getClass()==OutState.class){

                    reparator.addLight(light);
                    setAnotherOut(true);

                    for(Color color: lightsToOut){
                        if (light.getColor()==color){

                            return true;
                        }

                    }
                }
            }
        }

        return false;
    }

    public void addFire(Fire feux) {

        listFire.add(feux);
    }

    public boolean isAnotherOut() {

        return isAnotherOut;
    }

    public void setAnotherOut(boolean isAnotherOut) {
        this.isAnotherOut = isAnotherOut;
    }
}
















