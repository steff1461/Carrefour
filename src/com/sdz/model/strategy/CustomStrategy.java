package com.sdz.model.strategy;

import com.sdz.model.fire.CustomFire;
import com.sdz.model.fire.Fire;
import com.sdz.model.light.CustomLight;
import com.sdz.model.light.Light;
import com.sdz.model.stateLight.I_stateLight;
import com.sdz.model.stateLight.OffState;
import com.sdz.model.stateLight.OnState;
import com.sdz.model.stateLight.OutState;

public class CustomStrategy implements I_strategy{

    private long timeToWait=0;
    private boolean isOnlyActive=false;
    private boolean isCarRunning=false;
    private int cpt=0;

    public CustomStrategy(){}

    @Override
    public boolean changeLight(Fire feux) {
        CustomFire fire= (CustomFire)feux;
        CustomLight nextLight=null;

        for (int i =0; i<fire.getLightlist().size();i++){

            CustomLight tempLight=(CustomLight)fire.getLightlist().get(i);

            if (tempLight.getState().getClass()==OnState.class) {

                cpt++;
                System.out.println(cpt);
                changeStateLight(tempLight,new OffState());

                if (isOnlyActive){

                    isOnlyActive=false;
                }
                switch(tempLight.getStateFire()){

                    case GreenState:

                        setCarRunning(true);
                        setTimeToWait(6000);
                        break;

                    case RedState:

                        setCarRunning(false);
                        setTimeToWait(4000);

                        break;

                }

                if (i>=fire.getLightlist().size()-1){

                    nextLight=(CustomLight) fire.getLightlist().get(0);
                }

                else {

                     nextLight= (CustomLight) fire.getLightlist().get(i+1);
                }
                changeStateLight(nextLight,new OnState());
                break;
            }
        }
        assert nextLight != null;

        if (cpt==fire.getLightlist().size()*2) {

            cpt=0;
            return  true;}

      else   return false;
    }

    @Override
    public long getTimeToWait() {
        return  timeToWait;
    }

    @Override
    public void setOffFire(Fire feux) {

        for (Light light: feux.getLightlist()){

            changeStateLight(light,new OffState());
        }
    }

    @Override
    public void setTimeToWait(int timeToWait) {

        this.timeToWait=timeToWait;
    }

    @Override
    public void startFire(Fire feux) {

        setOffFire(feux);
        feux.getLightlist().get(feux.getLightlist().size()-1).setState(new OnState());
    }

    @Override
    public Class getLightToOut() {
        return null;
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

        isOnlyActive=true;
        changeLight(feux);
    }


    @Override
    public boolean isCarRunning() { return  isCarRunning;
    }

    @Override
    public void setCarRunning(boolean isCarRunning) { this.isCarRunning=isCarRunning; }
}
