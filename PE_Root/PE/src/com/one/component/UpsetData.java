package com.one.component;

import com.one.domain.Data;
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

public class UpsetData extends JDialog {
    final int WIDTH = 350;
    final int HEIGHT = 300;

     ActionDoneListener listener = null;
     String id = "";
     Map<String,String> map = null;
     Data data = null;

    JComboBox<String> stringJComboBox = null;
    JTextField jt_add = null;
    JTextField jt_pas = null;
    JTextField jt_content = null;

    //id 为 资料ID
    public UpsetData(JFrame jf, String title, boolean isModel, ActionDoneListener listener,String id){
        super(jf,title,isModel);
        this.listener = listener;
        this.id = id;
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        //资料类型
        Box TypeBox = Box.createHorizontalBox();
        JLabel jl_type = new JLabel("资料类型");
        stringJComboBox = new JComboBox<>();
        stringJComboBox.addItem("计算机");
        stringJComboBox.addItem("数学");
        stringJComboBox.addItem("英语");
        stringJComboBox.addItem("政治");
        stringJComboBox.addItem("经济学");
        TypeBox.add(jl_type);
        TypeBox.add(Box.createHorizontalStrut(20));
        TypeBox.add(stringJComboBox);


        //资料地址
        Box Box_add = Box.createHorizontalBox();
        JLabel jl_add = new JLabel("资料地址");
        jt_add = new JTextField(150);
        Box_add.add(jl_add);
        Box_add.add(Box.createHorizontalStrut(20));
        Box_add.add(jt_add);


        //密码
        Box Box_pas = Box.createHorizontalBox();
        JLabel jl_pas = new JLabel("密码");
        jt_pas = new JTextField(20);
        Box_pas.add(jl_pas);
        Box_pas.add(Box.createHorizontalStrut(40));
        Box_pas.add(jt_pas);


        //简介
        Box Box_content = Box.createHorizontalBox();
        JLabel jl_content = new JLabel("简介");
        jt_content = new JTextField(150);
        Box_content.add(jl_content);
        Box_pas.add(Box.createHorizontalStrut(40));
        Box_content.add(jt_content);


        //组装按钮
        Box Box_bn = Box.createHorizontalBox();
        JButton jButton = new JButton("修改");
        Box_bn.add(jButton);

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击修改，点用修改方法，进行修改

                String type = stringJComboBox.getSelectedItem().toString();
                String address = jt_add.getText();
                String content = jt_content.getText();
                String password = jt_pas.getText();

                if(type.isEmpty() | content.isEmpty() | address.isEmpty() | password.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"修改后的内容不能为空");
                }else if (type.equals(map.get("dataType")) & address.equals(map.get("dataAddress"))
                & content.equals(map.get(content)) & password.equals(map.get("verCode"))) {
                    JOptionPane.showMessageDialog(jf,"内容未进行修改，无法提交");
                }else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("id", id);
                    param.put("type", type);
                    param.put("content", content);
                    param.put("address", address);
                    param.put("password", password);

                    GetUtils.getWithParams("http://localhost:8080/data/set", param, new SuccessListener() {
                        @Override
                        public void success(String result) {
                            ResultInfo info = JsonUtils.parseResult(result);
                            if(info.isFlag()){
                                JOptionPane.showMessageDialog(jf,info.getMessage());

                                dispose();

                                listener.done(null);
                            } else {
                                JOptionPane.showMessageDialog(jf,info.getMessage());
                            }
                        }
                    }, new FailListener() {
                        @Override
                        public void fail() {
                            JOptionPane.showMessageDialog(jf,"服务器问题...运维正在抢修中");
                        }
                    });

                }
            }
        });




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

        RequestData();

    }



    //通过资料ID查询数据
    public void RequestData(){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        GetUtils.getWithParams("http://localhost:8080/data/select", param, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    map = new HashMap<>();
                    map = (Map<String, String>) info.getData();
                    stringJComboBox.setSelectedItem(map.get("dataType"));
                    jt_add.setText(map.get("dataAddress"));
                    jt_content.setText(map.get("content"));
                    jt_pas.setText(map.get("verCode"));

                } else {
                    JOptionPane.showMessageDialog(new JFrame(),info.getMessage());
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }


}
