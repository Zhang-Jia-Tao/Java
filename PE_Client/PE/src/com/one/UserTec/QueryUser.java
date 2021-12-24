package com.one.UserTec;


import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ScreenUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

//在贴吧区中，显示用户信息
public class QueryUser extends JDialog {

    final int WIDTH = 500;
    final int HEIGHT = 400;

    public QueryUser(JFrame jf, String title, boolean isModel, Map<String,Object> user, DefaultMutableTreeNode node,JTree jTree){
        super(jf,title,isModel);
        //设置窗口的属性
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        this.setResizable(false);

        JPanel jPanel = new JPanel();
        jPanel.setBounds(0,0,500,400);
        jPanel.setBackground(new Color(0xB0E5E5));


        Box vBox = Box.createVerticalBox();

            //组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        JTextField uField = new JTextField(15);
        uField.setText(user.get("userName").toString());

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

            //组装年龄
        Box AgeBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("年    龄：");
        JTextField tField = new JTextField(3);
        tField.setText(user.get("age").toString());

        AgeBox.add(tLabel);
        AgeBox.add(Box.createHorizontalStrut(20));
        AgeBox.add(tField);

            //组装性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别：");
        JRadioButton maleBtn = new JRadioButton("男",false);
        JRadioButton femaleBtn = new JRadioButton("女",false);

        if("男".equals(user.get("sex"))){
            maleBtn.setSelected(true);
        } else {
            femaleBtn.setSelected(true);
        }

        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(femaleBtn);
        gBox.add(Box.createHorizontalStrut(120));



            //考研方向
        Box DirBox = Box.createHorizontalBox();
        JLabel Dirlabel = new JLabel("考研方向");
        JTextField DirField = new JTextField(15);
        DirField.setText(user.get("pe_Dir").toString());

        DirBox.add(Dirlabel);
        DirBox.add(Box.createHorizontalStrut(20));
        DirBox.add(DirField);

            //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton friendBtn = new JButton("添加好友");
        JButton backBtn = new JButton("返回登录页面");


        friendBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加好友的操作逻辑
                Map<String,Object> param = new HashMap<>();
                param.put("name",user.get("userName").toString());
                param.put("phone",user.get("phone").toString());

                GetUtils.getWithParams("http://localhost:8080/friend/insert", param, new SuccessListener() {
                    @Override
                    public Object success(String result) {
                        ResultInfo info = JsonUtils.parseResult(result);
                        if(info.isFlag()){
                            JOptionPane.showMessageDialog(jf,info.getMessage());

                            //  -----回调嘛
                            new Display_Friend(node,jTree,null);
                        } else {
                            JOptionPane.showMessageDialog(jf,"添加失败");
                        }
                        return null;
                    }

                }, new FailListener() {
                    @Override
                    public void fail() {

                    }
                });
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
            vBox.add(DirBox);
            vBox.add(Box.createVerticalStrut(20));
            vBox.add(btnBox);
            jPanel.add(vBox);

            this.add(jPanel);

    }

}

