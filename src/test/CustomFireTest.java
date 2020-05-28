package test;

import main.model.fire.CustomFire;
import main.model.light.CustomLight;
import main.model.light.EnumColor;
import main.model.light.Light;
import main.model.stateFire.EnumState;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomFireTest {

    private final CustomLight lightOne=   new CustomLight(EnumColor.Vert.getLightColor(), EnumState.RedState);
    private final CustomLight lightTwo= new CustomLight(EnumColor.Bleu.getLightColor(), EnumState.GreenState);
    private final CustomLight lightThree= new CustomLight(EnumColor.Jaune.getLightColor(), EnumState.RedState);
    private final CustomLight lightFour= new CustomLight(EnumColor.Magenta.getLightColor(), EnumState.GreenState);

    private final ArrayList<Light> lightslist=new ArrayList<>(
            List.of(lightOne,lightTwo,lightThree,lightFour));

    private final CustomFire fireTest= new CustomFire(lightslist);

    @Test
    void testGetLightByColor_R1() {

        Light result= fireTest.getLightByColor(Color.green);
        assertEquals(lightOne,result);
    }

 @Test
    void testGetLightByColor_R2() {

        Light result= fireTest.getLightByColor(Color.BLUE);
        assertEquals(lightTwo,result);
    }

 @Test
    void testGetLightByColor_R3() {

        Light result= fireTest.getLightByColor(Color.MAGENTA);
        assertEquals(lightFour,result);
    }

    @Test
    void getLightlist() {

        ArrayList<Light> result= fireTest.getLightlist();
        assertEquals(result,lightslist);
    }

    @Test
    void getCurrentState() {

        EnumState result= EnumState.RedState;
        assertEquals(result,fireTest.getCurrentState());
    }

    @Test
    void setCurrentState() {

        EnumState result= EnumState.GreenState;
        fireTest.setCurrentState(EnumState.GreenState);
        assertEquals(result,fireTest.getCurrentState());
    }
}