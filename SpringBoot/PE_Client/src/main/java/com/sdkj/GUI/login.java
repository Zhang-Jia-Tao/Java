package com.sdkj.GUI;


import com.sdkj.domain.User;
import com.sdkj.util.ScreenUtils;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class login {

    final int WIDTH = 500;
    final int HEIGH = 300;

    JFrame jFrame = new JFrame("考研互助App");
    User user = new User();


    public void init() throws Exception{

        jFrame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGH)/2,WIDTH,HEIGH);
        jFrame.setResizable(false);

        //设置logo
        //jFrame.setIconImage(ImageIO.read(new File("/Users/zhangjiatao/Desktop/SpringBoot/PE_Client/src/main/java/com/sdkj/image/background.jpg")));





        //设置窗口的内容
        //BackgrounPanel jPanel = null;
        //jPanel = new BackgrounPanel(ImageIO.read(new File("/Users/zhangjiatao/Desktop/SpringBoot/PE_Client/src/main/java/com/sdkj/image/background.jpeg")));
        JPanel jPanel = new JPanel();
        jPanel.setLocation(0,0);

        //组装登陆相关的元素
        Box vBox = Box.createVerticalBox();

        //组装用户名
        Box UserBox = Box.createHorizontalBox();
        JLabel UserLabel = new JLabel("用户名：");
        JTextField UserTextField = new JTextField(15);

        UserBox.add(UserLabel);
        UserBox.add(Box.createHorizontalStrut(20));
        UserBox.add(UserTextField);

        //组装密码
        Box PasBox = Box.createHorizontalBox();
        JLabel PasLabel = new JLabel("密    码：");
        JTextField PasTextField = new JTextField(15);

        PasBox.add(PasLabel);
        PasBox.add(Box.createHorizontalStrut(20));
        PasBox.add(PasTextField);

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton LoginBtn = new JButton("登陆");
        JButton RegistryBtn = new JButton("注册");

        //监听登陆
        LoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的账号密码
                String username = UserTextField.getText();
                String password = PasTextField.getText();


            }
        });



        btnBox.add(LoginBtn);
        btnBox.add(Box.createHorizontalStrut(20));
        btnBox.add(RegistryBtn);


        vBox.add(Box.createVerticalStrut(50));
        vBox.add(UserBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(PasBox);
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(btnBox);

        jPanel.add(vBox);
        jFrame.add(jPanel);

        jFrame.setVisible(true);

    }
}
