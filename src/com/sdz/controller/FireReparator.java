package com.sdz.controller;

import com.sdz.model.light.Light;
import com.sdz.model.stateLight.OffState;
import com.sdz.model.stateLight.OutState;
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
