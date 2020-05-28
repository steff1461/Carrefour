package main.model.light;

import java.awt.*;

public enum EnumColor {

    Vert(Color.green),
    Rouge(Color.red),
    Orange(Color.orange),
    Bleu(Color.blue),
    Noir(Color.black),
    Cyan(Color.cyan),
    Magenta(Color.magenta),
    Rose(Color.pink),
    Jaune(Color.yellow),
    Gris(Color.darkGray);

    private final Color lightColor;

    EnumColor(Color lightColor){

        this.lightColor=lightColor;
    }

    public Color getLightColor(){

        return lightColor;
    }
}
