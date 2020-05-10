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

    public Car(EnumCarPosition carPosition){

        setCarPosition(carPosition);
        initComponent();
        Thread t= new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while(isRunning()){

            switch (getCarPosition()){

                case Left:

                 //   posX+=50;
                    setPosX(getPosX()+50);

                    if(getPosX()>800){

                        setRunning(false);
                    }

                    break;

                case Right:

                  //  posX-=50;
                    setPosX(getPosX()-50);

                    if(getPosX()<-50){

                        setRunning(false);
                    }
                    break;

                case Up:

                   // posY+=50;
                    setPosY(getPosY()+50);

                    if(getPosY()>850){

                        setRunning(false);
                    }
                    break;

                case Down:

                    //posY-=50;
                    setPosY(getPosY()-50);

                    if(getPosY()<-50){

                        setRunning(false);
                    }
                    break;
            }

            setLocation(getPosX(),getPosY());

            try { Thread.sleep(500); }
            catch (InterruptedException e) { e.printStackTrace(); }

            if (isPause()){pauseThread();}
        }
    }

    public void initComponent(){

        switch (carPosition){

            case Left:

                setPosX(-50);
                setPosY(450);
               setSize(carDimHorizontal);
                break;

            case Right:

                setPosX(850);
                setPosY(300);
                setSize(carDimHorizontal);
                break;

            case Up:

                setPosX(300);
                setPosY(-50);
                setSize(carDimVertical);
                break;

            case Down:

                setPosX(450);
                setPosY(850);
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

        notify();
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
}
