package main.model.strategy;

import main.model.fire.CustomFire;
import main.model.fire.Fire;
import main.model.light.CustomLight;
import main.model.light.Light;
import main.model.stateLight.I_stateLight;
import main.model.stateLight.OffState;
import main.model.stateLight.OnState;
import main.model.stateLight.OutState;
import java.awt.*;
import java.util.List;

public class CustomStrategy implements I_strategy{

    private long timeToWait=0;
    private boolean isOnlyActive=false;
    private boolean isCarRunning=false;
    private int cpt=0;
    private List<Color> listToOut;
    private Color lightToFlash;

    public CustomStrategy(List<Color> listToOut,Color lightToFlash){

        setListToOut(listToOut);
        setLightToFlash(lightToFlash);
    }

    @Override
    public boolean changeLight(Fire feux) {
        CustomFire fire= (CustomFire)feux;
        CustomLight nextLight;

        for (int i =0; i<fire.getLightlist().size();i++){

            CustomLight tempLight=(CustomLight)fire.getLightlist().get(i);

            if (tempLight.getState().getClass()==OnState.class) {

                setcpt(getCpt()+ 1);

                if (isOnlyActive) {

                    isOnlyActive = false;
                }

                else {

                    changeStateLight(tempLight, new OffState());
                    if (i >= fire.getLightlist().size() - 1) {

                        nextLight = (CustomLight) fire.getLightlist().get(0);
                    } else {

                        nextLight = (CustomLight) fire.getLightlist().get(i + 1);
                    }


                    switch (nextLight.getStateFire()) {

                        case GreenState:

                            setCarRunning(true);
                            setTimeToWait(6000);
                            break;

                        case RedState:

                            setCarRunning(false);
                            setTimeToWait(4000);
                            break;
                    }


                    changeStateLight(nextLight, new OnState());
                    break;
                }
            }
        }

        if (cpt==fire.getLightlist().size()*2) {

            setcpt(0);
            return  true;
        }

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

        setcpt(0);
        setOffFire(feux);
        feux.getLightlist().get(feux.getLightlist().size()-1).setState(new OnState());
    }

    @Override
    public List getLightsToOut() {


        return getListToOut();
    }

    @Override
    public Color getLightToFlash() {

        return lightToFlash;
    }

    @Override
    public void changeStateLight(
            Light light,
            I_stateLight newState) {

        if(light.getState().getClass()!= OutState.class){

            light.setState(newState);
        }
    }

    @Override
    public void actualizeFire(Fire feux) {

        isOnlyActive=true;
        setcpt(0);
        setOffFire(feux);
        startFire(feux);
    }


    @Override
    public boolean isCarRunning() { return  isCarRunning;
    }

    @Override
    public void setCarRunning(boolean isCarRunning) { this.isCarRunning=isCarRunning; }


    public List<Color> getListToOut() {
        return listToOut;
    }

    public void setListToOut(List<Color> listToOut) {
        this.listToOut = listToOut;
    }

    public void setLightToFlash(Color lightToFlash) {
        this.lightToFlash = lightToFlash;
    }

    public void setcpt(int cpt){ this.cpt=cpt;}

    public int getCpt(){ return cpt;}
}
