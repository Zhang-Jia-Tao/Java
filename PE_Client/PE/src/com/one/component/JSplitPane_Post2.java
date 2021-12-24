package com.one.component;

import com.one.UserTec.QueryUser;
import com.one.domain.ResultInfo;

import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_post;
import com.one.util.WrapLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class JSplitPane_Post2 extends JSplitPane {

    Vector<Vector> vectors;
    String type = null;
    JFrame jf = null;
    JSplitPane_Post2 jSplitPane_post2 = null;
    JPanel left_panel = null;
    JScrollPane left_scro = null;
    DefaultMutableTreeNode node = null;
    JTree jTree = null;

    public JSplitPane_Post2(String type, JFrame jf, DefaultMutableTreeNode node,JTree jTree){
        super();
        this.setDividerLocation(500);
        this.setContinuousLayout(false);
        this.setDividerSize(10);
        this.jTree = jTree;

        //数据修改区---
            this.jSplitPane_post2 = this;
            this.type = type;
            this.jf = jf;
            this.node = node;

        //左侧面板
        left_panel = new JPanel(new WrapLayout(FlowLayout.LEFT));
        left_panel.setBackground(new Color(0xB9ECEC));

        BacktoDate();

        //循环添加组件
        AddComponent();

        //---------------------------------
        //右侧面板
        JPanel right_panel = new JPanel();
        right_panel.setBackground(new Color(0x2FC2C2));

        //发送框
        JTextArea Postpost = new JTextArea(40,15);

        //发帖按钮
        JButton ToPostpost = new JButton("发帖");
        ToPostpost.setBounds(600,700,30,30);

        right_panel.add(Postpost,BorderLayout.NORTH);
        right_panel.add(ToPostpost,BorderLayout.SOUTH);

        this.setRightComponent(right_panel);

        //发送帖子
        ToPostpost.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String,String> param = new HashMap<>();
                String content = Postpost.getText();
                param.put("Content",content);
                param.put("type",type);

                if(Postpost.getText().isEmpty()){
                    JOptionPane.showMessageDialog(jf,"发送的内容不能为空");
                    return;
                }
                PostUtils.postWithParams("http://localhost:8080/post/insert",param, new SuccessListener() {
                    @Override
                    public Object success(String result) {
                        ResultInfo info = JsonUtils.parseResult(result);
                        if(info.isFlag()){
                            JOptionPane.showMessageDialog(jf,"发帖成功");

                            //进行回调
                            left_panel.removeAll();

                            BacktoDate();

                            AddComponent();
                            //回调成功 --- 可以进行封装

                        } else {
                            JOptionPane.showMessageDialog(jf,"发帖失败,服务器问题");
                        }
                        return null;
                    }
                }, new FailListener() {
                    @Override
                    public void fail() {

                    }
                });
            }
        });



    }

    //获取帖子
    public void BacktoDate(){
        Map<String,String> pa = new HashMap<>();
        pa.put("type",type);
        PostUtils.postWithParams("http://localhost:8080/post/query",pa, new SuccessListener() {
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

            }
        });
    }

    //循环添加组件   ---用于回调
    public void AddComponent(){
        int y = 0;
        for(int i=0;i<vectors.size();i++){
            //每一个帖子所用的面板
            JPanel post_panel = new JPanel();
            post_panel.setBounds(0,y,500,150);
            post_panel.setBackground(new Color(0x7BDBDB));

            //显示帖子内容
            JTextArea post_content = new JTextArea(2,17);
            JPanel jPanel = new JPanel();
            jPanel.add(post_content);
            JScrollPane post_scrol = new JScrollPane();
            post_scrol.setViewportView(post_content);
            post_content.setText(""+vectors.elementAt(i).elementAt(0)+":"+vectors.elementAt(i).elementAt(2)+
                    "---"+vectors.elementAt(i).elementAt(4));

            //用于存放按钮的面板
            JPanel btn_panel = new JPanel();

            //功能按钮
            JButton query_user = new JButton("查看用户");
            JButton like = new JButton("点赞:"+vectors.elementAt(i).elementAt(3));
            JButton following = new JButton("跟帖");

            btn_panel.add(query_user);
            btn_panel.add(like);
            btn_panel.add(following);

            post_panel.add(post_scrol,BorderLayout.NORTH);
            post_panel.add(btn_panel,BorderLayout.SOUTH);

            left_panel.add(post_panel);

            y+=150;

            //添加查看用户信息的监听事件
            query_user.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        //首先查找到PostID
                    JTextArea textArea = null;
                    JScrollPane scrollPane = null;
                    JViewport jViewport = null;

                    JButton jButton = (JButton) e.getSource();
                    JPanel fa_jPanel = (JPanel) jButton.getParent();
                    JPanel gr_jPanel = (JPanel) fa_jPanel.getParent();
                    Component[] components = gr_jPanel.getComponents();
                    for(Component component:components){
                        if(component instanceof JScrollPane){
                            scrollPane = (JScrollPane) component;
                            break;
                        }
                    }
                    Component[] components1 = scrollPane.getComponents();
                    for(Component component:components1){
                        if(component instanceof JViewport){
                            jViewport = (JViewport) component;
                            Component[] components2 = jViewport.getComponents();
                            for(Component c:components2){
                                if(c instanceof JTextArea){
                                    textArea = (JTextArea) c;
                                    break;
                                }
                            }
                        }
                    }
                    String text = textArea.getText();  //依据PostID进行点赞的更新
                    String[] split = text.split(":");
                    String id = split[0];

                    //依据PostID 进行查找UserID
                    QueryUser(id);

                }
            });

            //点赞监听事件
            like.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextArea textArea = null;
                    JScrollPane scrollPane = null;
                    JViewport jViewport = null;

                    JButton jButton = (JButton) e.getSource();
                    JPanel fa_jPanel = (JPanel) jButton.getParent();
                    JPanel gr_jPanel = (JPanel) fa_jPanel.getParent();
                    Component[] components = gr_jPanel.getComponents();
                    for(Component component:components){
                        if(component instanceof JScrollPane){
                            scrollPane = (JScrollPane) component;
                            break;
                        }
                    }
                    Component[] components1 = scrollPane.getComponents();
                    for(Component component:components1){
                        if(component instanceof JViewport){
                            jViewport = (JViewport) component;
                            Component[] components2 = jViewport.getComponents();
                            for(Component c:components2){
                                if(c instanceof JTextArea){
                                    textArea = (JTextArea) c;
                                    break;
                                }
                            }
                        }
                    }
                    String text = textArea.getText();  //依据PostID进行点赞的更新
                    String[] split = text.split(":");
                    String id = split[0];
                    //调用UpdateLike方法去更新喜欢
                    UpdateLike(id);
                }
            });
        }

        left_scro = new JScrollPane(left_panel);

        jSplitPane_post2.setLeftComponent(left_scro);

    }


    //增加喜欢
    public void UpdateLike(String id){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        GetUtils.getWithParams("http://localhost:8080/post/update", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jf,"点赞成功");

                    //进行回调
                    left_panel.removeAll();
                    vectors.clear();

                    BacktoDate();

                    AddComponent();
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }

    //查找UserId，依据UserID进行来查找用户的信息
    public void QueryUser(String id){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        GetUtils.getWithParams("http://localhost:8080/post/queryuser", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    //调用一个展示个人信息的窗口JDialog
                    Map<String,Object> map = (Map<String, Object>) info.getData();
                    new QueryUser(jf,"用户信息",true,map,node,jTree).setVisible(true);

                }
                JOptionPane.showMessageDialog(jf,info.getMessage());
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }
}
