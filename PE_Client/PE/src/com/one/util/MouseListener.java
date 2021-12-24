package com.one.util;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//主要用来获取鼠标点击后显示正确答案以及正确率以及答题人数
public class MouseListener extends MouseAdapter {

    String right;
    String rightRate;
    int count;
    Box box;

    public MouseListener(String right,String rightRate,int count,Box box){
        this.right = right;
        this.rightRate = rightRate;
        this.count = count;
        this.box = box;
    }

    public void mouseClicked() {
        JLabel jLabel = new JLabel("正确答案："+right+"                                  "+"正确率："+rightRate+"  "+"答题人数："+count);
        box.add(jLabel);
        count = count + 1;

    }

}
