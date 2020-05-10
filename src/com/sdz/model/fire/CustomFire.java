package com.sdz.model.fire;

import com.sdz.model.light.CustomLight;
import com.sdz.model.light.EnumColor;
import com.sdz.model.light.Light;
import java.util.ArrayList;


public class CustomFire extends Fire {


    public CustomFire(ArrayList<Light> lightlist) {

        super(lightlist);
    }

    public CustomLight getLightByColor(EnumColor color){

        CustomLight tempLight=null;

        for(Light  light:this.getLightlist()){

            if (light.getColor()==color) tempLight=(CustomLight)light;

        }

        return tempLight;
    }


}
