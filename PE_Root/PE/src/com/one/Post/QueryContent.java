package com.one.Post;

import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

//查询帖子的详细内容
public class QueryContent extends JDialog {
    final int WIDTH = 350;
    final int HEIGHT = 350;
    String id = null;

    JTextArea jTextArea = null;
    ActionDoneListener listener = null;
    JDialog jDialog = null;

    public QueryContent(JFrame jf, String title, boolean isModel,String id,ActionDoneListener listener){
        super(jf,title,isModel);
        this.id = id;
        this.jDialog = this;
        this.listener = listener;
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        Box box = Box.createHorizontalBox();
        jTextArea = new JTextArea();
        box.setBounds(0,0,350,280);
        box.add(jTextArea);

        Box btnbox = Box.createHorizontalBox();
        JButton btn = new JButton("确认");
        JButton delBtn = new JButton("删除");
        JButton FengBtn = new JButton("封号");

        btnbox.add(btn);
        btnbox.add(delBtn);
        btnbox.add(FengBtn);

        vBox.add(box);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(btnbox);

        this.add(vBox);

        QueryById();

        btn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        delBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //调用删除方法
                new DeletePost(id, jf,listener,jDialog);
            }
        });

        FengBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProhibitAccount(id,jf,listener);
            }
        });


    }

    //通过id 查询帖子内容
    public void QueryById(){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        GetUtils.getWithParams("http://localhost:8080/post/querycontent",param, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                String content = (String)info.getData();

                jTextArea.setText(content);

            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }


}
