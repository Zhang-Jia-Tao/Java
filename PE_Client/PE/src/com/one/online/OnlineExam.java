package com.one.online;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.MouseListener;
import com.one.util.ResultInfoData2Vector_exam;
import com.one.util.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OnlineExam extends JScrollPane {

    private Map<String,Object> param = new HashMap<>();
    private Vector<Vector> vectors = null;
    Box box = null;
    JScrollPane jScrollPane = null;
    Counter counter = null;

    public OnlineExam(String type){

        super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setBackground(new Color(0xB3F1F1));
        this.jScrollPane = this;
        //试题类型
        param.put("type",type);


        //设计要显示的页面

        box = Box.createVerticalBox();

        //添加三个按钮

        JPanel panel_up = new JPanel();
        panel_up.setLayout(new WrapLayout());
        panel_up.setBounds(0,0,850,100);
        panel_up.setBackground(new Color(0xE3EEEE));
        JButton start = new JButton("开始考试");
        JButton finish = new JButton("结束考试");
        JButton pause = new JButton("暂停考试");
        start.setBounds(100,25,100,50);
        finish.setBounds(300,25,100,50);
        pause.setBounds(500,25,100,50);
        panel_up.add(start);
        panel_up.add(finish);
        panel_up.add(pause);

        box.add(panel_up);


        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long laterTime = System.currentTimeMillis() + 120*60*1000;
                Date date = new Date(laterTime);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String later_time = "";
                later_time = df.format(laterTime);
                counter = new Counter();
                counter.timer(later_time);

                //向服务器发送请求

                //请求50个选择题，并且保存在vectors中
                requestData();

                //循环创建每个试题的单独面板
                AddPanel();

                /*
                * 在这里遇到了一个问题就是关于JScrollPane加载不了 再开始点击考试后无法显示出考试内容，
                *   是因为运行程序的时候，第一次不会加载按钮监听事件中的内容，所以需要在点击开始考试后
                *   在监听事件中添加jScrollPane.setViewportView(box)
                * */
                jScrollPane.setViewportView(box);
            }
        });

        //暂停考试监听
        pause.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter.stopTimer();
            }
        });

        //结束考试
        finish.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        this.setViewportView(box);
    }


    //请求数据
    public void requestData(){
        GetUtils.getWithParams("http://localhost:8080/exam/get",param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                vectors = ResultInfoData2Vector_exam.convertResultInfoData2Vector(resultInfo);
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {
            }
        });
    }

    public void AddPanel(){
        for(int i=0;i<vectors.size();i++){
            JLabel jLabel = new JLabel((i)+". "+vectors.elementAt(i).elementAt(2).toString()+"",SwingConstants.LEFT);  //题干
            jLabel.setBounds(0,0,850,30);

            Box btnBox = Box.createVerticalBox();
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
            btnBox.add(A);
            btnBox.add(B);
            btnBox.add(C);
            btnBox.add(D);


            box.add(jLabel);
            box.add(btnBox);



            //new MouseListener(right,rightRate,count,box).mouseClicked();    //这里有bug

        }
    }
}
