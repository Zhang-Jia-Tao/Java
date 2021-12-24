package com.one.component;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_post;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class JSplitPane_Post_giveup extends JSplitPane {

    JTextArea jTextArea = null;
    JButton AddUser = null;
    JButton Like = null;

    int post_num = 0; //帖子的数量
    String type = "计算机";   //默认计算机

    Vector<Vector> vectors;  //返回的帖子存放于此

    public JSplitPane_Post_giveup(String type){
        super(JSplitPane.HORIZONTAL_SPLIT);
        this.type = type;

        this.setDividerLocation(450);
        this.setDividerSize(10);

        //左侧为帖子
        JScrollPane jScrollPane = new JScrollPane();
        JPanel panel = new JPanel();

        //请求帖子
        BacktoDate();


        //循环添加帖子到左侧
        for(int i=0;i<vectors.size();i++){
           box(panel,"wuwu","12");
        }

        jScrollPane.add(panel);
        this.setLeftComponent(jScrollPane);

        Box postBox = Box.createHorizontalBox();
        JTextArea Postpost = new JTextArea(40,10);
        JButton post = new JButton("发表");
        postBox.add(Postpost);
        postBox.add(Box.createVerticalStrut(20));
        postBox.add(post);
        this.setRightComponent(postBox);

    }



    //每一个帖子使用Box来进行封装
    public void box(JPanel jPanel,String content,String num){
        //帖子的内容
        jTextArea = new JTextArea(3,5);
        jTextArea.setText(content);
        JScrollPane jScrollPane_text = new JScrollPane();
        jScrollPane_text.add(jTextArea);

        JPanel j1 = new JPanel();
        j1.setLayout(new FlowLayout());
        AddUser = new JButton("查看用户");
        Like = new JButton("点赞"+num);
        j1.add(AddUser);
        j1.add(Like);

        jPanel.add(jScrollPane_text);
        jPanel.add(j1);

    }

    //获取帖子
    public void BacktoDate(){
        Map<String,Object> param = new HashMap<>();
        param.put("type",type);
        GetUtils.getWithParams("http://localhost:8080/post/query",param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if(resultInfo.isFlag()){
                    vectors = ResultInfoData2Vector_post.convertResultInfoData2Vector(resultInfo);
                } else {
                    System.out.println("异常，");
                }

                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {
                JOptionPane.showMessageDialog(jTextArea,"网络异常，请退出重试");
            }
        });
    }

}

