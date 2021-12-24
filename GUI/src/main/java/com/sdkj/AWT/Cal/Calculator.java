package com.sdkj.AWT.Cal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
        new MyFrame();
    }
}


//界面
class MyFrame extends Frame{
    public MyFrame(){
        //三个文本框
        TextField textField01 = new TextField(10); //限制输入字符的长度
        TextField textField02 = new TextField(10);
        TextField textField03 = new TextField(10);

        //一个按钮
        Button btn = new Button("=");

        //为button设置监听事件
        btn.addActionListener(new MyListener(textField01,textField02,textField03));

        //一个标签
        Label label = new Label("+");

        //设置布局      为流式布局
        setLayout(new FlowLayout());

        add(textField01);
        add(label);
        add(textField02);
        add(btn);
        add(textField03);

        setVisible(true);
        pack();

    }
}


//监听事件   监听按钮的事件
class MyListener implements ActionListener {

    public TextField  num1,num2,num3;
    public MyListener(TextField num1,TextField num2,TextField num3){
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String num_1 = num1.getText();
        String num_2 = num2.getText();
        int res = Integer.parseInt(num_1) + Integer.parseInt(num_2);
        num3.setText(""+res);
    }
}
