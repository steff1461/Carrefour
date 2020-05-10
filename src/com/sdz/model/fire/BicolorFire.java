package com.sdz.model.fire;

import com.sdz.model.light.GreenLight;
import com.sdz.model.light.Light;
import com.sdz.model.light.RedLight;

public class BicolorFire extends Fire {

    public BicolorFire() {

        super(new GreenLight(),new RedLight());
    }

    public Light getGreenLight(){

        return getLightByColor(GreenLight.class);
    }

    public Light getRedLight(){

        return getLightByColor(RedLight.class);
    }
}
