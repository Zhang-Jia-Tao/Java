package com.one.component;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Boxs extends Box {

    final int WIDTH = 850;
    final int HEIGHT = 600;

    private JTable jTable;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private TableModel tableModel;

    //资料类型
     public Map<String,Object> type = new HashMap<>();

    public Boxs(String type){

        super(1);  //垂直布局

        this.type.put("type",type);

        //设置组件
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(0x7FE9C6));
        jPanel.setMaximumSize(new Dimension(WIDTH,80));
        jPanel.setLayout(new FlowLayout());

        JButton jButton01 = new JButton("升序");
        JButton jButton02 = new JButton("降序");

        jPanel.add(jButton01);
        jPanel.add(jButton02);

         this.add(jPanel);

         //组装表格

        String[] strings = {"资料内容介绍","验证码","资料类型","下载次数","下载地址"};
        titles = new Vector<>();
        for(String title: strings){
            titles.add(title);
        }
        tableData = new Vector<>();
        tableModel = new DefaultTableModel(tableData,titles);

        //创建JTable
        jTable = new JTable(tableModel);

        JScrollPane jScrollPane = new JScrollPane(jTable);

        this.add(jScrollPane);

        requestData();

    }


    //请求数据
    public void requestData(){

        GetUtils.getWithParams("http://localhost:8080/data/query",this.type , new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if (resultInfo.isFlag()) {
                    Vector<Vector> vectors = ResultInfoData2Vector.convertResultInfoData2Vector(resultInfo);//服务端发送的数据

                    //清空tabledata中的数据
                    tableData.clear();

                    for(Vector<Vector> vector:vectors){
                        tableData.add(vector);
                    }

                    //刷新表格

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
