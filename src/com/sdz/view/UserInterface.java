package com.sdz.view;

import com.sdz.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {

    private final JPanel panPrincipal = (JPanel) this.getContentPane();
    private final JPanel containerFire1 =new JPanel();
    private final JPanel containerFire2 =new JPanel();
    private final JPanel containerFire3 =new JPanel();
    private final JPanel containerFire4 =new JPanel();
    private final JPanel containerFire5 =new JPanel();
    private final JPanel containerFire6 =new JPanel();
    private final JPanel containerFire7 =new JPanel();
    private final JPanel containerFire8 =new JPanel();
    private final JPanel containerFire9 =new JPanel();
    private final JPanel containerFire10 =new JPanel();
    private final JPanel containerFire11 =new JPanel();
    private final JPanel containerFire12 =new JPanel();
    private final JButton btnmanual= new JButton();
    private final JCheckBox isManual= new JCheckBox("Manuel");
    private final JButton btnRepare = new JButton("Rep");
    private final JPanel panControl= new JPanel();
    private final PanCarrefour panCarrefour=new PanCarrefour();
    private Controller controller;

    public UserInterface() {

        this.setVisible(true);
        this.initComponent();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponent() {

        this.setBounds(0, 0, 1050, 1000);
        panPrincipal.setLayout(new FlowLayout());

        panPrincipal.add(panControl);
        panPrincipal.add(panCarrefour);

        panCarrefour.add(containerFire1);
        panCarrefour.add(containerFire2);
        panCarrefour.add(containerFire3);
        panCarrefour.add(containerFire4);

        panCarrefour.add(containerFire5);
        panCarrefour.add(containerFire6);
        panCarrefour.add(containerFire7);
        panCarrefour.add(containerFire8);

        panCarrefour.add(containerFire9);
        panCarrefour.add(containerFire10);
        panCarrefour.add(containerFire11);
        panCarrefour.add(containerFire12);

        containerFire1.setLocation(550,550);
        containerFire2.setLocation(230,184);
        containerFire3.setLocation(184,550);
        containerFire4.setLocation(550,228);

        containerFire5.setLocation(550,730);
        containerFire6.setLocation(205,730);
        containerFire7.setLocation(549,50);
        containerFire8.setLocation(206,50);

        containerFire9.setLocation(70,549);
        containerFire10.setLocation(70,206);
        containerFire11.setLocation(730,548);
        containerFire12.setLocation(730,206);

        panControl.setPreferredSize(new Dimension(200,800));
        panControl.setLayout(new FlowLayout());
        panControl.setBorder(BorderFactory.createLineBorder(Color.RED));

        panControl.add(btnmanual);
        panControl.add(isManual);
        panControl.add(btnRepare);

        getBtnmanual().addActionListener(new btnManualListener());
        getIsManual().addActionListener(new isManualListener());
        getBtnRepare().addActionListener(new btnRepareListener());
    }

    class isManualListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (((JCheckBox) e.getSource()).isSelected()) {

                controller.getStateChanger().setManualChange(true);
            } else {

                controller.getStateChanger().setManualChange(false);

                if ( controller.getStateChanger().getState() == Thread.State.WAITING) {

                    controller.getStateChanger().restartThread();
                }
            }
        }
    }

    class btnManualListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if ( controller.getStateChanger().getState() == Thread.State.WAITING) {

                if ( controller.getStateChanger().istManualChange()) {

                    controller.getStateChanger().restartThread();
                }

            }
        }
    }

    class btnRepareListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            controller.getReparator().repareLight();
            controller.getStateController().setAnotherOut(false);

            if( controller.getStateChanger().getState()== Thread.State.WAITING) {

                controller.getStateChanger().restartThread();
                controller.getStateChanger().startAllFire();
            }

            else if( controller.getStateChanger().getState()== Thread.State.TIMED_WAITING){

                controller.getStateChanger().actualizeAllFire();
                controller.getStateChanger().notifyAllView();
            }

        }
    }

    public JPanel getContainerFire1() { return containerFire1; }

    public JPanel getContainerFire2() { return containerFire2; }

    public JPanel getContainerFire3() {
        return containerFire3;
    }

    public JPanel getContainerFire4() { return containerFire4; }

    public JPanel getContainerFire5() { return containerFire5; }

    public JPanel getContainerFire6() { return containerFire6; }

    public JButton getBtnmanual() { return btnmanual;}

    public JCheckBox getIsManual() { return isManual;}

    public JButton getBtnRepare() { return btnRepare;}

    public PanCarrefour getPanCarrefour() { return panCarrefour; }

    public JPanel getContainerFire7() { return containerFire7; }

    public JPanel getContainerFire8() { return containerFire8; }

    public JPanel getContainerFire9() { return containerFire9; }

    public JPanel getContainerFire10() { return containerFire10; }

    public JPanel getContainerFire11() { return containerFire11; }

    public JPanel getContainerFire12() { return containerFire12; }

    public void setController(Controller controller){this.controller =controller; }



}
