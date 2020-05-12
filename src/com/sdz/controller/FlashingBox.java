package com.sdz.controller;

import com.sdz.model.light.Light;
import com.sdz.model.stateLight.I_stateLight;
import com.sdz.model.stateLight.OffState;
import com.sdz.model.stateLight.OnState;
import com.sdz.model.stateLight.OutState;
import com.sdz.model.strategy.I_strategy;

import java.util.ArrayList;

public class FlashingBox extends Thread {

    private final ArrayList<Light> flashingLights= new ArrayList<>();
    private boolean flashing=false;
    private final ArrayList<DisplayManager> phaseOne;
    private final ArrayList<DisplayManager> phaseTwo;

    private final StateController stateController;
    private final I_strategy strategy;
    private final CarController carController;

    public FlashingBox(
            ArrayList<DisplayManager> phaseOne,
            ArrayList<DisplayManager> phaseTwo,
            StateController stateController,
            I_strategy strategy,
            CarController carController){

        this.stateController =stateController;
        this.carController=carController;
        this.phaseOne=phaseOne;
        this.phaseTwo=phaseTwo;
        this.strategy=strategy;
        this.start();
    }

    @Override
    public void run() {

        try {
            pauseThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true){

                setLightState(new OffState());
                notifyView(phaseOne);
                notifyView(phaseTwo);
                carController.pauseCarPhaseOne();
                carController.pauseCarPhaseThree();
                carController.setRunning(false);

            if (!isFlashing()){

                try { pauseThread(); }
                catch (InterruptedException e) {e.printStackTrace();
                }
            }

            try {sleep(500);}

            catch (InterruptedException e) {e.printStackTrace();}
            stateController.checkIfOut(strategy.getLightsToOut());

            setLightState(new OnState());

            notifyView(phaseOne);
            notifyView(phaseTwo);

            try {sleep(500);}

            catch (InterruptedException e) {e.printStackTrace();}

            stateController.checkIfOut(strategy.getLightsToOut());
        }

    }



    public void addLight(Light light){

        flashingLights.add(light);
    }

    public synchronized void startFlash(){

        setFlashing(true);
        this.notify();
    }

    public void stopFlash(){

        setFlashing(false);
    }

    public synchronized void pauseThread() throws InterruptedException {

        this.wait();
    }

    public  void notifyView(ArrayList<DisplayManager> displayManagers){

        for (DisplayManager displayManager: displayManagers) {

            displayManager.notifyView();
        }
    }

    public boolean isFlashing() {
        return flashing;
    }

    public void setFlashing(boolean flashing){

        this.flashing=flashing;
    }

    public void setLightState(I_stateLight stateLight){

        for (Light light: flashingLights) {
            if(light.getState().getClass()!= OutState.class){

            light.setState(stateLight);
            }
        }
    }
}
