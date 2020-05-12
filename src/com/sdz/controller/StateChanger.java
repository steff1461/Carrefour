package com.sdz.controller;

import com.sdz.model.fire.Fire;
import com.sdz.model.strategy.I_strategy;
import java.util.ArrayList;

public class StateChanger extends Thread {

    private final ArrayList<Fire> phaseOne= new ArrayList<>();
    private final ArrayList<Fire> phaseThree = new ArrayList<>();
    private final ArrayList<Fire> phaseTwo = new ArrayList<>();
    private final ArrayList<Fire> phaseFour= new ArrayList<>();

    private final ArrayList< DisplayManager> displayPhaseOne=new ArrayList<>();
    private final ArrayList<DisplayManager> displayPhaseThree = new ArrayList<>();
    private final ArrayList<DisplayManager> displayPhaseTwo = new ArrayList<>();
    private final ArrayList<DisplayManager> displayPhaseFour = new ArrayList<>();

    private final StateController stateController;
    private  I_strategy carStrategy;
    private  I_strategy pedestrianStrategy;
    private final FlashingBox flashingBox;
    private boolean manualChange=false;
    private boolean isChangePhase =false;
    private EnumPhase currentPhase= EnumPhase.PhaseOne;
    private I_strategy currentStratgy;
    private final CarController carController;

    public StateChanger(
            StateController stateController,
            I_strategy carStrategy,
            I_strategy pedestrianStrategy,
            CarController carController) {

        this.stateController =stateController;

        setPedestrianStrategy(pedestrianStrategy);
        setCarStrategy(carStrategy);
        this.carController =carController;

        flashingBox= new FlashingBox(
                displayPhaseOne,
                displayPhaseThree,
                this.stateController,
                getCarStrategy()
                ,carController);
    }

    public synchronized void run() {

        startAllFire();
        notifyAllView();

        while (true) {

            switch (getCurrentPhase()) {

                case PhaseOne:

                    setCurrentStratgy(getCarStrategy());
                    setIsChangePhase(changeLight(phaseOne, getCurrentStratgy()));
                    notifyView(displayPhaseOne);

                    if(!carStrategy.isCarRunning()){

                        carController.pauseCarPhaseOne();
                        carController.setRunning(false);
                    }

                    else {

                        carController.restartCarPhaseOne();
                        carController.restartThread();
                    }

                    if (isChangePhase()) {

                        setCurrentPhase(EnumPhase.PhaseTwo);
                        carController.setCurrentPhase(EnumPhase.PhaseTwo);
                    }

                    try { activeWaiting(getCurrentStratgy()); }
                    catch (InterruptedException e) { e.printStackTrace();}

                    break;


                case PhaseTwo:

                    setCurrentStratgy(getPedestrianStrategy());
                    setIsChangePhase(changeLight(phaseTwo, getCurrentStratgy()));
                    notifyView(displayPhaseTwo);

                    try { activeWaiting(getCurrentStratgy()); }
                    catch (InterruptedException e) { e.printStackTrace(); }

                    if (isChangePhase()) {

                        if (isManualChange()) {
                            try { pauseThread(); }
                            catch (InterruptedException e) { e.printStackTrace(); }
                        }

                        setCurrentPhase(EnumPhase.PhaseThree);
                        carController.setCurrentPhase(EnumPhase.PhaseThree);
                        carController.restartCarPhaseThree();
                    }

                    break;

                case PhaseThree:

                    setCurrentStratgy(getCarStrategy());
                    setIsChangePhase(changeLight(phaseThree, getCurrentStratgy()));
                    notifyView(displayPhaseThree);

                    if(!carStrategy.isCarRunning()){

                    carController.pauseCarPhaseThree();
                    carController.setRunning(false);
                }

                    else {

                        carController.restartCarPhaseThree();
                        carController.restartThread();
                }
                    if (isChangePhase()) {

                        setCurrentPhase(EnumPhase.PhaseFour);
                        carController.setCurrentPhase(EnumPhase.PhaseFour);
                    }

                    try { activeWaiting(getCurrentStratgy()); }
                    catch (InterruptedException e) { e.printStackTrace(); }

                    break;

                case PhaseFour:

                    setCurrentStratgy(getPedestrianStrategy());
                    setIsChangePhase(changeLight(phaseFour, getCurrentStratgy()));
                    notifyView(displayPhaseFour);

                    try { activeWaiting(getCurrentStratgy()); }
                    catch (InterruptedException e) { e.printStackTrace(); }

                    if (isChangePhase()) {

                        if (isManualChange()) {

                            try { pauseThread(); }
                            catch (InterruptedException e) { e.printStackTrace(); }
                        }

                        setCurrentPhase(EnumPhase.PhaseOne);
                        carController.setCurrentPhase(EnumPhase.PhaseOne);
                        carController.restartCarPhaseOne();
                    }
                    break;
            }
        }
    }

