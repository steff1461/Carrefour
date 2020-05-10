package com.sdz.model.fire;

import com.sdz.model.stateFire.EnumState;
import com.sdz.model.stateLight.OnState;
import com.sdz.model.light.Light;
import com.sdz.model.light.EnumColor;
import java.util.ArrayList;

public abstract class Fire{

    private ArrayList<Light> lightList=new ArrayList<>();
    private EnumState currentState=EnumState.RedState;

   public Fire(Light greenLight,Light redLight,Light orangeLight) {

       lightList.add(greenLight);
       lightList.add(redLight);
       lightList.add(orangeLight);
   }

   public Fire(Light greenLight,Light redLight){

       lightList.add(greenLight);
       lightList.add(redLight);
   }

   public Fire(ArrayList<Light> lightList){

       this.lightList=lightList;
   }

   public Light getLightByColor(Class lightToFind){

       Light tempLight=null;

       for (Light light:lightList) {

           if (light.getClass()==lightToFind){

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
