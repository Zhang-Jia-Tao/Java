package com.one.a;



import javax.swing.*;
import java.awt.*;

public class tx extends JFrame {
    public static void main(String[] args) {
        new tx();
    }

    public tx(){
        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setDividerLocation(600);
        jSplitPane.setDividerSize(20);

        JPanel jPanel = new JPanel();

        int y=0;
        for(int i=0;i<5;i++){
            JPanel jPanel1 = new JPanel();
            jPanel1.setBounds(0,y,600,200);
            JTextArea jTextArea = new JTextArea(4,25);
            JScrollPane jScrollPane = new JScrollPane(jTextArea);
            JButton jButton = new JButton("电");
            JButton jButton2 = new JButton("子");

            jPanel1.add(jScrollPane,BorderLayout.NORTH);
            JPanel jPanel2 = new JPanel(new FlowLayout());
            jPanel2.add(jButton);
            jPanel2.add(jButton2);
            jPanel1.add(jPanel2,BorderLayout.SOUTH);
            y = y+200;
            jPanel.add(jPanel1);
        }

        jSplitPane.setLeftComponent(jPanel);
        this.add(jSplitPane);
        this.setBounds(0,0,1640,1440);
        this.setVisible(true);



    }


}
