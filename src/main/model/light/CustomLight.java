package main.model.light;

import main.model.stateFire.EnumState;

import java.awt.*;

public class CustomLight extends Light implements Cloneable {

    private  EnumState stateFire;

    public CustomLight(
            Color color,
            EnumState stateFire) {

        super(color);
    setStateFire(stateFire);
    }


    public EnumState getStateFire() { return stateFire;}

    public void setStateFire(EnumState stateFire) { this.stateFire = stateFire;}

    @Override
    public CustomLight clone() {
        CustomLight tempLight = null;

        try {
            tempLight = (CustomLight) super.clone();
        }
        catch (CloneNotSupportedException e) { e.printStackTrace(); }

        return new CustomLight(tempLight.getColor(),tempLight.getStateFire());
    }
}
