package com.one.GUI;


import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;

import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class RegisterInterface {
    JFrame jf = new JFrame("注册");

    final int WIDTH = 500;
    final int HEIGHT = 400;


    //组装视图
    public void init() throws Exception {
        //设置窗口的属性
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        //jf.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("logo.png"))));

        //BackgroundPanel jPanel = new BackgroundPanel(ImageIO.read(new File(PathUtils.getRealPath("1.jpg"))));
        JPanel jPanel = new JPanel();
        jPanel.setBounds(0,0,500,400);
        jPanel.setBackground(new Color(0xB0E5E5));


        Box vBox = Box.createVerticalBox();

        //组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        JTextField uField = new JTextField(15);

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码：");
        JTextField pField = new JTextField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装手机号
        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLabel = new JLabel("手机号码：");
        JTextField phoneField = new JTextField(11);

        phoneBox.add(phoneLabel);
        phoneBox.add(Box.createHorizontalStrut(20));
        phoneBox.add(phoneField);

        //组装年龄
        Box AgeBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("年    龄：");
        JTextField tField = new JTextField(3);

        AgeBox.add(tLabel);
        AgeBox.add(Box.createHorizontalStrut(20));
        AgeBox.add(tField);

        //组装性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别：");
        JRadioButton maleBtn = new JRadioButton("男",true);
        JRadioButton femaleBtn = new JRadioButton("女",false);

        //实现单选的效果
        ButtonGroup bg = new ButtonGroup();
        bg.add(maleBtn);
        bg.add(femaleBtn);

        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(femaleBtn);
        gBox.add(Box.createHorizontalStrut(120));

        /*//组装验证码
        Box cBox = Box.createHorizontalBox();
        JLabel cLabel = new JLabel("验证码：");
        JTextField cField = new JTextField(4);
        JLabel cImg = new JLabel(new ImageIcon(ImageRequestUtils.getImage("http://localhost:8080/code/getCheckCode")));
        //给某个组件设置提示信息
        cImg.setToolTipText("点击刷新");
        cImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cImg.setIcon(new ImageIcon(ImageRequestUtils.getImage("http://localhost:8080/code/getCheckCode")));
                cImg.updateUI();
            }
        });

        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(20));
        cBox.add(cField);
        cBox.add(cImg);*/



        //考研方向
        Box DirBox = Box.createHorizontalBox();
        JLabel Dirlabel = new JLabel("考研方向");
        JTextField DirField = new JTextField(15);

        DirBox.add(Dirlabel);
        DirBox.add(Box.createHorizontalStrut(20));
        DirBox.add(DirField);

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton registBtn = new JButton("注册");
        JButton backBtn = new JButton("返回登录页面");

        registBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户录入的信息
                String username = uField.getText().trim();
                String password = pField.getText().trim();
                String phone = phoneField.getText().trim();
                String age = tField.getText().trim();
                //性别
                String sex = bg.isSelected(maleBtn.getModel())? maleBtn.getText():femaleBtn.getText();
                //考研方向
                String dir = DirField.getText().trim();

                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                params.put("phone",phone);
                params.put("age",age);
                params.put("sex",sex);
                params.put("dir",dir);
                //访问后台接口
                PostUtils.postWithParams("http://localhost:8080/user/register", params, new SuccessListener() {
                    @Override
                    public Object success(String result) {
                        ResultInfo info = JsonUtils.parseResult(result);
                        if (info.isFlag()){
                            //注册成功
                            JOptionPane.showMessageDialog(jf,"注册成功，即将返回登录页面");
                            try {
                                new MainInterface().init();
                                jf.dispose();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }else{
                            //注册失败
                            JOptionPane.showMessageDialog(jf,info.getMessage());
                        }
                        return null;
                    }
                }, new FailListener() {
                    @Override
                    public void fail() {
                        JOptionPane.showMessageDialog(jf,"该手机号码已被注册过");
                    }
                });
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //返回到登录页面即可
                try {
                    new MainInterface().init();
                    jf.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        btnBox.add(registBtn);
        btnBox.add(Box.createHorizontalStrut(80));
        btnBox.add(backBtn);


        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(phoneBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(gBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(AgeBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(DirBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(btnBox);

        jPanel.add(vBox);

        jf.add(jPanel);

        jf.setVisible(true);

    }


  public static void main(String[] args) {
        try {
            new RegisterInterface().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
