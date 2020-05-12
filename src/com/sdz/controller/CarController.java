package com.sdz.controller;

import com.sdz.view.Car;
import com.sdz.view.EnumCarPosition;
import com.sdz.view.PanCarrefour;
import java.util.ArrayList;

public class CarController implements Runnable{

    private final PanCarrefour panCarrefour;
    private final ArrayList<Car> listCarPhaseOne=new ArrayList<>();
    private final ArrayList<Car> listCarPhaseThree=new ArrayList<>();
    private EnumPhase currentPhase=EnumPhase.PhaseOne;
    private boolean isRunning = true;
    private final Thread t;

    public CarController(PanCarrefour panCarrefour) {

        this.panCarrefour = panCarrefour;

        t= new Thread(this);
        t.start();
    }

    @Override
    public  void run() {

        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }

        while(true){

           addCar();


            try { Thread.sleep(4500); }
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

        if (t.getState()== Thread.State.RUNNABLE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void restartThread(){

        setRunning(true);
        if (t.getState()== Thread.State.WAITING) {
            notify();
        }
    }

    public void restartCarPhaseOne(){
        for (Car car:listCarPhaseOne) {

            if(car.isRunning()){

                car.setPause(false);
                car.restartThread();
            }
        }
    }

    public void restartCarPhaseThree(){

        for (Car car:listCarPhaseThree) {

            if(car.isRunning()){

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

            System.out.println(listCarPhaseOne.size());
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

    public boolean isRunning() { return isRunning;}

    public void setRunning(boolean running) { isRunning = running;}
}
