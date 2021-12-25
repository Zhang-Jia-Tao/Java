package com.one.Admin;

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

public class UpdateAdmin extends JDialog {
    final int WIDTH = 350;
    final int HEIGHT = 300;

    ActionDoneListener listener = null;
    String id = "";
    Map<String,String> map = null;
    Data data = null;

    JComboBox<String> stringJComboBox = null;
    JTextField jt_name = null;
    JTextField jt_pas = null;

    //id 为 资料ID
    public UpdateAdmin(JFrame jf, String title, boolean isModel, ActionDoneListener listener,String id){
        super(jf,title,isModel);
        this.listener = listener;
        this.id = id;
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();


        //管理员名称
        Box Box_name = Box.createHorizontalBox();
        JLabel jl_name = new JLabel("管理员名称");
        jt_name = new JTextField(150);
        Box_name.add(jl_name);
        Box_name.add(Box.createHorizontalStrut(20));
        Box_name.add(jt_name);


        //密码
        Box Box_pas = Box.createHorizontalBox();
        JLabel jl_pas = new JLabel("密码");
        jt_pas = new JTextField(20);
        Box_pas.add(jl_pas);
        Box_pas.add(Box.createHorizontalStrut(40));
        Box_pas.add(jt_pas);

        //资料类型
        Box levelBox = Box.createHorizontalBox();
        JLabel jl_type = new JLabel("权限等级");
        stringJComboBox = new JComboBox<>();
        stringJComboBox.addItem("9");
        stringJComboBox.addItem("8");
        stringJComboBox.addItem("7");
        stringJComboBox.addItem("6");
        stringJComboBox.addItem("5");
        levelBox.add(jl_type);
        levelBox.add(Box.createHorizontalStrut(20));
        levelBox.add(stringJComboBox);


        //组装按钮
        Box Box_bn = Box.createHorizontalBox();
        JButton jButton = new JButton("修改");
        Box_bn.add(jButton);

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击修改，点用修改方法，进行修改

                String level = stringJComboBox.getSelectedItem().toString();
                String name = jt_name.getText();
                String password = jt_pas.getText();

                if(level.isEmpty() | name.isEmpty() | password.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"修改后的内容不能为空");
                }else if (name.equals(map.get("userAccount"))
                         & password.equals(map.get("password")) & level.equals(map.get("adminLeve"))) {
                    JOptionPane.showMessageDialog(jf,"内容未进行修改，无法提交");
                }else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("level",level);
                    param.put("name",name);
                    param.put("password", password);
                    param.put("id",id);

                    GetUtils.getWithParams("http://localhost:8080/admin/set", param, new SuccessListener() {
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
        vBox.add(Box_name);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_pas);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(levelBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_bn);


        this.add(vBox);

        RequestData();

    }



    //通过资料ID查询数据
    public void RequestData(){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        GetUtils.getWithParams("http://localhost:8080/admin/selectmis", param, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    map = new HashMap<>();
                    map = (Map<String, String>) info.getData();
                    stringJComboBox.setSelectedItem(map.get("adminLeve"));
                    jt_name.setText(map.get("userAccount"));
                    jt_pas.setText(map.get("password"));

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

