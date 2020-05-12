package com.sdz.model.strategy;

import com.sdz.model.fire.Fire;
import com.sdz.model.light.EnumColor;
import com.sdz.model.light.OrangeLight;
import com.sdz.model.light.RedLight;
import com.sdz.model.stateFire.EnumState;
import com.sdz.model.stateLight.I_stateLight;
import com.sdz.model.stateLight.OffState;
import com.sdz.model.stateLight.OnState;
import com.sdz.model.fire.TricolorFire;
import com.sdz.model.light.Light;
import com.sdz.model.stateLight.OutState;

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
        feux.getLightByColor(EnumColor.Rouge).setState(new OnState());
        feux.setCurrentState(EnumState.GreenState);
    }

    @Override
    public List getLightsToOut() {

        List<EnumColor> lightList= new ArrayList<>();
        lightList.add(EnumColor.Rouge);

        return lightList;
    }

    @Override
    public EnumColor getLightToFlash()
    {

        return EnumColor.Orange;
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
