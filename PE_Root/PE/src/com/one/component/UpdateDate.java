package com.one.component;

import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

//用于管理员更新数据
//管理数据的主界面
public class UpdateDate extends Box {

    final int WIDTH = 850;
    final int HEIGHT = 600;

    JFrame jf = null;

    private JTable table;
    private Vector<String> title;
    private Vector<Vector> tableDate;
    private TableModel tableModel;

    public UpdateDate(JFrame jf){
        //垂直布局
        super(BoxLayout.Y_AXIS);

        this.jf = jf;

        //组装视图
        JPanel btnjPanel = new JPanel();
        btnjPanel.setBackground(new Color(0x33D033));
        btnjPanel.setMaximumSize(new Dimension(WIDTH,80));
        btnjPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addBtn = new JButton("添加");
        JButton updateBtn = new JButton("修改");
        JButton delBtn = new JButton("删除");
        btnjPanel.add(delBtn);
        btnjPanel.add(updateBtn);
        btnjPanel.add(addBtn);


        addBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出对话框让管理员录入资料
                new AddData(jf, "添加", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {   //设计一个接口 为了在录入完成后刷新表格数据，显示最新的数据
                        requestData();
                    }
                }).setVisible(true);

            }
        });



        this.add(btnjPanel);

        //组装表格
        String[] ts={"资料ID","类型","资料地址","下载次数","验证码","简介"};
        title = new Vector<>();
        for(String tit:ts){
            title.add(tit);
        }

        tableDate = new Vector<>();

        tableModel = new DefaultTableModel(tableDate,title);

        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //设置只能选中一行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane jScrollPane = new JScrollPane(table);

        this.add(jScrollPane);

        requestData();



        //用于更新数据
        updateBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //获取当前选中的行号
                int selectedRow = table.getSelectedRow(); //如果有条目选中，则返回行号，如果没有选中条目则返回-1
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(jf,"请选择要修改的条目");
                    return;
                }

                String id = tableModel.getValueAt(selectedRow,0).toString();


                //弹出对话框，让管理员修改信息
                new UpsetData(jf, "修改", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        requestData();
                    }
                },id).setVisible(true);
            }
        });


        delBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //用于删除数据
                int selectedRow = table.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(jf,"请选择要删除的条目");
                    return;
                }
                String id = tableModel.getValueAt(selectedRow,0).toString();

                new DelData(jf,id, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                            requestData();
                    }
                });
            }
        });




    }

    //请求数据
    public void requestData(){
        GetUtils.get("http://localhost:8080/data/search", new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                Vector<Vector> vectors = ResultInfoData2Vector.convertResultInfoData2Vector(resultInfo);
                //清空tableData中的数据
                tableDate.clear();
                for(Vector vector : vectors){
                    tableDate.add(vector);
                }

            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }




}
