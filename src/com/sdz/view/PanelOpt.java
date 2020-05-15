package com.sdz.view;

import javax.swing.*;
import java.awt.*;

public class PanelOpt extends JPanel {

    private final JLabel lbl1= new JLabel("Voulez-vous ajouter des voitures au carrefour?");

    public PanelOpt(){

        initComponent();
    }

    private void initComponent(){

        this.setLayout(new FlowLayout());
        this.add(lbl1,new FlowLayout());
    }
}
