package main.model.fire;

import main.model.stateFire.EnumState;
import main.model.light.Light;

import java.awt.*;
import java.util.ArrayList;

public abstract class Fire{

    private ArrayList<Light> lightList=new ArrayList<>();
    private EnumState currentState=EnumState.RedState;

   public Fire(Light greenLight,
               Light redLight,
               Light orangeLight) {

       lightList.add(greenLight);
       lightList.add(redLight);
       lightList.add(orangeLight);
   }

   public Fire(Light greenLight,
               Light redLight){

       lightList.add(greenLight);
       lightList.add(redLight);
   }

   public Fire(ArrayList<Light> lightList){

       this.lightList=lightList;
   }

   public Light getLightByColor(Color lightToFind){

       Light tempLight=null;

       for (Light light:lightList) {

           if (light.getColor()==lightToFind){

               tempLight=light;
           }
       }

       return tempLight;
   }

    public ArrayList<Light> getLightlist() {

       return lightList;
    }

    public EnumState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(EnumState currentState) {
        this.currentState = currentState;
    }

}
