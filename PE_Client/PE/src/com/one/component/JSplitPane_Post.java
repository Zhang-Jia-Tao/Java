package com.one.component;

import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;

import com.one.net_client.GetUtils;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_post;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//还存在一个bug 就是无法在用户发表了帖子后无法实时更新左侧面板的内容


//贴吧区
public class JSplitPane_Post extends JSplitPane{


    //用与显示帖子
    Map<String,String> pa = new HashMap<>();

    //用于点赞
    Map<String,Object> pa2 = new HashMap<>();
    Integer PostId = 1;
    int res = 0;

    int flag = 0;

    Vector<Vector> vectors = new Vector<>();
    Map<String,String> params = new HashMap<>();
    JTextArea jTextArea;



    public JSplitPane_Post(String type){
        super(JSplitPane.HORIZONTAL_SPLIT);

        params.put("type",type);
        pa.put("type",type);


        this.setDividerLocation(450);
        this.setContinuousLayout(true);

        Box box = Box.createVerticalBox();
        JScrollPane jScrollPane = new JScrollPane(box,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setTopComponent(jScrollPane);

        BacktoDate();

        for(int i=0;i<vectors.size();i++){
            JTextArea jTextArea = new JTextArea(vectors.elementAt(i).elementAt(0)+":"+vectors.elementAt(i).elementAt(2)+""+
                    "                          ---"+vectors.elementAt(i).elementAt(4)+"");
            JButton jButton = new JButton("点赞 :"+vectors.elementAt(i).elementAt(3));
            jButton.setBounds(400,50+50*i,50,50);
                jButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {      //点赞怎么解决  是个bug
                        JButton jButton1 = (JButton) e.getSource();
                        String text = jButton1.getText();
                        String[] strings = text.split(":");
//                        UpdatePost(Integer.parseInt(strings[0]));
                        jButton1.setText("点赞 :"+(Integer.parseInt(strings[1])+1));
                        /*
                        *  利用帖子的id区查询按钮点击的是哪一个帖子，因此在进行+1操作
                        * */

                    }
                });

            box.add(jTextArea);
            box.add(jButton);
        }



        //---------------------------------发送区
        jTextArea = new JTextArea("请输入你想发送的内容.....");

        JButton jButton = new JButton("发  表");

        jButton.setBounds(350,500,70,40);

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextArea.getText();
                params.put("Content",text);
                SendRequest(new ActionDoneListener(){
                    @Override
                    public void done(Object result) {
                        jScrollPane.remove(box);
                        Box box1 = Box.createVerticalBox();
                        BacktoDate();
                        for(int i=0;i<vectors.size();i++){
                            JTextArea jTextArea = new JTextArea(vectors.elementAt(i).elementAt(0)+":"+vectors.elementAt(i).elementAt(2)+""+
                                    "                          ---"+vectors.elementAt(i).elementAt(4)+"");
                            JButton jButton = new JButton("点赞 :"+vectors.elementAt(i).elementAt(3));
                            jButton.setBounds(400,50+50*i,50,50);
                            jButton.addActionListener(new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {      //点赞怎么解决  是个bug
                                    JButton jButton1 = (JButton) e.getSource();
                                    String text = jButton1.getText();
                                    String[] strings = text.split(":");
//                        UpdatePost(Integer.parseInt(strings[0]));
                                    jButton1.setText("点赞 :"+(Integer.parseInt(strings[1])+1));
                                    /*
                                     *  利用帖子的id区查询按钮点击的是哪一个帖子，因此在进行+1操作
                                     * */

                                }
                            });

                            box1.add(jTextArea);
                            box1.add(jButton);
                        }
                        jScrollPane.add(box1);

                    }
                });

                jScrollPane.revalidate();
                jScrollPane.repaint();

            }
        });


        jTextArea.add(jButton);

        this.setBottomComponent(jTextArea);
    }

    public void SendRequest(ActionDoneListener listener){
        PostUtils.postWithParams("http://localhost:8080/post/insert", params, new SuccessListener() {
            @Override
            public Object success(String result) {
                vectors.clear();
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if(resultInfo.isFlag()){
                    JOptionPane.showMessageDialog(jTextArea,resultInfo.getMessage());
                    //      -----------
                    //
                    listener.done(null);
                } else {
                    JOptionPane.showMessageDialog(jTextArea,resultInfo.getMessage());
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {
                JOptionPane.showMessageDialog(jTextArea,"输入的字符超过了最大字符长度");
            }
        });
    }

    //获取帖子
    public void BacktoDate(){
        PostUtils.postWithParams("http://localhost:8080/post/query", pa, new SuccessListener() {
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

    //点赞事件   暂时弃用
    public void LikeEvent(){
        GetUtils.getWithParams("http://localhost:8080/post/like",pa2, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if(resultInfo.isFlag()){
                    JOptionPane.showMessageDialog(jTextArea,"点赞成功");
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }

    //利用Post_Id来更新点赞数
    void UpdatePost(Integer id){
        Map<String,String> param = new HashMap<>();
        param.put("PostId",id.toString());
        PostUtils.postWithParams("http://localhost:8080/post/update", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if(resultInfo.isFlag()){
                    res = (Integer)resultInfo.getData();
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }


}
