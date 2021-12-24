package com.sdkj.GUI;

import javax.swing.*;
import java.awt.*;

//自定义带背景的Jpanel
public class BackgrounPanel extends JPanel {

    private Image image;
    public BackgrounPanel(Image image){
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //绘制背景
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);

    }


}
