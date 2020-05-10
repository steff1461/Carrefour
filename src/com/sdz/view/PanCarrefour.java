package com.sdz.view;

import javax.swing.*;
import java.awt.*;

public class PanCarrefour extends JPanel {

    private Graphics g;

    public PanCarrefour( ){

        initComponent();
    }

    public void paintComponent(Graphics g){

        this.g=g;

        g.setColor(Color.gray);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        drawDottedLineV(g);
        drawDottedLineH(g);
        drawCorner1(g);
        drawCorner2(g);
        drawCorner3(g);
        drawCorner4(g);
    }

    public void drawDottedLineV(Graphics g){

        for (int i = 0 ; i<this.getHeight();i=i+40){

            if(i<250||i>550) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(10));
                g.setColor(Color.white);
                g.drawLine(400, i, 400, i + 10);
            }
        }
    }

    public void drawDottedLineH(Graphics g){

        for (int i = 0 ; i<this.getWidth();i=i+40){

            if(i<250||i>550) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(10));
                g.setColor(Color.white);
                g.drawLine(i, 400, i + 10, 400);
            }
        }
    }

    public void drawCorner1(Graphics g){

        g.setColor(Color.getHSBColor(10,0,15));
        g.fillRect(0,0,250,250);
        g.setColor(Color.green);
        g.fillRect(0,0,200,200);
    }

    public void drawCorner2(Graphics g){

        g.setColor(Color.getHSBColor(10,0,15));
        g.fillRect(0,550,250,250);
        g.setColor(Color.green);
        g.fillRect(0,600,200,200);
    }

    public void drawCorner3(Graphics g){

        g.setColor(Color.getHSBColor(10,0,15));
        g.fillRect(550,0,250,250);
        g.setColor(Color.green);
        g.fillRect(600,0,200,200);
    }

    public void drawCorner4(Graphics g){

        g.setColor(Color.getHSBColor(10,0,15));
        g.fillRect(550,550,250,250);
        g.setColor(Color.green);
        g.fillRect(600,600,200,200);
    }

    public void initComponent(){

        this.setLayout(null);
        this.setPreferredSize(new Dimension(800,800));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void display(){

        paintComponent(g);
    }
}
