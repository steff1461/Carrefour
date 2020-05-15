package com.sdz.view;

import com.sdz.model.light.EnumColor;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustColorChoo extends AbstractColorChooserPanel {
    @Override
    public void updateChooser() {

    }

    @Override
    protected void buildChooser() {

        setLayout(new FlowLayout());

        for(EnumColor enumColor: EnumColor.values()) {

            makeAddButton(enumColor.name(), enumColor.getLightColor());

        }


    /*
    Vert(Color.green),
    Rouge(Color.red),
    Orange(Color.orange),
    Bleu(Color.blue),
    Noir(Color.black),
    Cyan(Color.cyan),
    Magenta(Color.magenta),
    Rose(Color.pink),
    Jaune(Color.yellow),
    Gris(Color.darkGray);*/
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return null;
    }

    @Override
    public Icon getLargeDisplayIcon() {
        return null;
    }

    private void makeAddButton(String name,Color color){

        JButton button= new JButton(name);
        button.setBackground(color);
        button.setAction(setColorAction);
        this.add(button);

    }

    Action setColorAction= new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton button=(JButton) e.getSource();
            getColorSelectionModel().setSelectedColor(button.getBackground());
        }
    };
}
