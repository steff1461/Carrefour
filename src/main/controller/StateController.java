package main.controller;
import main.model.fire.Fire;
import main.model.light.Light;
import main.model.stateLight.OutState;

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
















