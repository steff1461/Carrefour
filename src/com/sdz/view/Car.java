package com.sdz.view;

import javax.swing.*;
import java.awt.*;

public class Car extends JPanel implements Runnable{


    private int posX;
    private int posY;
    private EnumCarPosition carPosition;
    private boolean running = true;
    private boolean pause = false;
    private final Dimension carDimHorizontal= new Dimension(70,50);
    private final Dimension carDimVertical= new Dimension(50,70);
    private final Thread t;
    private boolean toInterrupt=false;

    public Car(EnumCarPosition carPosition){

        setCarPosition(carPosition);
        initComponent();
       t= new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while(!isToInterrupt()){

            switch (getCarPosition()){

                case Left:

                    setPosX(getPosX()+30);

                    if(getPosX()>800){

                    }

                    break;

                case Right:

                    setPosX(getPosX()-30);

                    if(getPosX()<-50){

                    }
                    break;

                case Up:

                    setPosY(getPosY()+30);

                    if(getPosY()>850){

                    }
                    break;

                case Down:

                    setPosY(getPosY()-30);

                    if(getPosY()<-50){

                    }
                    break;
            }

            setRunning(false);
            setLocation(getPosX(),getPosY());


            try { Thread.sleep(500); }
            catch (InterruptedException e) { e.printStackTrace(); }

            if (isPause()){pauseThread();}
        }
    }

    public void initComponent(){

        switch (carPosition){

            case Left:

                setPosX(20);
                setPosY(450);
               setSize(carDimHorizontal);
                break;

            case Right:

                setPosX(780);
                setPosY(300);
                setSize(carDimHorizontal);
                break;

            case Up:

                setPosX(300);
                setPosY(20);
                setSize(carDimVertical);
                break;

            case Down:

                setPosX(450);
                setPosY(780);
                setSize(carDimVertical);
                break;
        }

        setLocation(getPosX(),getPosY());
        setBackground(Color.blue);
    }

    public EnumCarPosition getCarPosition() { return carPosition; }

    public void setCarPosition(EnumCarPosition carPosition) { this.carPosition = carPosition; }

    public synchronized void pauseThread(){

        try { wait(); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    public synchronized void restartThread(){

        if(t.getState()== Thread.State.WAITING)  notify();
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setToInterrupt(boolean toInterrupt){this.toInterrupt=toInterrupt;}

    public  boolean isToInterrupt(){ return  toInterrupt;}
}
