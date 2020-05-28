package main.model.strategy;

import main.model.fire.Fire;
import main.model.light.EnumColor;
import main.model.stateFire.EnumState;
import main.model.stateLight.I_stateLight;
import main.model.stateLight.OffState;
import main.model.stateLight.OnState;
import main.model.fire.TricolorFire;
import main.model.light.Light;
import main.model.stateLight.OutState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TricolorStrategy implements I_strategy {

    private long timeToWait=0;
    private boolean isOnlyActive = false;
    private boolean isCarRunning=false;

    public TricolorStrategy() {}

    @Override
    public  boolean changeLight(Fire tricolorefeux) {

        TricolorFire feux= (TricolorFire) tricolorefeux;

        switch (feux.getCurrentState()) {

            case GreenState:

                setCarRunning(true);

                if(isOnlyActive){

                    isOnlyActive =false;
                    feux.setCurrentState(EnumState.RedState);
                    changeLight(feux);
                }

                else {

                    setOffFire(feux);
                    changeStateLight(feux.getGreenLight(), new OnState());
                    setTimeToWait(5000);
                    feux.setCurrentState(EnumState.OrangeState);
                }
                return false;

            case OrangeState:

                if(isOnlyActive){

                    isOnlyActive =false;
                    feux.setCurrentState(EnumState.GreenState);
                    changeLight(feux);
                }

                else {

                    setOffFire(feux);
                    changeStateLight(feux.getOrangeLight(), new OnState());
                    setTimeToWait(3000);
                    feux.setCurrentState(EnumState.RedState);
                }
                return false;

            case RedState:

                setCarRunning(false);
                if(isOnlyActive){

                    isOnlyActive =false;
                    feux.setCurrentState(EnumState.OrangeState);
                    changeLight(feux);
                }

                else {

                    setOffFire(feux);
                    changeStateLight(feux.getRedLight(), new OnState());
                    setTimeToWait(5000);
                    feux.setCurrentState(EnumState.GreenState);
                }
                return true;

                default:  return true;
        }
    }

    @Override
    public void actualizeFire(Fire feux){

        isOnlyActive =true;
        changeLight(feux);
    }

    @Override
    public void changeStateLight(Light light, I_stateLight newState){

        if(light.getState().getClass()!=OutState.class){

        light.setState(newState);
        }
    }

    @Override
    public void setOffFire(Fire feux) {

        for (Light light: feux.getLightlist()) {

            changeStateLight(light,new OffState());
            }
        }

    @Override
    public long getTimeToWait() { return timeToWait; }

    @Override
    public void setTimeToWait(int timeToWait) { this.timeToWait = timeToWait; }


    @Override
    public void startFire(Fire feux){

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
    public Color getLightToFlash()
    {

        return EnumColor.Orange.getLightColor();
    }

    @Override
    public boolean isCarRunning() {
        return isCarRunning;
    }

    @Override
    public void setCarRunning(boolean carRunning) {
        isCarRunning = carRunning;
    }
}
