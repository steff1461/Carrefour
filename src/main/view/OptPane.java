package main.view;

import javax.swing.*;
import java.awt.*;

public class OptPane extends JPanel {

    private final JLabel lbl1= new JLabel("Voulez-vous ajouter des voitures au carrefour?");

    public OptPane(){

        initComponent();
    }

    private void initComponent(){

        this.setLayout(new FlowLayout());
        this.add(lbl1,new FlowLayout());
    }
}
