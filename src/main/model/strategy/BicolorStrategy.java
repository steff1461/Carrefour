package main.model.strategy;

import main.model.fire.BicolorFire;
import main.model.fire.Fire;
import main.model.light.EnumColor;
import main.model.light.Light;
import main.model.stateFire.EnumState;
import main.model.stateLight.I_stateLight;
import main.model.stateLight.OffState;
import main.model.stateLight.OnState;
import main.model.stateLight.OutState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BicolorStrategy implements I_strategy {

    private long timeTowait=0;
    private boolean onlyActive= false;

    @Override
    public boolean changeLight(Fire fire) {

        BicolorFire feux= (BicolorFire)fire;

        switch (feux.getCurrentState()){

            case GreenState:

                if(onlyActive){

                    onlyActive=false;
                    feux.setCurrentState(EnumState.RedState);
                    changeLight(feux);
                }

                else {

                    setOffFire(feux);
                    changeStateLight(feux.getGreenLight(), new OnState());
                    setTimeToWait(5000);
                    feux.setCurrentState(EnumState.RedState);
                }
                return false;


            case RedState:

                if(onlyActive){

                    onlyActive=false;
                    feux.setCurrentState(EnumState.GreenState);
                    changeLight(feux);
                }

                else {

                    setOffFire(feux);
                    changeStateLight(feux.getRedLight(), new OnState());
                    setTimeToWait(1000);
                    feux.setCurrentState(EnumState.GreenState);
                }
                return true;

                default:   return true;
        }
    }

    @Override
    public long getTimeToWait() {
        return timeTowait;
    }

    @Override
    public void setOffFire(Fire feux) {

        for (Light light: feux.getLightlist()) {

            light.setState(new OffState());
        }
    }

    @Override
    public void setTimeToWait(int timeToWait) {

        this.timeTowait = timeToWait;
    }

    @Override
    public void startFire(Fire feux) {

        setOffFire(feux);
        feux.getLightByColor(EnumColor.Rouge.getLightColor()).setState(new OnState());
        feux.setCurrentState(EnumState.GreenState);
    }

    @Override
    public List getLightsToOut() {

        List<Color> lightList= new ArrayList<>();
        lightList.add(EnumColor.Rouge.getLightColor());

        return lightList;
    }

    @Override
    public Color getLightToFlash() {
        return null;
    }

    @Override
    public void changeStateLight(Light light, I_stateLight newState) {

        if(light.getState().getClass()!= OutState.class){

            light.setState(newState);
        }
    }

    @Override
    public void actualizeFire(Fire feux) {

        onlyActive=true;
        changeLight(feux);
    }

    @Override
    public boolean isCarRunning() {
        return false;
    }

    @Override
    public void setCarRunning(boolean isCarRunning) {

    }
}
