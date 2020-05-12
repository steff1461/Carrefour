package com.sdz;

import com.sdz.controller.Controller;
import com.sdz.view.UserInterface;

public class Launcher {

    public static void main(String[] args) {

        UserInterface ui = new UserInterface();
        Controller ctrl = new Controller(ui);
        ui.setController(ctrl);

          ctrl.startRegularFire();
        //ctrl.startCustomFire();
    }
}
