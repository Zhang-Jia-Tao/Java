package com.one.component;


import com.one.GUI.ManagerInterface;
import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

//更新用户信息
public class UpdateUser {

    private int WIDTH = 300;
    private int HEIGHT = 500;

    JFrame jf = new JFrame("更新用户信息");

    public void init(){

        //设置窗口的属性
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        //jf.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("logo.png"))));

        JPanel jPanel = new JPanel();
        jPanel.setBounds(0,0,500,400);


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

        //考研方向
        Box DirBox = Box.createHorizontalBox();
        JLabel Dirlabel = new JLabel("考研方向");
        JTextField DirField = new JTextField(15);

        DirBox.add(Dirlabel);
        DirBox.add(Box.createHorizontalStrut(20));
        DirBox.add(DirField);

        Box PhoBox = Box.createHorizontalBox();
        JLabel Pholabel = new JLabel("电  话");
        JTextField PhoField = new JTextField(15);

        PhoBox.add(Pholabel);
        PhoBox.add(Box.createHorizontalStrut(20));
        PhoBox.add(PhoField);

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton registBtn = new JButton("确认修改");
        JButton backBtn = new JButton("返回主界面");

        registBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户录入的信息
                String username = uField.getText().trim();
                String password = pField.getText().trim();
                String age = tField.getText().trim();
                //性别
                String sex = bg.isSelected(maleBtn.getModel())? maleBtn.getText():femaleBtn.getText();
                //考研方向
                String dir = DirField.getText().trim();
                String phone = PhoField.getText().trim();

                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                params.put("age",age);
                params.put("sex",sex);
                params.put("pe_dir",dir);
                params.put("phone",phone);

                if("".equals(username) | "".equals(password) | "".equals(age) | "".equals(sex) | "".equals(dir) | "".equals(phone)){
                    JOptionPane.showMessageDialog(jf,"要求填写的内容不能为空");
                    return;
                }


                //访问后台接口
                PostUtils.postWithParams("http://localhost:8080/user/update", params, new SuccessListener() {
                    @Override
                    public Object success(String result) {
                        ResultInfo info = JsonUtils.parseResult(result);
                        if (info.isFlag()){
                            //更新成功
                            JOptionPane.showMessageDialog(jf,"更新成功，即将返回主界面");
                            try {
                                new ManagerInterface().init();
                                jf.dispose();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }else{
                            //更新失败
                            JOptionPane.showMessageDialog(jf,info.getMessage());
                        }
                        return null;
                    }
                }, new FailListener() {
                    @Override
                    public void fail() {
                        JOptionPane.showMessageDialog(jf,"网络异常，请稍后重试");
                    }
                });
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //返回到主界面页面即可
                try {
                    new ManagerInterface().init();
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
        vBox.add(gBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(AgeBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(DirBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(PhoBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(btnBox);

        jPanel.add(vBox);

        jf.add(jPanel);

        jf.setVisible(true);

    }



}


