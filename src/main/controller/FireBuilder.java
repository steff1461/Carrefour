package main.controller;

import main.model.fire.Fire;
import main.model.light.Light;
import main.view.PanFire;
import main.view.PanLight;
import javax.swing.*;

public class FireBuilder {

public FireBuilder( ){ }

public  JPanel buildFire(
        Fire feux,
        EnumFirePosition firePosition){

    PanFire panFire=new PanFire();
    int fireSize=0;

    for (Light light:feux.getLightlist()) {

        PanLight panLight= new PanLight(light.getColor(),light.getState());

        panLight.addMouseListener(new OutLightListener(light));
        panFire.add(panLight);
        fireSize+=22;
    }

    switch (firePosition){

        case Horizontal:
            panFire.setSize(fireSize,22);
            break;

        case Vertical:
            panFire.setSize(20,fireSize);
            break;
    }

    return panFire;
}
}
