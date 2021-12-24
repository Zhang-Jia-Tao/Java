package com.one.component;


import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_user;
import com.one.util.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//添加好友
public class AddFriend {

    private final static int WIDTH = 300;
    private final static int HEIGHT = 200;

    boolean bool = true;

    //保存查询到的好友信息
    Vector<Vector> vectors = new Vector<>();

    public void AddFriends(){

        JFrame jFrame = new JFrame("添加好友");

        jFrame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box box = Box.createVerticalBox();

        JTextField jTextArea = new JTextField();
        jTextArea.setColumns(20);
        jTextArea.setMaximumSize(new Dimension(300,100));

        jTextArea.setText("请输入对方的手机号或用户名");

        JButton jButton = new JButton("添加");
        jButton.setMaximumSize(new Dimension(150,50));

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextArea.getText();

                //查询好友
                boolean b = searchfriend(text);

                if(b == false){
                    JOptionPane.showMessageDialog(box,"用户名不存在或输入不存在");
                    return;
                }
                if(vectors.isEmpty()){
                    JOptionPane.showMessageDialog(box,"用户不存在或输入错误");
                    return;
                }

                //查询是否已经添加过了   true为添加过来 false 为没有添加过 若添加过了
                //则不能再次进行添加处理
                boolean c = queryConfirm();

                if(b&(!c)){
                    //将要添加人的id添加到param中
                    Map<String,Object> param = new HashMap<>();
                    param.put("Receive_id",vectors.elementAt(0).elementAt(0).toString());
                    //往确认表中增添申请信息
                    GetUtils.getWithParams("http://localhost:8080/confirm/add",param, new SuccessListener() {
                        @Override
                        public Object success(String result) {
                            ResultInfo info = JsonUtils.parseResult(result);
                            if(info.isFlag()){
                                JOptionPane.showMessageDialog(box,"好友申请已发送");
                            }
                            return null;
                        }
                    }, new FailListener() {
                        @Override
                        public void fail() {

                        }
                    });
                } else {
                    if(!b){
                        JOptionPane.showMessageDialog(box,"好友不存在，请重新尝试");
                    } else {
                        JOptionPane.showMessageDialog(box,"你已经申请添加他为好友了，抱歉不能频繁添加");
                    }
                }

            }
        });

        box.add(jTextArea);
        box.add(Box.createVerticalStrut(50));
        box.add(jButton);


        jFrame.add(box);

        jFrame.setVisible(true);
    }

    //查询好友
    public boolean searchfriend(String id){
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        bool = true;
        PostUtils.postWithParams("http://localhost:8080/user/friend", map, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if(resultInfo.isFlag()){
                    vectors = ResultInfoData2Vector_user.convertResultInfoData2Vector(resultInfo);
                } else {
                     bool = false;
                }

                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
        return bool;
    }

    //查询好友成功后，需要将其更新到好友表中
    public void UpdateFriend(String username,String phone){
        Map<String,String> param = new HashMap<>();
        param.put("name",username);
        param.put("phone",phone);
        PostUtils.postWithParams("http://localhost:8080/friend/update", param, new SuccessListener() {
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



    //查询添加信息,看是否添加过了
    public boolean queryConfirm(){
        final boolean[] flag = {true};
        Map<String,Object> param = new HashMap<>();
        param.put("Receive_id",vectors.elementAt(0).elementAt(0));
        GetUtils.getWithParams("http://localhost:8080/confirm/query", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(!info.isFlag()){
                    flag[0] = true;
                } else {
                    //如果没有添加过，才可以添加
                    flag[0] = false;
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
        return flag[0];
    }



    public static void main(String[] args) {
        new AddFriend();
    }

}
