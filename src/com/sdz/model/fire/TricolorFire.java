package com.sdz.model.fire;

import com.sdz.model.light.GreenLight;
import com.sdz.model.light.Light;
import com.sdz.model.light.OrangeLight;
import com.sdz.model.light.RedLight;

public class TricolorFire extends Fire {

    public TricolorFire(){

        super(new GreenLight(),new RedLight(),new OrangeLight());
    }

    public Light getRedLight(){

        return getLightByColor(RedLight.class);
    }

    public Light getOrangeLight(){

        return getLightByColor(OrangeLight.class);
    }

    public Light getGreenLight(){

        return getLightByColor(GreenLight.class);
    }

}
