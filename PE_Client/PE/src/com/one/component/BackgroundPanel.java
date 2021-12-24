package com.one.component;


import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    Image backIcon;

    public BackgroundPanel(Image backIcon){
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}
