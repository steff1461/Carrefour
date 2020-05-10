package com.sdz.model.strategy;

import com.sdz.model.fire.BicolorFire;
import com.sdz.model.fire.Fire;
import com.sdz.model.light.Light;
import com.sdz.model.light.RedLight;
import com.sdz.model.stateFire.EnumState;
import com.sdz.model.stateLight.I_stateLight;
import com.sdz.model.stateLight.OffState;
import com.sdz.model.stateLight.OnState;
import com.sdz.model.stateLight.OutState;

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
                    setTimeToWait(5000);
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
        feux.getLightByColor(RedLight.class).setState(new OnState());
        feux.setCurrentState(EnumState.GreenState);
    }

    @Override
    public Class getLightToOut() {

        return RedLight.class;
    }

    @Override
    public Class getLightToFlash() {
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
