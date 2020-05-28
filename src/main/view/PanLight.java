package main.view;

import main.model.stateLight.I_stateLight;
import main.model.stateLight.OffState;
import main.model.stateLight.OnState;
import javax.swing.*;
import java.awt.*;

public class PanLight extends JPanel  {

    private Color lightColor;
    private  I_stateLight stateLight;

    public PanLight(
            Color lightColor,
            I_stateLight stateLight){

        this.setLightColor(lightColor);
        this.setStateLight(stateLight);
        this.setPreferredSize(new Dimension(15,15));
    }

    public void paintComponent(Graphics g) {

        g.setColor(getLightColor());

        if (getStateLight().getClass() == OffState.class) {

            int alpha= 105;
            Color color= new Color(getLightColor().getRed(),getLightColor().getGreen(),getLightColor().getBlue(),alpha);
            g.setColor(color);
            g.fillOval(0, 0, getWidth(), getHeight());
        }

        else if(this.getStateLight().getClass()== OnState.class) {

            g.fillOval(0,0,getWidth(), getHeight());
        }

        else {

            this.setLightColor(Color.black);
            g.setColor(getLightColor());
            g.fillOval(0,0,getWidth(), getHeight());
        }
    }


    public Color getLightColor() {
        return lightColor;
    }

    public void setLightColor(Color lightColor) {
        this.lightColor = lightColor;
    }

    public I_stateLight getStateLight() {
        return stateLight;
    }

    public void setStateLight(I_stateLight stateLight) {
        this.stateLight = stateLight;
    }

}
