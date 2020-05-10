package com.sdz.controller;

public enum EnumPhase {

    PhaseOne ("Phase un"),
    PhaseTwo("Phase deux"),
    PhaseThree("Phase trois"),
    PhaseFour("Phase quatre");

    private final String currentPhase;

    EnumPhase(String currentPhase){

        this.currentPhase=currentPhase;
    }
}
