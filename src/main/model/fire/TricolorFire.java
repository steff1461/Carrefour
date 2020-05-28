package main.model.fire;

import main.model.light.*;

public class TricolorFire extends Fire {

    public TricolorFire(){

        super(new GreenLight(),new RedLight(),new OrangeLight());
    }

    public Light getRedLight(){

        return getLightByColor(EnumColor.Rouge.getLightColor());
    }

    public Light getOrangeLight(){

        return getLightByColor(EnumColor.Orange.getLightColor());
    }

    public Light getGreenLight(){

        return getLightByColor(EnumColor.Vert.getLightColor());
    }

}
