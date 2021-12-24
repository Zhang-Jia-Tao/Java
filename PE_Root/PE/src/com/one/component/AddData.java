package com.one.component;

import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ScreenUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class AddData extends JDialog {
    final int WIDTH = 350;
    final int HEIGHT = 300;

    ActionDoneListener listener = null;

    public AddData(JFrame jf,String title,boolean isModel,ActionDoneListener listener){
        super(jf,title,isModel);
        this.listener = listener;
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        //资料类型
        Box TypeBox = Box.createHorizontalBox();
        JLabel jl_type = new JLabel("资料类型");
        JComboBox<String> stringJComboBox = new JComboBox<>();
        stringJComboBox.addItem("计算机");
        stringJComboBox.addItem("数学");
        stringJComboBox.addItem("英语");
        stringJComboBox.addItem("政治");
        TypeBox.add(jl_type);
        TypeBox.add(Box.createHorizontalStrut(20));
        TypeBox.add(stringJComboBox);

        //资料地址
        Box Box_add = Box.createHorizontalBox();
        JLabel jl_add = new JLabel("资料地址");
        JTextField jt_add = new JTextField(150);
        Box_add.add(jl_add);
        Box_add.add(Box.createHorizontalStrut(20));
        Box_add.add(jt_add);

        //密码
        Box Box_pas = Box.createHorizontalBox();
        JLabel jl_pas = new JLabel("密码");
        JTextField jt_pas = new JTextField(20);
        Box_pas.add(jl_pas);
        Box_pas.add(Box.createHorizontalStrut(40));
        Box_pas.add(jt_pas);

        //简介
        Box Box_content = Box.createHorizontalBox();
        JLabel jl_content = new JLabel("简介");
        JTextField jt_content = new JTextField(150);
        Box_content.add(jl_content);
        Box_content.add(Box.createHorizontalStrut(40));
        Box_content.add(jt_content);



        //组装按钮
        Box Box_bn = Box.createHorizontalBox();
        JButton jButton = new JButton("添加");
        Box_bn.add(jButton);




        vBox.add(Box.createVerticalStrut(20));
        vBox.add(TypeBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_add);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_pas);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_content);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_bn);


        this.add(vBox);


        //为按钮添加监听
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String type = (String)stringJComboBox.getSelectedItem();
                String address = jt_add.getText();
                String password = jt_pas.getText();
                String content = jt_content.getText();

                //给服务器传送数据
                if(type.isEmpty() | address.isEmpty() | password.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"不能为空");
                } else {
                    Map<String,Object> param = new HashMap<>();
                    param.put("type",type);
                    param.put("address",address);
                    param.put("password",password);
                    param.put("content",content);

                    GetUtils.getWithParams("http://localhost:8080/data/upload",param, new SuccessListener() {
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
