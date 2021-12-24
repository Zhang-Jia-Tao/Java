package com.one.GUI;


import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.ImageRequestUtils;
import com.one.util.JsonUtils;
import com.one.util.ScreenUtils;
import com.one.util.Serviceutil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class MainInterface {

    JFrame jf = new JFrame("考研互助");

    final int WIDTH = 500;
    final int HEIGHT = 300;

    //组装视图
    public void init() throws Exception {
        //设置窗口相关的属性
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        jf.setResizable(false);
        jf.setBackground(new Color(0xC0F4F4));

//        jf.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("0.jpeg"))));


        //设置窗口的内容
//        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File(PathUtils.getRealPath("2.jpg"))));
        JPanel bgPanel = new JPanel();
        bgPanel.setBounds(0,0,WIDTH,HEIGHT);




        //组装登录相关的元素
        Box vBox = Box.createVerticalBox();

        //组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名：");
        JTextField uField = new JTextField(15);
        uField.setBackground(new Color(0xCFECEC));

        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码：");
        JPasswordField pField = new JPasswordField(15);
        pField.setBackground(new Color(0xCFECEC));

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装验证码
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
        cBox.add(cImg);


        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        loginBtn.setForeground(new Color(0x39F7F7));
        loginBtn.setMargin(new Insets(0,0,0,0));
        loginBtn.setIconTextGap(0);
        loginBtn.setBorderPainted(false);
        loginBtn.setBorder(null);
        loginBtn.setFocusPainted(false);
        loginBtn.setContentAreaFilled(false);

        JButton registBtn = new JButton("注册");
        registBtn.setForeground(new Color(0x39F7F7));
        registBtn.setMargin(new Insets(0,0,0,0));
        registBtn.setIconTextGap(0);
        registBtn.setBorderPainted(false);
        registBtn.setBorder(null);
        registBtn.setFocusPainted(false);
        registBtn.setContentAreaFilled(false);


        loginBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //获取用户输入的数据
               String username = uField.getText().trim();
               String password = pField.getText().trim();
               String checkcode = cField.getText().trim();
               Map<String,String> params = new HashMap<>();
               params.put("username",username);
               params.put("password",password);
               params.put("checkcode",checkcode);

               //访问登录接口
               PostUtils.postWithParams("http://localhost:8080/user/login", params, new SuccessListener() {
                   //请求发送成功，服务器正常响应，会执行这里
                   @Override
                   public Object success(String result) {//result参数就是服务器响应回来的json字符串
                       ResultInfo info = JsonUtils.parseResult(result);
                       //判断info中的flag标记
                       if (info.isFlag()){
                           //登录成功,跳转到主页面
                           try {
                               new ManagerInterface().init();
                               jf.dispose();

                               //更新IP
                               InetAddress localHost = InetAddress.getLocalHost();
                               String IP = localHost.getHostAddress();
                               UpdateIP(IP,username);
                           } catch (Exception ex) {
                               ex.printStackTrace();
                           }
                       }else{
                          // 登录失败
                           JOptionPane.showMessageDialog(jf,info.getMessage());
                       }
                       return null;
                   }
               }, new FailListener() {
                   //当该请求发送失败后会执行这个方法
                   @Override
                   public void fail() {
                        JOptionPane.showMessageDialog(jf,"网络异常，请稍后重试");
                   }
               });

           }


       });

       registBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //跳转到注册页面
               try {
                   new RegisterInterface().init();
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
               //当前界面消失
               jf.dispose();
           }
       });




        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(registBtn);
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(cBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);

        //为验证码框添加了键盘监听
        cField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    //获取用户输入的数据
                    String username = uField.getText().trim();
                    String password = pField.getText().trim();
                    String checkcode = cField.getText().trim();
                    Map<String,String> params = new HashMap<>();
                    params.put("username",username);
                    params.put("password",password);
                    params.put("checkcode",checkcode);

                    //访问登录接口
                    PostUtils.postWithParams(Serviceutil.userip+"/login", params, new SuccessListener() {
                        //请求发送成功，服务器正常响应，会执行这里
                        @Override
                        public Object success(String result) {//result参数就是服务器响应回来的json字符串
                            ResultInfo info = JsonUtils.parseResult(result);
                            //判断info中的flag标记
                            if (info.isFlag()){
                                //登录成功,跳转到主页面
                                try {
                                    new ManagerInterface().init();
                                    jf.dispose();

                                    //更新IP
                                    InetAddress localHost = InetAddress.getLocalHost();
                                    String IP = localHost.getHostAddress();
                                    UpdateIP(IP,username);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }else{
                                // 登录失败
                                JOptionPane.showMessageDialog(jf,info.getMessage());
                            }
                            return null;
                        }
                    }, new FailListener() {
                        //当该请求发送失败后会执行这个方法
                        @Override
                        public void fail() {
                            JOptionPane.showMessageDialog(jf,"网络异常，请稍后重试");
                        }
                    });
                }
            }
        });


    }

    //每次登陆更新IP地址
    void UpdateIP(String IP,String username){
        Map<String,String> map = new HashMap<>();
        map.put("IP",IP);
        map.put("username",username);
        PostUtils.postWithParams(Serviceutil.userip +"/ip", map, new SuccessListener() {
            @Override
            public Object success(String result) {

                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }


    //客户端程序的入口
    public static void main(String[] args) {
        try {
            new MainInterface().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
