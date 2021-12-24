package com.one.online;


import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_exam;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class exam_false extends JSplitPane {

    Vector<Vector> vectors = null;
    String type = null;
    JScrollPane jScrollPane = null;
    JPanel MainPanel = null;

    public exam_false(String type){
        super(JSplitPane.VERTICAL_SPLIT);
        //设立一个布局
        //上面设置一有开始考试按钮 结束考试按钮 暂停考试按钮  一个计时器
        //设置一行备注：

        this.setDividerLocation(70);
        this.setDividerSize(10);

        this.type = type;

        Box box = Box.createHorizontalBox();
        JButton start = new JButton("开始考试");
        JButton finish = new JButton("结束考试");
        JButton pause = new JButton("暂停考试");


        box.add(start);
        box.add(Box.createVerticalStrut(20));
        box.add(finish);
        box.add(Box.createVerticalStrut(20));
        box.add(pause);


        //添加的监听事件
        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long laterTime = System.currentTimeMillis() + 120*60*1000;
                Date date = new Date(laterTime);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String later_time = "";
                later_time = df.format(laterTime);
                Counter counter = new Counter();
                counter.timer(later_time);

                //向服务器发送请求

                //请求59个选择题，并且保存在vectors中
                request_exam();

                //循环创建每个试题的单独面板
                exam_panel();

            }
        });


        MainPanel = new JPanel();
        jScrollPane = new JScrollPane(MainPanel);


        this.setLeftComponent(box);
        this.setRightComponent(jScrollPane);
        this.setVisible(true);


    }



    //向服务器请求数据---试题
    public void request_exam(){
        Map<String,Object> param = new HashMap<>();
        param.put("type",type);
        GetUtils.getWithParams("http://localhost:8080/exam/get", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    vectors = ResultInfoData2Vector_exam.convertResultInfoData2Vector(info);
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }


    public void exam_panel(){
        //循环添加面板
        int y=0;
        for(int i=0;i<vectors.size();i++){
            JPanel jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.setBounds(0,y,850,200);

            JTextArea jTextArea = new JTextArea();  //题干
            jTextArea.setText((i)+". "+vectors.elementAt(i).elementAt(2).toString());
            jTextArea.setBounds(0,y,850,50);

            JRadioButton A = new JRadioButton("A."+vectors.elementAt(i).elementAt(3).toString());
            JRadioButton B = new JRadioButton("B."+vectors.elementAt(i).elementAt(4).toString());
            JRadioButton C = new JRadioButton("C."+vectors.elementAt(i).elementAt(5).toString());
            JRadioButton D = new JRadioButton("D."+vectors.elementAt(i).elementAt(6).toString());
            //实现单选的效果
            ButtonGroup bg = new ButtonGroup();
            bg.add(A);
            bg.add(B);
            bg.add(C);
            bg.add(D);
            A.setBounds(20,y+50,850,30);
            B.setBounds(20,y+80,850,30);
            C.setBounds(20,y+110,850,30);
            D.setBounds(20,y+140,850,30);

            jPanel.add(jTextArea);
            jPanel.add(A);
            jPanel.add(B);
            jPanel.add(C);
            jPanel.add(D);

            MainPanel.add(jPanel);
            y += 140;
        }
    }
}
