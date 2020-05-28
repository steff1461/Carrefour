package main.controller;

import main.view.Car;
import main.view.EnumCarPosition;
import main.view.PanCarrefour;
import java.util.ArrayList;

public class CarController extends Thread {

    private final PanCarrefour panCarrefour;
    private final ArrayList<Car> listCarPhaseOne=new ArrayList<>();
    private final ArrayList<Car> listCarPhaseThree=new ArrayList<>();
    private EnumPhase currentPhase=EnumPhase.PhaseOne;
    private boolean isRunning = false;
    private boolean toInterrupt=false;

    public CarController(PanCarrefour panCarrefour) {

        this.panCarrefour = panCarrefour;
    }

    @Override
    public  void run() {

        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }

        while(!toInterrupt){

            if(isRunning()) addCar();

            try { Thread.sleep(3000); }
            catch (InterruptedException e) { e.printStackTrace(); }

            if (!isRunning()){
                pauseThread();
            }
        }
    }

    public void addCar() {

        switch (getCurrentPhase()){

            case PhaseOne:
                Car carTwo = new Car(EnumCarPosition.Down);
                Car carOne = new Car(EnumCarPosition.Up);
                listCarPhaseOne.add(carOne);
                listCarPhaseOne.add(carTwo);

                panCarrefour.add(carTwo);
                panCarrefour.add(carOne);
                break;

            case PhaseThree:
                Car carThree = new Car(EnumCarPosition.Right);
                Car carFour = new Car(EnumCarPosition.Left);

                listCarPhaseThree.add(carThree);
                listCarPhaseThree.add(carFour);

                panCarrefour.add(carThree);
                panCarrefour.add(carFour);
                break;

            default:
                break;
        }
    }

    public synchronized void pauseThread(){

        if (this.getState()== Thread.State.RUNNABLE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void restartThread(){

        setRunning(true);
        if (this.getState()== Thread.State.WAITING) {
            notify();
        }
    }

    public void restartCarPhaseOne(){
        for (Car car:listCarPhaseOne) {

            if(!car.isRunning()){

                car.setPause(false);
                car.restartThread();
            }
        }
    }

    public void restartCar(){

        switch(getCurrentPhase()){

            case PhaseOne:
                restartCarPhaseOne();
                break;

            case PhaseThree:
                restartCarPhaseThree();
                break;
        }
    }

    public void restartCarPhaseThree(){

        for (Car car:listCarPhaseThree) {

            if(!car.isRunning()){

                car.setPause(false);
                car.restartThread();
            }
        }
    }
    public void pauseCarPhaseThree(){


        for (Car car: listCarPhaseThree) {

        switch (car.getCarPosition()) {
            case Right:

                if (car.getPosX() >= 600) {

                    car.setPause(true);
                }
                break;

            case Left:

                if (car.getPosX() <= 200) {

                    car.setPause(true);
                }
                break;
        }

        }
    }

    public void pauseCarPhaseOne(){

        for (Car car:listCarPhaseOne) {

            switch (car.getCarPosition()){

                case Up:
                    if(car.getPosY()<=200){

                        car.setPause(true);
                    }
                    break;

                case Down:

                    if(car.getPosY()>=600){

                        car.setPause(true);
                    }
                    break;
            }
        }
    }

    public void setCurrentPhase(EnumPhase currentPhase){

        this.currentPhase=currentPhase;
    }

    public EnumPhase getCurrentPhase(){

        return currentPhase;
    }

    public void interruptCar(){

        for(Car car: listCarPhaseOne){

            car.setVisible(false);
            car.setToInterrupt(true);
        }

        for(Car car: listCarPhaseThree){

            car.setVisible(false);
            car.setToInterrupt(true);
        }
    }

    public boolean isRunning() { return isRunning;}

    public void setRunning(boolean running) { isRunning = running;}

    public boolean isToInterrupt() {
        return toInterrupt;
    }

    public void setToInterrupt(boolean toInterrupt) {
        this.toInterrupt = toInterrupt;
    }
}
