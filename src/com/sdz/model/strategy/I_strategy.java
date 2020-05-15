package com.sdz.model.strategy;
import com.sdz.model.fire.Fire;
import com.sdz.model.light.EnumColor;
import com.sdz.model.light.Light;
import com.sdz.model.stateLight.I_stateLight;

import java.awt.*;
import java.util.List;


public interface I_strategy {

    boolean changeLight(Fire feux);
    long getTimeToWait();
    void setOffFire(Fire feux) ;
    void setTimeToWait(int timeToWait);
    void startFire(Fire feux);
    List<Color> getLightsToOut();
    Color getLightToFlash();
    void changeStateLight(Light light, I_stateLight newState);
    void actualizeFire(Fire feux);
    boolean isCarRunning();
    void setCarRunning(boolean isCarRunning);


}
