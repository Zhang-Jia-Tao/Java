package com.one.UserTec;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_message;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Box_Talk {

    String username = "";
    String phone = "";

    int Width = 850;
    int Height = 600;

    JPanel jPanel = null;
    JTextArea jTextArea = null;
    JScrollPane jScrollPane = null;
    String receive_id = null;       //接受方id
    JPanel jso_japnel = null;

    public Box_Talk(String username,String phone){
        this.username = username;
        this.phone = phone;

    }

    public JPanel init(){

        //主面板
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0,0,900,600);
        jPanel.setBackground(new Color(0x058686));


        //查询接收方id
        queryReceiveid();


        //上面板   ----用于聊天框和好友信息
        JPanel jPanel_up = new JPanel();
        jPanel_up.setBounds(0,0,900,400);
        jPanel_up.setBackground(new Color(0xE6F1EB));
        jPanel_up.setLayout(null);

        //聊天框
        jScrollPane = new JScrollPane();
        jScrollPane.setLayout(null);
        jScrollPane.setBounds(0,0,750,400);
        jso_japnel = new JPanel();
        jso_japnel.setLayout(null);
        jso_japnel.setBounds(0,0,750,400);
        jScrollPane.add(jso_japnel);


        //好友信息
        JPanel fri_jpanel = new JPanel();
        fri_jpanel.setBounds(750,0,216,400);
        fri_jpanel.setBackground(new Color(0xE2F7F7));
        fri_jpanel.setLayout(null);

        jPanel_up.add(jScrollPane);
        jPanel_up.add(fri_jpanel);


        //下面板
        JPanel jPanel_down = new JPanel();
        jPanel_down.setBounds(0,400,900,200);
        jPanel_down.setBackground(new Color(0xBDECEC));
        jPanel_down.setLayout(null);

        jTextArea = new JTextArea(10,10);
        jTextArea.setBounds(0,0,800,200);

        JButton jButton = new JButton("发送");
        jButton.setBounds(800,0,100,200);

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //发送
                send();
            }
        });


        /*
        *
        * */
        querymessage();

        jPanel_down.add(jTextArea);
        jPanel_down.add(jButton);

        jPanel.add(jPanel_up);
        jPanel.add(jPanel_down);
       return jPanel;
    }


    //发送
    public void send(){
        String text = jTextArea.getText();
        if(text == "" | text.isEmpty()){
            JOptionPane.showMessageDialog(jPanel,"发送的信息不能为空");
            return;
        }

        Map<String,String> param = new HashMap<>();
        param.put("content",text);
        param.put("receive_id",receive_id);
        PostUtils.postWithParams("http://localhost:8080/message/add", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jPanel,info.getMessage());
                    /*
                    * 其实也可以每当发送完一个消息后就将该信息添加到滑动面板上，但是有些麻烦
                    *
                    * */

                    jso_japnel.removeAll();

                    querymessage();

                    jso_japnel.repaint();

                } else {
                    JOptionPane.showMessageDialog(jPanel,info.getMessage());
                }


                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });



    }


    //利用username，phone来查询接受方id
    public void queryReceiveid(){

        if(username.isEmpty() | phone.isEmpty()){
            JOptionPane.showMessageDialog(jPanel,"未知错误 因为好友信息未知");
        }

        Map<String,Object> param = new HashMap<>();
        param.put("username",username);
        param.put("phone",phone);

        GetUtils.getWithParams("http://localhost:8080/message/query", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    receive_id = info.getData().toString();
                }
                return null;

            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }


    /*
    * 还存在一个一个问题就是将聊天窗口变成可滑动的窗口 以及将发送的短信变为可滑动的文本
    *
    *
    * */


    //查询与好友的聊天信息
    public void querymessage(){
        Map<String,String> param = new HashMap<>();
        param.put("receive_id",receive_id);
        PostUtils.postWithParams("http://localhost:8080/message/search",param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    Vector<Vector> vectors = ResultInfoData2Vector_message.convertResultInfoData2Vector(info);
                    //循环添加组件
                    int y=0;
                    for(int i=0;i<vectors.size();i++){
                        if(vectors.elementAt(i).elementAt(0).toString().equals(receive_id)){
                            JScrollPane jScrollPane = new JScrollPane();
                            JTextArea jTextArea = new JTextArea(3,10);
                            jScrollPane.setLayout(null);
                            jTextArea.setText(vectors.elementAt(i).elementAt(1).toString()+
                                    "           -----"+vectors.elementAt(i).elementAt(2).toString());
                            jTextArea.setBounds(0,0,375,30);
                            jScrollPane.add(jTextArea);
                            jScrollPane.setBounds(375,y,375,30);
                            jso_japnel.add(jScrollPane);
                            y = y + 30;
                        } else {
                            JScrollPane jScrollPane = new JScrollPane();
                            JTextArea jTextArea = new JTextArea(3,10);
                            jScrollPane.setLayout(null);
                            jTextArea.setText(vectors.elementAt(i).elementAt(1).toString()+
                                    "           -----"+vectors.elementAt(i).elementAt(2).toString());
                            jTextArea.setBounds(0,0,375,30);
                            jScrollPane.add(jTextArea);
                            jScrollPane.setBounds(0,y,375,30);
                            jso_japnel.add(jScrollPane);
                            y = y + 30;
                        }
                    }

                }
                //JOptionPane.showMessageDialog(jPanel,info.getMessage());
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }



}
