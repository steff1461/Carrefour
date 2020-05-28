package main.controller;

import main.model.light.Light;
import main.model.stateLight.OffState;
import main.model.stateLight.OutState;
import java.util.ArrayList;

public class FireReparator {

    private ArrayList<Light> listLight= new ArrayList<>();

    public FireReparator(){}

    public void addLight(Light light){

        if(!listLight.contains(light)){

        listLight.add(light);
        }
    }

    public void repareLight(){

        for (Light light: listLight) {

                if(light.getState().getClass()==OutState.class){

                    light.setState(new OffState());
                }
            }

        listLight=new ArrayList<>();
    }
}
