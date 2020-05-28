package main;

import main.controller.Controller;
import main.view.UserInterface;

public class Launcher {

    public static void main(String[] args) {

        UserInterface ui = new UserInterface();
        Controller ctrl = new Controller(ui);
        ui.setController(ctrl);
    }
}
