package com.sdz.model.light;

import com.sdz.model.stateFire.EnumState;

public class CustomLight extends Light {

    private  EnumState stateFire;

    public CustomLight(
            EnumColor color,
            EnumState stateFire) {

        super(color);
        this.stateFire=stateFire;
    }


    public EnumState getStateFire() { return stateFire;}

    public void setStateFire(EnumState stateFire) { this.stateFire = stateFire;}
}
