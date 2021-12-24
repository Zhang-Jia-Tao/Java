package com.one.Exam;

import com.one.domain.Exam;
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
import java.util.Vector;

public class QueryExam extends JDialog {
    final int WIDTH = 1680;
    final int HEIGHT = 1440;

    ActionDoneListener listener = null;
    String id = null;


    //

    JComboBox<String> stringJComboBox = null;
    JTextArea ja_stem = null;
    JTextField jt_optionA = null;
    JTextField jt_optionB = null;
    JTextField jt_optionC = null;
    JTextField jt_optionD = null;
    JTextField jt_answer = null;
    JTextField jt_rate = null;
    JTextField jt_num = null;

    //

    //根据选中的行，来填充原先的位置的内容
    public QueryExam(JFrame jf,String title,boolean isModel,ActionDoneListener listener,String id){
        super(jf,title,isModel);
        this.listener = listener;
        this.id = id;
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        //Exam类型
        Box TypeBox = Box.createHorizontalBox();
        JLabel jl_type = new JLabel("试题类型");
        stringJComboBox = new JComboBox<>();
        stringJComboBox.addItem("计算机");
        stringJComboBox.addItem("数学");
        stringJComboBox.addItem("英语");
        stringJComboBox.addItem("政治");
        stringJComboBox.addItem("经济学");
        TypeBox.add(jl_type);
        TypeBox.add(Box.createHorizontalStrut(20));
        TypeBox.add(stringJComboBox);

        //题干
        Box Box_stem = Box.createHorizontalBox();
        JLabel jl_stem = new JLabel("资料内容");
        ja_stem = new JTextArea(3,15);    //文本域
        Box_stem.add(jl_stem);
        Box_stem.add(Box.createHorizontalStrut(20));
        Box_stem.add(new JScrollPane(ja_stem));   // 支持滚动条

        //optionA
        Box Box_optionA = Box.createHorizontalBox();
        JLabel jl_optionA = new JLabel("选项A");
        jt_optionA = new JTextField(150);
        Box_optionA.add(jl_optionA);
        Box_optionA.add(Box.createHorizontalStrut(40));
        Box_optionA.add(jt_optionA);

        //optionB
        Box Box_optionB = Box.createHorizontalBox();
        JLabel jl_optionB = new JLabel("选项B");
        jt_optionB = new JTextField(150);
        Box_optionA.add(jl_optionB);
        Box_optionA.add(Box.createHorizontalStrut(40));
        Box_optionA.add(jt_optionB);

        //optionC
        Box Box_optionC = Box.createHorizontalBox();
        JLabel jl_optionC = new JLabel("选项C");
        jt_optionC = new JTextField(150);
        Box_optionA.add(jl_optionC);
        Box_optionA.add(Box.createHorizontalStrut(40));
        Box_optionA.add(jt_optionC);

        //optionD
        Box Box_optionD = Box.createHorizontalBox();
        JLabel jl_optionD = new JLabel("选项D");
        jt_optionD = new JTextField(150);
        Box_optionA.add(jl_optionD);
        Box_optionA.add(Box.createHorizontalStrut(40));
        Box_optionA.add(jt_optionD);

        //answer
        Box Box_answer = Box.createHorizontalBox();
        JLabel jl_answer = new JLabel("答案");
        jt_answer = new JTextField(150);
        Box_answer.add(jl_answer);
        Box_answer.add(Box.createHorizontalStrut(40));
        Box_answer.add(jt_answer);

        //correctRate
        Box Box_rate = Box.createHorizontalBox();
        JLabel jl_rate = new JLabel("正确率");
        jt_rate = new JTextField(150);
        Box_rate.add(jl_rate);
        Box_rate.add(Box.createHorizontalStrut(40));
        Box_rate.add(jt_rate);

        //答题数
        Box Box_num = Box.createHorizontalBox();
        JLabel jl_num = new JLabel("答题数");
        jt_num = new JTextField(150);
        Box_num.add(jl_num);
        Box_num.add(Box.createHorizontalStrut(40));
        Box_num.add(jt_num);

        //组装按钮
        Box Box_bn = Box.createHorizontalBox();
        JButton jButton = new JButton("更新");
        Box_bn.add(jButton);


        //因为url对于中文以及特殊符号的无法处理导致的报错 --------有待解决
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更新
                Map<String,Object> param = new HashMap<>();

                String type = stringJComboBox.getSelectedItem().toString();
                String stem = ja_stem.getText();
                String optionA = jt_optionA.getText();
                String optionB = jt_optionB.getText();
                String optionC = jt_optionC.getText();
                String optionD = jt_optionD.getText();
                String answer = jt_answer.getText();
                String rate = jt_rate.getText();
                String num = jt_num.getText();

                param.put("id",id);
                param.put("type",type);
                param.put("stem",stem);
                param.put("optionA",optionA);
                param.put("optionB",optionB);
                param.put("optionC",optionC);
                param.put("optionD",optionD);
                param.put("answer",answer);
                param.put("rate",rate);
                param.put("num",num);

                GetUtils.getWithParams("http://localhost:8080/exam/update", param, new SuccessListener() {
                    @Override
                    public void success(String result) {
                        ResultInfo info = JsonUtils.parseResult(result);
                        if(info.isFlag()){
                            JOptionPane.showMessageDialog(jf,info.getMessage());

                            dispose();

                            listener.done(null);
                        }
                        JOptionPane.showMessageDialog(jf,info.getMessage());
                    }
                }, new FailListener() {
                    @Override
                    public void fail() {

                    }
                });


            }
        });



        //----------------------代码到这里

        vBox.add(Box.createVerticalStrut(20));
        vBox.add(TypeBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_stem);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_optionA);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_optionB);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_optionC);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_optionD);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_answer);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_rate);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_num);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(Box_bn);

        this.add(vBox);


        AddData();

    }

    //查询数据，将数据填回jDialog
    public void AddData(){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);

        GetUtils.getWithParams("http://localhost:8080/exam/select", param, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    Map<String,Object> map = new HashMap<>();
                    map = (Map<String,Object>) info.getData();
                    //将数据填写回JDialog中
                    stringJComboBox.setSelectedItem(map.get("examType").toString());
                    ja_stem.setText(map.get("stem").toString());
                    jt_optionA.setText(map.get("optionA").toString());
                    jt_optionB.setText(map.get("optionB").toString());
                    jt_optionC.setText(map.get("optionC").toString());
                    jt_optionD.setText(map.get("optionD").toString());
                    jt_answer.setText(map.get("answer").toString());
                    jt_rate.setText(map.get("correctRate").toString());
                    jt_num.setText(map.get("numerOfRes").toString());
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }

}
