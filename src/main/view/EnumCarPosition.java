package main.view;

public enum EnumCarPosition {

    Left("Gauche"),
    Right("Droite"),
    Up("Haut"),
    Down("Bas");

    private final String position;

    EnumCarPosition(String position){

        this.position=position;
    }
}
