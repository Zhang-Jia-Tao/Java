package com.sdkj.AWT.Paint;

import java.awt.*;

public class paint extends Frame {

    public static void main(String[] args) {
        new paint().loadframe();
    }

    public void loadframe(){
        setBounds(100,100,1000,1000);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(100,100,100,100);
    }
}
