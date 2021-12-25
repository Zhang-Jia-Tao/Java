package com.one.Admin;

import com.one.component.AddData;
import com.one.component.DelData;
import com.one.component.UpsetData;
import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector;
import com.one.util.ResultInfoData2Vector_admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;


public class Panel_Admin extends Box {

    final int WIDTH = 850;
    final int HEIGHT = 600;

    JFrame jf = null;

    private JTable table;
    private Vector<String> title;
    private Vector<Vector> tableDate;
    private TableModel tableModel;

    public Panel_Admin(JFrame jf){
        //垂直布局
        super(BoxLayout.Y_AXIS);

        this.jf = jf;

        //组装视图
        JPanel btnjPanel = new JPanel();
        btnjPanel.setBackground(new Color(0x33D033));
        btnjPanel.setMaximumSize(new Dimension(WIDTH,80));
        btnjPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addBtn = new JButton("添加管理员");
        JButton updateBtn = new JButton("修改权限等级");
        JButton delBtn = new JButton("删除管理员");
        btnjPanel.add(delBtn);
        btnjPanel.add(updateBtn);
        btnjPanel.add(addBtn);


        addBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出对话框让管理员录入资料
                new AddAdmin(jf, "添加", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {   //设计一个接口 为了在录入完成后刷新表格数据，显示最新的数据
                        requestData();
                    }
                }).setVisible(true);

            }
        });



        this.add(btnjPanel);

        //组装表格
        String[] ts={"管理员ID","管理员","密码","权限等级"};
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



        //用于修改管理员的有关信息
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
                new UpdateAdmin(jf, "修改", true, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        requestData();
                    }
                },id).setVisible(true);
            }
        });

        //用于删除某个管理员
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

                new DelAdmin(jf,id, new ActionDoneListener() {
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
        GetUtils.get("http://localhost:8080/admin/query", new SuccessListener() {
            @Override
            public void success(String result) {
                Vector<Vector> vectors = null;
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if(resultInfo.isFlag()){
                    vectors = ResultInfoData2Vector_admin.convertResultInfoData2Vector(resultInfo);
                } else {
                    JOptionPane.showMessageDialog(jf,resultInfo.getMessage());
                }
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

