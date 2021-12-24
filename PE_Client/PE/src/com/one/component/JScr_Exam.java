package com.one.component;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.MouseListener;
import com.one.util.ResultInfoData2Vector;
import com.one.util.ResultInfoData2Vector_exam;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class JScr_Exam extends JScrollPane {

    private Map<String,Object> param = new HashMap<>();
    private Vector<Vector> vectors = null;

    public JScr_Exam(String type){

        super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setBackground(new Color(0xB3F1F1));

        //试题类型
        param.put("type",type);

        this.requestData();


        //设计要显示的页面



        Box box = Box.createVerticalBox();


        for(int i=0;i<vectors.size();i++){
            JLabel jLabel = new JLabel((i+1)+". "+vectors.elementAt(i).elementAt(2).toString()+"",SwingConstants.LEFT);  //题干
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


            String right = vectors.elementAt(i).elementAt(7).toString();
            String rightRate = vectors.elementAt(i).elementAt(8).toString();
            int count = Integer.parseInt( vectors.elementAt(i).elementAt(9).toString());
            new MouseListener(right,rightRate,count,box).mouseClicked();    //这里有bug

        }


        this.setViewportView(box);
    }


    //请求数据

    public void requestData(){
        GetUtils.getWithParams("http://localhost:8080/exam/query",param, new SuccessListener() {
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
}
