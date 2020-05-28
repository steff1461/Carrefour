package main.view;

import main.model.light.CustomLight;
import main.model.light.Light;
import main.model.stateFire.EnumState;
import main.model.stateLight.OnState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreatorFrame extends JFrame implements ActionListener {

    private final JButton plusBtn= new JButton("+");
    private final JButton minBtn= new JButton("-");
    private final JButton validate= new JButton("Valider");
    private PanFire fire;
    private final ArrayList<Color> colorsList = new ArrayList<>();
    private final ArrayList<Light> lightsList = new ArrayList<>();
    private final JPanel panState= new JPanel();
    private UserInterface ui;
    private final ArrayList<Color> listLightToOut= new ArrayList<>();
    private boolean isFirst=true;
    private final String title ="Composez votre propre feu.";
    private final String instructionsA ="Cliquez sur +";
    private final String instructionsB ="pour ajouter une lampe";
    private final JLabel lblTitle= new JLabel( title);
    private final JLabel lblInstructA= new JLabel(instructionsA);
    private final JLabel lblInstructB= new JLabel(instructionsB);

    public CreatorFrame(UserInterface ui){

        setUi(ui);
        initComponent();
    }

    private void initComponent(){

        this.setBounds(300,150,190,320);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        panState.setLayout(new Wraplayout());

        this.add(lblTitle);
        this.add(lblInstructA);
        this.add(lblInstructB);
        this.add(plusBtn);
        this.add(minBtn);
        this.add(panState);

        validate.addActionListener(this);
        plusBtn.addActionListener(this);
        minBtn.addActionListener(this);

        validate.setActionCommand("VALIDATE");
        plusBtn.setActionCommand("PLUS");
        minBtn.setActionCommand("MINUS");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       switch (e.getActionCommand()){

           case "PLUS":

               if(isFirst()){

                   fire=new PanFire();
                   this.add(fire);
                   this.add(validate);
                   setFirst(false);
               }
               Color color= JColorChooser.showDialog(this,"Color Picker",Color.green);
               PanLight panlight=new PanLight(color,new OnState());
               fire.add(panlight);
               colorsList.add(color);
               JComboBox<EnumState> combo= new JComboBox<>();
               combo.setPreferredSize(new Dimension(90,17));

               combo.addItem(EnumState.GreenState);
               combo.addItem(EnumState.RedState);
               panState.add(combo);
               this.repaint();
               this.revalidate();
               break;

           case "MINUS":

               if(fire.getComponents().length>0) {
                   fire.remove(fire.getComponents().length - 1);
                   colorsList.remove(colorsList.size()-1);
                   panState.remove(panState.getComponents().length-1);
                   this.repaint();
                   this.revalidate();
               }
               break;

           case "VALIDATE":

               for (int i = 0; i< colorsList.size(); i++){

                   Color tempColor= colorsList.get(i);
                   JComboBox<EnumState> comb= (JComboBox<EnumState>) panState.getComponent(i);
                   EnumState state= (EnumState) comb.getSelectedItem();
                   CustomLight light= new CustomLight(tempColor,state);
                   lightsList.add(light);
                   if (state==EnumState.RedState){

                       listLightToOut.add(tempColor);
                   }
               }

               getUi().launchCustom(lightsList,listLightToOut);
               this.dispose();
               break;
       }
    }

    public UserInterface getUi() {
        return ui;
    }

    public void setUi(UserInterface ui) {
        this.ui = ui;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }
}
