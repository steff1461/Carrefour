package com.sdz.controller;

import com.sdz.model.fire.BicolorFire;
import com.sdz.model.fire.CustomFire;
import com.sdz.model.fire.TricolorFire;
import com.sdz.model.light.CustomLight;
import com.sdz.model.light.EnumColor;
import com.sdz.model.light.Light;
import com.sdz.model.stateFire.EnumState;
import com.sdz.model.strategy.BicolorStrategy;
import com.sdz.model.strategy.CustomStrategy;
import com.sdz.model.strategy.TricolorStrategy;
import com.sdz.view.UserInterface;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final FireReparator reparator = new FireReparator();
    private final UserInterface ui;
    private final StateController stateController = new StateController(reparator);
    private  final StateChanger stateChanger;
    CarController carController;

    public Controller(UserInterface ui) {

        List<EnumColor> lightList= new ArrayList<>();
        lightList.add(EnumColor.Gris);
        lightList.add(EnumColor.Rouge);

        carController = new CarController(ui.getPanCarrefour());
        stateChanger= new StateChanger(
                stateController,
                new TricolorStrategy(),
                //new CustomStrategy(lightList,EnumColor.Bleu),
                new BicolorStrategy(),
                carController);

        this.ui = ui;

    }

    private final ArrayList<Light> lightList= new ArrayList<>();

    private final ArrayList<Light> lightList2= new ArrayList<>();
    private final ArrayList<Light> lightList3= new ArrayList<>();
    private final ArrayList<Light> lightList4= new ArrayList<>();

    private final CustomLight cl1= new CustomLight(EnumColor.Bleu, EnumState.GreenState);
    private final CustomLight cl2= new CustomLight(EnumColor.Gris,EnumState.RedState);
    private final CustomLight cl3= new CustomLight(EnumColor.Jaune,EnumState.GreenState);
    private final CustomLight cl4= new CustomLight(EnumColor.Rouge,EnumState.RedState);

    private final CustomLight cl5= new CustomLight(EnumColor.Bleu, EnumState.GreenState);
    private final CustomLight cl6= new CustomLight(EnumColor.Gris,EnumState.RedState);
    private final CustomLight cl7= new CustomLight(EnumColor.Jaune,EnumState.GreenState);
    private final CustomLight cl8= new CustomLight(EnumColor.Rouge,EnumState.RedState);

    private final CustomLight cl9= new CustomLight(EnumColor.Bleu, EnumState.GreenState);
    private final CustomLight cl10= new CustomLight(EnumColor.Gris,EnumState.RedState);
    private final CustomLight cl11= new CustomLight(EnumColor.Jaune,EnumState.GreenState);
    private final CustomLight cl12= new CustomLight(EnumColor.Rouge,EnumState.RedState);

    private final CustomLight cl13= new CustomLight(EnumColor.Bleu, EnumState.GreenState);
    private final CustomLight cl14= new CustomLight(EnumColor.Gris,EnumState.RedState);
    private final CustomLight cl15= new CustomLight(EnumColor.Jaune,EnumState.GreenState);
    private final CustomLight cl16= new CustomLight(EnumColor.Rouge,EnumState.RedState);



    public void startCustomFire(){

        lightList.add(cl1);
        lightList.add(cl2);
        lightList.add(cl3);
        lightList.add(cl4);

        lightList2.add(cl5);
        lightList2.add(cl6);
        lightList2.add(cl7);
        lightList2.add(cl8);

        lightList3.add(cl9);
        lightList3.add(cl10);
        lightList3.add(cl11);
        lightList3.add(cl12);

        lightList4.add(cl13);
        lightList4.add(cl14);
        lightList4.add(cl15);
        lightList4.add(cl16);


        stateChanger.addPhaseOne(
                new CustomFire(lightList),
                new DisplayManager(ui.getContainerFire1(),
                        EnumFirePosition.Vertical));

        stateChanger.addPhaseOne(
                new CustomFire(lightList2),
                new DisplayManager(ui.getContainerFire2(),
                        EnumFirePosition.Vertical));

        stateChanger.addPhaseTwo(
                new BicolorFire(),
                new DisplayManager(ui.getContainerFire5(),
                        EnumFirePosition.Horizontal));

        stateChanger.addPhaseThree(
                new CustomFire(lightList3),
                new DisplayManager(ui.getContainerFire3(),
                        EnumFirePosition.Horizontal));

        stateChanger.addPhaseThree(
                new CustomFire(lightList4),
                new DisplayManager(ui.getContainerFire4(),
                        EnumFirePosition.Horizontal));

        stateChanger.addPhaseFour(
                new BicolorFire(),
                new DisplayManager(ui.getContainerFire9(),
                        EnumFirePosition.Vertical));

        stateChanger.start();
    }

    public void startRegularFire(){

        stateChanger.addPhaseOne(new TricolorFire(),new DisplayManager(ui.getContainerFire1(),EnumFirePosition.Vertical));
        stateChanger.addPhaseOne(new TricolorFire(),new DisplayManager(ui.getContainerFire2(),EnumFirePosition.Vertical));

        stateChanger.addPhaseTwo(new BicolorFire(),new DisplayManager(ui.getContainerFire5(),EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(),new DisplayManager(ui.getContainerFire6(),EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(),new DisplayManager(ui.getContainerFire7(),EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(),new DisplayManager(ui.getContainerFire8(),EnumFirePosition.Horizontal));

        stateChanger.addPhaseThree(new TricolorFire(),new DisplayManager(ui.getContainerFire3(),EnumFirePosition.Horizontal));
        stateChanger.addPhaseThree(new TricolorFire(),new DisplayManager(ui.getContainerFire4(),EnumFirePosition.Horizontal));

        stateChanger.addPhaseFour(new BicolorFire(),new DisplayManager(ui.getContainerFire9(),EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(),new DisplayManager(ui.getContainerFire10(),EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(),new DisplayManager(ui.getContainerFire11(),EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(),new DisplayManager(ui.getContainerFire12(),EnumFirePosition.Vertical));

        stateChanger.start();
    }

    public FireReparator getReparator() { return reparator; }
    public StateChanger getStateChanger() { return stateChanger; }
    public StateController getStateController(){return stateController;}
}
