package com.sdz.view;

import com.sdz.controller.Controller;
import com.sdz.model.light.Light;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserInterface extends JFrame {

    private final JPanel panPrincipal = (JPanel) this.getContentPane();
    private final JPanel containerFire1 =new PanConteneur();
    private final JPanel containerFire2 =new PanConteneur();
    private final JPanel containerFire3 =new PanConteneur();
    private final JPanel containerFire4 = new PanConteneur();
    private final JPanel containerFire5 =new PanConteneur();
    private final JPanel containerFire6 =new PanConteneur();
    private final JPanel containerFire7 =new PanConteneur();
    private final JPanel containerFire8 =new PanConteneur();
    private final JPanel containerFire9 =new PanConteneur();
    private final JPanel containerFire10 =new PanConteneur();
    private final JPanel containerFire11 =new PanConteneur();
    private final JPanel containerFire12 =new PanConteneur();
    private final JButton btnmanual= new JButton();
    private final JCheckBox isManual= new JCheckBox("Manuel");
    private final JButton btnRepare = new JButton("Rep");
    private final JPanel panControl= new JPanel();
    private final JMenuBar menuBar= new JMenuBar();
    private final JMenu optMenu= new JMenu("Nouveau");
    private final JButton reset= new JButton("Reset");
    private final JButton leave= new JButton("Quitter");
    private final JMenuItem itemStand= new JMenuItem("Mode regular");
    private final JMenuItem itemCustom= new JMenuItem("Mode custom");
    private final PanCarrefour panCarrefour=new PanCarrefour();
    private final UserInterface ui = this;


    private Controller controller;

    public UserInterface() {

        initComponent();
    }

    private void initComponent() {

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(150, 0, 1050, 825);
        this.setResizable(false);
        this.setJMenuBar(menuBar);

        panPrincipal.setLayout(new FlowLayout());

        panPrincipal.add(panControl);
        panPrincipal.add(panCarrefour);

        menuBar.add(optMenu);
        menuBar.add(reset);
        menuBar.add(leave);

        optMenu.add(itemStand);
        optMenu.add(itemCustom);

        reset.setEnabled(false);
        reset.setContentAreaFilled(false);
        leave.setContentAreaFilled(false);
        reset.setBorderPainted(false);
        leave.setBorderPainted(false);


        reset.setActionCommand("RESET");
        leave.setActionCommand("LEAVE");
        itemStand.setActionCommand("REGULAR");
        itemCustom.setActionCommand("CUSTOM");

        leave.addActionListener(new MenuItemListener());
        reset.addActionListener(new MenuItemListener());
        itemStand.addActionListener(new MenuItemListener());
        itemCustom.addActionListener(new MenuItemListener());


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


        panControl.add(btnmanual);
        panControl.add(isManual);
        panControl.add(btnRepare);

        btnmanual.setEnabled(false);
        isManual.setEnabled(false);
        btnRepare.setEnabled(false);

        btnmanual.addActionListener(new BtnManualListener());
        isManual.addActionListener(new IsManualListener());
        btnRepare.addActionListener(new BtnRepareListener());
    }
    
    public JPanel getContainerFire1() { return containerFire1; }

    public JPanel getContainerFire2() { return containerFire2; }

    public JPanel getContainerFire3() {
        return containerFire3;
    }

    public JPanel getContainerFire4() { return containerFire4; }

    public JPanel getContainerFire5() { return containerFire5; }

    public JPanel getContainerFire6() { return containerFire6; }

    public PanCarrefour getPanCarrefour() { return panCarrefour; }

    public JPanel getContainerFire7() { return containerFire7; }

    public JPanel getContainerFire8() { return containerFire8; }

    public JPanel getContainerFire9() { return containerFire9; }

    public JPanel getContainerFire10() { return containerFire10; }

    public JPanel getContainerFire11() { return containerFire11; }

    public JPanel getContainerFire12() { return containerFire12; }

    public void setController(Controller controller){this.controller =controller; }

    public Controller getController(){ return controller; }


    public void launchCustom(ArrayList<Light> lightsList,ArrayList<Color> listLightTOOut){

        if (lightsList.size()>3){

            int cpt= lightsList.size()-3;

            getContainerFire2().setLocation(230,184-(22*cpt));
            getContainerFire3().setLocation(184-(22*cpt),550);
            this.repaint();
            this.revalidate();

        }

        int result= JOptionPane.showConfirmDialog(
                null,
                new PanelOpt(),
                "Créez votre feu",
                JOptionPane.YES_NO_OPTION);

        controller.startCustomFire(lightsList,listLightTOOut);
        btnmanual.setEnabled(true);
        btnRepare.setEnabled(true);
        isManual.setEnabled(true);
        optMenu.setEnabled(false);
        reset.setEnabled(true);

        if (result==0) {
            getController().getCarController().start();
        }
    }
    class MenuListener implements javax.swing.event.MenuListener{
        @Override
        public void menuSelected(MenuEvent e) {

            System.out.println("test");
             JMenu menu= (JMenu) e.getSource();
            int result;

          switch (menu.getActionCommand()){
//
//              case "RESET":
//
//                   result= JOptionPane.showConfirmDialog(null,"Voulez-vous recommencer?","Reset",JOptionPane.YES_NO_OPTION);
//
//                   if (result==0) {
//
//                      getController().getStateChanger().setToInterrupt(true);
//                      getController().getCarController().interruptCar();
//                      getController().getCarController().setToInterrupt(true);
//                      getContainerFire1().removeAll();
//                      getContainerFire2().removeAll();
//                      getContainerFire1().revalidate();
//                      getContainerFire2().revalidate();
//                      getContainerFire3().removeAll();
//                      getContainerFire4().removeAll();
//                      getContainerFire5().removeAll();
//                      getContainerFire6().removeAll();
//                      getContainerFire7().removeAll();
//                      getContainerFire8().removeAll();
//                      getContainerFire9().removeAll();
//                      getContainerFire10().removeAll();
//                      getContainerFire11().removeAll();
//                      getContainerFire12().removeAll();
//                      optMenu.setEnabled(true);
//                      reset.setEnabled(false);
//                      getPanCarrefour().updateUI();
//                  }
//                  break;
//
//              case "LEAVE":
//                  result= JOptionPane.showConfirmDialog(
//                          null,
//                          "Voulez-vous quitter?",
//                          "Quitter",
//                          JOptionPane.YES_NO_OPTION);
//
//                  if (result==0) {
//                      System.exit(1);
//                  }
//                  break;
          }

        }

        @Override
        public void menuDeselected(MenuEvent e) {

        }

        @Override
        public void menuCanceled(MenuEvent e) {

        }
    }

    class MenuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()){

                case "REGULAR":

                    int result= JOptionPane.showConfirmDialog(
                            null,
                            new PanelOpt(),
                            "Car option",
                            JOptionPane.YES_NO_OPTION);

                    getController().startRegularFire();
                    btnmanual.setEnabled(true);
                    btnRepare.setEnabled(true);
                    isManual.setEnabled(true);
                    reset.setEnabled(true);
                    optMenu.setEnabled(false);
                    if (result==0) {
                        getController().getCarController().start();
                    }

                    break;

                case "CUSTOM":

                    CreatorFrame crator= new CreatorFrame(ui);
                    break;


                case "RESET":

                    result= JOptionPane.showConfirmDialog(null,"Voulez-vous recommencer?","Reset",JOptionPane.YES_NO_OPTION);

                    if (result==0) {

                        getController().getStateChanger().setToInterrupt(true);
                        getController().getCarController().interruptCar();
                        getController().getCarController().setToInterrupt(true);
                        getContainerFire1().removeAll();
                        getContainerFire2().removeAll();
                        getContainerFire1().revalidate();
                        getContainerFire2().revalidate();
                        getContainerFire3().removeAll();
                        getContainerFire4().removeAll();
                        getContainerFire5().removeAll();
                        getContainerFire6().removeAll();
                        getContainerFire7().removeAll();
                        getContainerFire8().removeAll();
                        getContainerFire9().removeAll();
                        getContainerFire10().removeAll();
                        getContainerFire11().removeAll();
                        getContainerFire12().removeAll();
                        optMenu.setEnabled(true);
                        reset.setEnabled(false);
                        getPanCarrefour().updateUI();
                    }
                    break;

                case "LEAVE":
                    result= JOptionPane.showConfirmDialog(
                            null,
                            "Voulez-vous quitter?",
                            "Quitter",
                            JOptionPane.YES_NO_OPTION);

                    if (result==0) {
                        System.exit(1);
                    }
                    break;
            }
        }
    }

    class IsManualListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (((JCheckBox) e.getSource()).isSelected()) {

                getController().getStateChanger().setManualChange(true);
            } else {

                getController().getStateChanger().setManualChange(false);

                if ( getController().getStateChanger().getState() == Thread.State.WAITING) {

                    getController().getStateChanger().restartThread();
                }
            }
        }
    }

    class BtnManualListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if ( getController().getStateChanger().getState() == Thread.State.WAITING) {

                if ( getController().getStateChanger().istManualChange()) {

                    getController().getStateChanger().restartThread();
                }

            }
        }
    }

    class BtnRepareListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            getController().getReparator().repareLight();
            getController().getStateController().setAnotherOut(false);
            getController().getCarController().restartCarPhaseOne();
            getController().getCarController().restartCarPhaseThree();

            if( getController().getStateChanger().getState()== Thread.State.WAITING) {

                getController().getStateChanger().restartThread();
                getController().getStateChanger().startAllFire();
            }

            else if( getController().getStateChanger().getState()== Thread.State.TIMED_WAITING){

                getController().getStateChanger().actualizeAllFire();
                getController().getStateChanger().notifyAllView();
            }
        }
    }
}
