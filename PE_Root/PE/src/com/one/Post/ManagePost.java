package com.one.Post;

import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector;
import com.one.util.ResultInfoData2Vector_post;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;


//管理贴吧的主界面
//管理贴吧的主要两个功能就是 删除帖子和封号
public class ManagePost extends Box {

    final int WIDTH = 850;
    final int HEIGHT = 600;

    JFrame jf = null;

    private JTable table;
    private Vector<String> title;
    private Vector<Vector> tableDate;
    private TableModel tableModel;


    public ManagePost(JFrame jf){
        super(BoxLayout.Y_AXIS);
        this.jf = jf;
        //组装视图
        JPanel btnjPanel = new JPanel();
        btnjPanel.setBackground(new Color(0x33D033));
        btnjPanel.setMaximumSize(new Dimension(WIDTH,80));
        btnjPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton delBtn = new JButton("删除");
        JButton FengBtn = new JButton("封号");
        JButton QueryBtn = new JButton("查看帖子全部内容");
        btnjPanel.add(delBtn);
        btnjPanel.add(FengBtn);
        btnjPanel.add(QueryBtn);

        this.add(btnjPanel);





        //组装表格
        String[] ts={"贴子ID","贴子内容"};
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


        //以下为监听事件
        QueryBtn.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //获取当前选中的行号
                int selectedRow = table.getSelectedRow(); //如果有条目选中，则返回行号，如果没有选中条目则返回-1
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(jf,"请选择要查看的条目");
                    return;
                }
                String id = tableModel.getValueAt(selectedRow,0).toString();

                new QueryContent(jf, "查看全部内容", true, id, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        //进行回调
                        requestData();
                    }
                }).setVisible(true);
            }
        });

        delBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //获取当前选中的行号
                int selectedRow = table.getSelectedRow(); //如果有条目选中，则返回行号，如果没有选中条目则返回-1
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(jf,"请选择要删除的条目");
                    return;
                }
                String id = tableModel.getValueAt(selectedRow,0).toString();

                new DeletePost(id, jf, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        requestData();
                    }
                },null);
            }
        });


        //封号之后，连带着应该把该帖子进行删除
        FengBtn.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //获取当前选中的行号
                int selectedRow = table.getSelectedRow(); //如果有条目选中，则返回行号，如果没有选中条目则返回-1
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(jf,"请选择要删除的条目");
                    return;
                }
                String id = tableModel.getValueAt(selectedRow,0).toString();

                new ProhibitAccount(id, jf, new ActionDoneListener() {
                    @Override
                    public void done(Object result) {
                        requestData();
                    }
                });

            }
        });

    }


    public void requestData(){
        GetUtils.get("http://localhost:8080/post/search", new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    Vector<Vector> vectors = ResultInfoData2Vector_post.convertResultInfoData2Vector(info);
                        tableDate.clear();
                        for(Vector vector : vectors){
                            tableDate.add(vector);
                        }
                } else {
                    JOptionPane.showMessageDialog(jf,info.getMessage());
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {
                JOptionPane.showMessageDialog(jf,"服务器异常");
            }
        });
    }
}
