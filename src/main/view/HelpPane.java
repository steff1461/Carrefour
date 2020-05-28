package main.view;

import javax.swing.*;

public class HelpPane extends JPanel {

    private final JTextArea txt1= new JTextArea("Cliquez sur une ou plusieurs lampes pour les mettre hors service");
    private final JTextArea txt2= new JTextArea("Les lampes rouges ou assimilées mettent les feux en stand-by");
    private final JTextArea txt3= new JTextArea("Cliquez sur 'Réparer' pour réparer les lampes");
    private final JTextArea txt4= new JTextArea("Cochez 'Manuel' pour mettre les feux en changement de phase manuel");
    private final JTextArea txt5= new JTextArea("Quand Manuel est coché, cliquez sur 'Suivant' pour changer de phase");
    private final JTextArea txt6= new JTextArea("Cochez manuel pour mettre les feux en changement de phase manuel");

    public HelpPane(){

        initComponent();
    }

    private void initComponent(){

        this.setLayout(new Wraplayout());
        this.setBounds(150,150,100,200);
        this.add(txt1);
        this.add(txt2);
        this.add(txt3);
        this.add(txt4);
        this.add(txt5);

    }
}
