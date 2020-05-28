package main.model.fire;

import main.model.light.EnumColor;
import main.model.light.GreenLight;
import main.model.light.Light;
import main.model.light.RedLight;

public class BicolorFire extends Fire {

    public BicolorFire() {

        super(new GreenLight(),new RedLight());
    }

    public Light getGreenLight(){

        return getLightByColor(EnumColor.Vert.getLightColor());
    }

    public Light getRedLight(){

        return getLightByColor(EnumColor.Rouge.getLightColor());
    }
}
