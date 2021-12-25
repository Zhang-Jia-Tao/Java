package com.one.Admin;

import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class AddAdmin extends JDialog {
    final int WIDTH = 350;
    final int HEIGHT = 300;

    ActionDoneListener listener = null;

    public AddAdmin(JFrame jf,String title,boolean isModel,ActionDoneListener listener){
        super(jf,title,isModel);
        this.listener = listener;
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();


        //管理员名称
        Box Box_name = Box.createHorizontalBox();
        JLabel jl_add = new JLabel("管理员名称");
        JTextField jt_add = new JTextField(150);
        Box_name.add(jl_add);
        Box_name.add(Box.createHorizontalStrut(20));
        Box_name.add(jt_add);

        //密码
        Box Box_pas = Box.createHorizontalBox();
        JLabel jl_pas = new JLabel("密码");
        JTextField jt_pas = new JTextField(20);
        Box_pas.add(jl_pas);
        Box_pas.add(Box.createHorizontalStrut(40));
        Box_pas.add(jt_pas);


        //管理员名称
        Box LevelBox = Box.createHorizontalBox();
        JLabel jl_type = new JLabel("权限等级");
        JComboBox<String> stringJComboBox = new JComboBox<>();
        stringJComboBox.addItem("9");
        stringJComboBox.addItem("8");
        stringJComboBox.addItem("7");
        stringJComboBox.addItem("6");
        stringJComboBox.addItem("5");
        LevelBox.add(jl_type);
        LevelBox.add(Box.createHorizontalStrut(20));
        LevelBox.add(stringJComboBox);


        //组装按钮
        Box Box_bn = Box.createHorizontalBox();
        JButton jButton = new JButton("添加");
        Box_bn.add(jButton);




        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_name);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_pas);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(LevelBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_bn);


        this.add(vBox);


        //为按钮添加监听
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String level = (String)stringJComboBox.getSelectedItem();
                String name = jt_add.getText();
                String password = jt_pas.getText();

                //给服务器传送数据
                if(level.isEmpty() | name.isEmpty() | password.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"不能为空");
                } else {
                    Map<String,String> param = new HashMap<>();
                    param.put("level",level);
                    param.put("name",name);
                    param.put("password",password);

                    PostUtils.postWithParams("http://localhost:8080/admin/add",param, new SuccessListener() {
                        @Override
                        public void success(String result) {
                            ResultInfo info = JsonUtils.parseResult(result);
                            if(info.isFlag()){
                                JOptionPane.showMessageDialog(jf,info.getMessage());

                                dispose();

                                //刷新
                                listener.done(null);
                            }
                        }
                    }, new FailListener() {
                        @Override
                        public void fail() {
                            JOptionPane.showMessageDialog(jf,"传输不稳定");
                        }
                    });
                }


            }
        });
    }

}

