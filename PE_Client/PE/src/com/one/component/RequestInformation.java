package com.one.component;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_user;
import com.one.util.ScreenUtils;
import com.one.util.WrapLayout;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/*
* 有个bug 它不显示 面板上的组件的信息
*
* */
public class RequestInformation extends JDialog {

    JFrame jFrame = null;
    Vector<Vector> vectors = null;
    JPanel MainPanel = null;
    JScrollPane jScrollPane = null;

    public RequestInformation(JFrame jf){
        //请求信息
        this.jFrame = jf;
        init();

    }

    //初始化一个JDialog 来显示申请信息
    public void init(){
        //主窗口
        JDialog jDialog = new JDialog(jFrame,"申请信息",true);
        jDialog.setLayout(null);
        jDialog.setBounds((ScreenUtils.getScreenWidth()-560)/2,(ScreenUtils.getScreenHeight()-680)/2,560,680);

        MainPanel = new JPanel();
        MainPanel.setLayout(null);
        MainPanel.setBounds(0,0,560,680);
        MainPanel.setBackground(new Color(0xE0F0F0));

        //获取申请列表
        requestmis();
        Dispaly();

        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(0,0,560,680);
        jScrollPane.setBackground(new Color(0x1F6767));
        jScrollPane.setViewportView(MainPanel);
        MainPanel.repaint();
        jDialog.add(MainPanel);

        jDialog.setVisible(true);
    }

    //查询申请信息
    public void requestmis(){
        GetUtils.get("http://localhost:8080/confirm/queue", new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    vectors = ResultInfoData2Vector_user.convertResultInfoData2Vector(info,1);
                } else {
                    JOptionPane.showMessageDialog(jFrame,info.getMessage());
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }

    public void Dispaly(){
        int y=0;
        for(int i=0;i<vectors.size();i++){

            JPanel jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.setBounds(0,y,560,50);

            JLabel name = new JLabel(i+"-用户名："+vectors.elementAt(i).elementAt(0));
            JLabel sex = new JLabel("年龄："+vectors.elementAt(i).elementAt(1));
            JLabel phone = new JLabel("联系方式："+vectors.elementAt(i).elementAt(2));
            name.setBounds(0,y,100,50);
            sex.setBounds(100,y,60,50);
            phone.setBounds(160,y,180,50);

            JButton agree = new JButton("同意");
            JButton refuse = new JButton("拒绝");
            agree.setBounds(360,y+5,60,40);
            refuse.setBounds(480,y+5,60,40);

            jPanel.add(name);
            jPanel.add(sex);
            jPanel.add(phone);
            jPanel.add(agree);
            jPanel.add(refuse);

            agree.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    JPanel panel = (JPanel) button.getParent();
                    JLabel jLabel = (JLabel)panel .getComponent(0);
                    String text = jLabel.getText();
                    String[] split = text.split("-");
                    String i_str = split[0];
                    Integer i = Integer.parseInt(i_str);
                    //添加同意的监听事件
                    agree(i);
                }
            });

            refuse.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //添加拒绝的监听事件
                    JButton button = (JButton) e.getSource();
                    JPanel panel = (JPanel) button.getParent();
                    JLabel jLabel = (JLabel)panel .getComponent(0);
                    String text = jLabel.getText();
                    String[] split = text.split("-");
                    String i_str = split[0];
                    Integer i = Integer.parseInt(i_str);

                    refuse(i);
                }
            });
            jPanel.repaint();

            MainPanel.add(jPanel);

            y += 80;
        }

    }


    public void agree(int i){
        //根据申请人的姓名和联系方式唯一确定申请人的id
        Map<String,String> param = new HashMap<>();
        param.put("requestname",vectors.elementAt(i).elementAt(0).toString());
        param.put("requestphone",vectors.elementAt(i).elementAt(2).toString());

        PostUtils.postWithParams("http://localhost:8080/confirm/agree", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jFrame,info.getMessage());
                } else {
                    JOptionPane.showMessageDialog(jFrame,info.getMessage());
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }


    public void refuse(int i){
        Map<String,String> param = new HashMap<>();
        param.put("requestname",vectors.elementAt(i).elementAt(0).toString());
        param.put("requestphone",vectors.elementAt(i).elementAt(2).toString());

        PostUtils.postWithParams("http://localhost:8080/confirm/refuse", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jFrame,info.getMessage());
                } else {
                    JOptionPane.showMessageDialog(jFrame,info.getMessage());
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
