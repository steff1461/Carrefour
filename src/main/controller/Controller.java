package main.controller;

import main.model.fire.BicolorFire;
import main.model.fire.CustomFire;
import main.model.fire.TricolorFire;
import main.model.light.CustomLight;
import main.model.light.Light;
import main.model.strategy.BicolorStrategy;
import main.model.strategy.CustomStrategy;
import main.model.strategy.TricolorStrategy;
import main.view.UserInterface;
import java.awt.*;
import java.util.ArrayList;

public class Controller {

    private final FireReparator reparator = new FireReparator();
    private final UserInterface ui;
    private final StateController stateController = new StateController(reparator);
    private StateChanger stateChanger;
    CarController carController;

    public Controller(UserInterface ui) {


        this.ui = ui;
    }

    private CustomFire buildCustomFire(ArrayList<Light> lightsList) {

        ArrayList<Light> tempLightsList= new ArrayList<>();

        for (Light light: lightsList){

            CustomLight tempLight=(CustomLight)light;

            tempLightsList.add(tempLight.clone());
        }

        return  new CustomFire(tempLightsList);
    }

    public void startCustomFire(ArrayList<Light> lightsList, ArrayList<Color> listLightToOut) {

        carController = new CarController(ui.getPanCarrefour());
        stateChanger=new StateChanger(
                stateController,
                new CustomStrategy(listLightToOut,
                        lightsList.get(0).getColor()),
                new BicolorStrategy(),
                carController);

        stateChanger.addPhaseOne(buildCustomFire(lightsList), new DisplayManager(ui.getContainerFire1(), EnumFirePosition.Vertical));
        stateChanger.addPhaseOne(buildCustomFire(lightsList), new DisplayManager(ui.getContainerFire2(), EnumFirePosition.Vertical));

        stateChanger.addPhaseTwo(new BicolorFire(), new DisplayManager(ui.getContainerFire5(), EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(), new DisplayManager(ui.getContainerFire6(), EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(), new DisplayManager(ui.getContainerFire7(), EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(), new DisplayManager(ui.getContainerFire8(), EnumFirePosition.Horizontal));

        stateChanger.addPhaseThree(buildCustomFire(lightsList), new DisplayManager(ui.getContainerFire3(), EnumFirePosition.Horizontal));
        stateChanger.addPhaseThree(buildCustomFire(lightsList),new DisplayManager(ui.getContainerFire4(), EnumFirePosition.Horizontal));

        stateChanger.addPhaseFour(new BicolorFire(), new DisplayManager(ui.getContainerFire9(), EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(), new DisplayManager(ui.getContainerFire10(), EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(), new DisplayManager(ui.getContainerFire11(), EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(), new DisplayManager(ui.getContainerFire12(), EnumFirePosition.Vertical));

        stateChanger.start();
    }

    public void startRegularFire() {

        carController = new CarController(ui.getPanCarrefour());
        stateChanger = new StateChanger(
                stateController,
                new TricolorStrategy(),
                new BicolorStrategy(),
                carController);


        stateChanger.addPhaseOne(new TricolorFire(), new DisplayManager(ui.getContainerFire1(), EnumFirePosition.Vertical));
        stateChanger.addPhaseOne(new TricolorFire(), new DisplayManager(ui.getContainerFire2(), EnumFirePosition.Vertical));

        stateChanger.addPhaseTwo(new BicolorFire(), new DisplayManager(ui.getContainerFire5(), EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(), new DisplayManager(ui.getContainerFire6(), EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(), new DisplayManager(ui.getContainerFire7(), EnumFirePosition.Horizontal));
        stateChanger.addPhaseTwo(new BicolorFire(), new DisplayManager(ui.getContainerFire8(), EnumFirePosition.Horizontal));

        stateChanger.addPhaseThree(new TricolorFire(), new DisplayManager(ui.getContainerFire3(), EnumFirePosition.Horizontal));
        stateChanger.addPhaseThree(new TricolorFire(), new DisplayManager(ui.getContainerFire4(), EnumFirePosition.Horizontal));

        stateChanger.addPhaseFour(new BicolorFire(), new DisplayManager(ui.getContainerFire9(), EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(), new DisplayManager(ui.getContainerFire10(), EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(), new DisplayManager(ui.getContainerFire11(), EnumFirePosition.Vertical));
        stateChanger.addPhaseFour(new BicolorFire(), new DisplayManager(ui.getContainerFire12(), EnumFirePosition.Vertical));

        stateChanger.start();
    }

    public FireReparator getReparator() {
        return reparator;
    }

    public StateChanger getStateChanger() {
        return stateChanger;
    }

    public StateController getStateController() {
        return stateController;
    }

    public CarController getCarController() {
        return carController;
    }

}