    public void activeWaiting(I_strategy strategy) throws InterruptedException {

        long timeToWait=strategy.getTimeToWait();
        for(int i =  0; i<(timeToWait/1000)*2;i++ ){

           if( stateController.checkIfOut(strategy.getLightsToOut())){

               setOffFire(phaseOne, carStrategy);
               setOffFire(phaseThree, carStrategy);

               notifyAllView();

               flashingBox.startFlash();
               pauseThread();
           }

           else if(stateController.isAnotherOut()){

               notifyAllView();
               stateController.setAnotherOut(false);
           }

           sleep(500);
        }
    }

    public boolean changeLight(ArrayList<Fire> phase,I_strategy strategy){

        boolean isPhaseOn=true;

        for (Fire feux: phase) {

          isPhaseOn=strategy.changeLight(feux);
        }

        return isPhaseOn;
    }

    public void startFire(ArrayList<Fire> phase,I_strategy strategy){

        for (Fire feux:phase) {

            strategy.startFire(feux);
        }
    }

    public void startAllFire(){

        startFire(phaseOne, carStrategy);
        startFire(phaseThree, carStrategy);
        startFire(phaseTwo, pedestrianStrategy);
        startFire(phaseFour, pedestrianStrategy);
    }

    public void actualizeAllFire(){

        for (Fire feux:phaseOne) {

            carStrategy.actualizeFire(feux);
        }

        for (Fire feux: phaseThree) {

            carStrategy.actualizeFire(feux);
        }

        for (Fire feux: phaseTwo) {

            pedestrianStrategy.actualizeFire(feux);
        }

        for (Fire feux: phaseFour) {

            pedestrianStrategy.actualizeFire(feux);
        }
    }

    private void setOffFire(ArrayList<Fire> phase,I_strategy strategy){

        for (Fire feux: phase) {

            strategy.setOffFire(feux);
        }
    }

    public void notifyAllView(){

        notifyView(displayPhaseOne);
        notifyView(displayPhaseTwo);
        notifyView(displayPhaseThree);
        notifyView(displayPhaseFour);
    }

    private void notifyView(ArrayList<DisplayManager> displayManagers){

        for (DisplayManager displayManager:displayManagers) {

            displayManager.notifyView();
        }
    }

    public void pauseThread() throws InterruptedException {

        this.wait();
    }

    public synchronized void restartThread()  {

            if (flashingBox.getState()==State.TIMED_WAITING) {

                flashingBox.stopFlash();

                notifyView(displayPhaseOne);
                notifyView(displayPhaseThree);
            }

            this.notify();
        }


    public void addPhaseOne(Fire feux,DisplayManager displayManager){

        phaseOne.add(feux);
        displayManager.addListeners(feux);
        displayPhaseOne.add(displayManager);
        stateController.addFire(feux);
        flashingBox.addLight(feux.getLightByColor(carStrategy.getLightToFlash()));
    }

    public void addPhaseThree(Fire feux, DisplayManager displayManager){

        phaseThree.add(feux);
        displayManager.addListeners(feux);
        displayPhaseThree.add(displayManager);
        stateController.addFire(feux);
        flashingBox.addLight(feux.getLightByColor(carStrategy.getLightToFlash()));

    }

    public void addPhaseTwo(Fire feux, DisplayManager displayManager){

        phaseTwo.add(feux);
        displayManager.addListeners(feux);
        displayPhaseTwo.add(displayManager);
        stateController.addFire(feux);

    }

    public void addPhaseFour(Fire feux, DisplayManager displayManager){

        phaseFour.add(feux);
        displayManager.addListeners(feux);
        displayPhaseFour.add(displayManager);
        stateController.addFire(feux);
    }

    private boolean isManualChange() {
        return manualChange;
    }

    public void setManualChange(boolean manualChange) {
        this.manualChange = manualChange;
    }

    public boolean istManualChange( ) { return manualChange;}

    public EnumPhase getCurrentPhase() { return currentPhase; }

    public void setCurrentPhase(EnumPhase currentPhase) { this.currentPhase = currentPhase; }

    public I_strategy getCarStrategy() { return carStrategy; }

    public void setCarStrategy(I_strategy carStrategy) { this.carStrategy = carStrategy; }

    public I_strategy getPedestrianStrategy() { return pedestrianStrategy; }

    public void setPedestrianStrategy(I_strategy pedestrianStrategy) { this.pedestrianStrategy = pedestrianStrategy; }

    public I_strategy getCurrentStratgy() { return currentStratgy; }

    public void setCurrentStratgy(I_strategy currentStratgy) { this.currentStratgy = currentStratgy; }

    private boolean isChangePhase() { return isChangePhase; }

    public void setIsChangePhase(boolean changePhase) { this.isChangePhase = changePhase; }
}






