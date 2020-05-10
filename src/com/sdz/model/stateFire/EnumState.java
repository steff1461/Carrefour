package com.sdz.model.stateFire;

public enum EnumState {

    GreenState("GreenState"),
    RedState("RedState"),
    OrangeState("OrangeState"),
    OutState("OutState");

    private final String currentState;

    EnumState(String currentState){

        this.currentState=currentState;
    }


    public String getCurrentState(){

        return currentState;
    }
}
