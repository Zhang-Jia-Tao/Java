package com.one.UserTec;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ScreenUtils;
import org.apache.commons.beanutils.BeanMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ConfirmJDialog extends JDialog {

    Vector<Vector> param = null;
    String id = null;
    Map<String,Object> infor = null;
    JDialog jDialog = null;

    public ConfirmJDialog(JFrame jf, String title, boolean isModel, Vector<Vector> param){
        super(jf,title,isModel);


        this.param = param;
        this.jDialog = this;
        //发送方id
        this.id = param.elementAt(0).elementAt(1).toString();
        this.setBounds((ScreenUtils.getScreenWidth()-500)/2,(ScreenUtils.getScreenHeight()-400),500,400);

        this.setResizable(false);

        JPanel jPanel = new JPanel();
        jPanel.setBounds(0,0,500,400);
        jPanel.setBackground(new Color(0xB0E5E5));

        querys();


        Box vBox = Box.createVerticalBox();

        //组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        JTextField uField = new JTextField(15);
        uField.setText(infor.get("userName").toString());

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装年龄
        Box AgeBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("年    龄：");
        JTextField tField = new JTextField(3);
        tField.setText(infor.get("age").toString());

        AgeBox.add(tLabel);
        AgeBox.add(Box.createHorizontalStrut(20));
        AgeBox.add(tField);

        //组装性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别：");
        JRadioButton maleBtn = new JRadioButton("男",false);
        JRadioButton femaleBtn = new JRadioButton("女",false);

        if("男".equals(infor.get("sex"))){
            maleBtn.setSelected(true);
        } else {
            femaleBtn.setSelected(true);
        }

        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(femaleBtn);
        gBox.add(Box.createHorizontalStrut(120));




        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton friendBtn = new JButton("同意");
        JButton backBtn = new JButton("拒绝");

        friendBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                method();
            }
        });

        //拒绝安妞
        backBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                method(1);
            }
        });



        btnBox.add(friendBtn);
        btnBox.add(Box.createHorizontalStrut(80));
        btnBox.add(backBtn);


        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(gBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(AgeBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(btnBox);
        jPanel.add(vBox);

        this.add(jPanel);



    }


    //依据发送人的id，查询该人的信息
    public void querys(){
        String send_id = param.elementAt(0).elementAt(1).toString();
        Map<String,Object> pa = new HashMap<>();
        pa.put("send_id",send_id);
        GetUtils.getWithParams("http://localhost:8080/confirm/queryBySendId", pa, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    infor = (Map<String, Object>) info.getData();
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }


    //同意以及拒绝的方法
    public void method(){
        Map<String,Object> par = new HashMap<>();
        par.put("send_id",param.elementAt(0).elementAt(1).toString());
        par.put("flag","agree");
        GetUtils.getWithParams("http://localhost:8080/confirm/setstatus", par, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jDialog,info.getMessage());
                } else {
                    JOptionPane.showMessageDialog(jDialog,info.getMessage());
                }
                dispose();
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }

    public void method(int i){
        Map<String,Object> par = new HashMap<>();
        par.put("send_id",param.elementAt(0).elementAt(1).toString());
        par.put("flag","refuse");
        GetUtils.getWithParams("http://localhost:8080/confirm/setstatus", par, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jDialog,info.getMessage());
                } else {
                    JOptionPane.showMessageDialog(jDialog,info.getMessage());
                }
                dispose();
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }

}
