package com.one.GUI;

import com.one.UserTec.ConfirmJDialog;
import com.one.UserTec.Display_Friend;
import com.one.UserTec.QueryConfirm;
import com.one.component.*;
import com.one.online.OnlineExam;
import com.one.online.exam;
import com.one.online.exam_false;
import com.one.util.ScreenUtils;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.util.Vector;

//主窗口
public class ManagerInterface {

    //好友名
    public static String friendName = "";
    public static boolean status = false;


    JFrame jFrame = new JFrame("考研加油");
    final int WIDTH = 1000;
    final int HEIGHT = 600;

    AddFriend addFriend = new AddFriend();

    JTree jTree = null;
    JSplitPane jsp = null;

    public void init(){
        jFrame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jFrame.setResizable(false);

        //设置菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("设置");
        JMenu jMenu02 = new JMenu("添加好友");
        JMenu jMenu03 = new JMenu("申请信息");
        JMenu jMenu04 = new JMenu("我的帖子");
        JMenuItem item1 = new JMenuItem("切换账号");
        JMenuItem item2 = new JMenuItem("退出程序");
        JMenuItem item3 = new JMenuItem("修改信息");
        JMenuItem item4 = new JMenuItem("添加好友");
        JMenuItem item5 = new JMenuItem("申请信息");
        JMenuItem item6 = new JMenuItem("我的帖子");

        item1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainInterface().init();
                    jFrame.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        item2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        item3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateUser().init();
            }
        });


        //添加好友的事件监听
        item4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFriend.AddFriends();
            }
        });

        //申请信息的事件监听
        item5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //申请信息
                new RequestInformation(jFrame);
            }
        });

        //我的帖子的事件监听
        item6.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setBounds((ScreenUtils.getScreenWidth()-WIDTH-300)/2,(ScreenUtils.getScreenHeight()-HEIGHT-200)/2,WIDTH-300,HEIGHT-200);
                dialog.add(new myPost(jFrame));
                dialog.setVisible(true);
            }
        });



        jMenu.add(item3);
        jMenu.add(item1);
        jMenu.add(item2);
        jMenu02.add(item4);
        jMenu03.add(item5);
        jMenu04.add(item6);

        jMenuBar.add(jMenu);
        jMenuBar.add(jMenu02);
        jMenuBar.add(jMenu03);
        jMenuBar.add(jMenu04);

        //将菜单栏设在jframe中
        jFrame.setJMenuBar(jMenuBar);

        //设置分隔面板
        jsp = new JSplitPane();

        //支持连续布局
        jsp.setContinuousLayout(true);
        jsp.setDividerLocation(150);
        jsp.setDividerSize(3);

        //设置左侧内容---->菜单项
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Function");


        DefaultMutableTreeNode data = new DefaultMutableTreeNode("课程资料");    //data 网盘资料
            DefaultMutableTreeNode data_IT = new DefaultMutableTreeNode("计算机");
            DefaultMutableTreeNode data_Math = new DefaultMutableTreeNode("数学");
            DefaultMutableTreeNode data_English = new DefaultMutableTreeNode("英语");
            DefaultMutableTreeNode data_Poli = new DefaultMutableTreeNode("政治");
            DefaultMutableTreeNode data_Econ = new DefaultMutableTreeNode("经济类");
            data.add(data_IT);
            data.add(data_Math);
            data.add(data_English);
            data.add(data_Poli);
            data.add(data_Econ);


        DefaultMutableTreeNode post = new DefaultMutableTreeNode("贴吧区");     //post 贴吧区
            DefaultMutableTreeNode post_IT = new DefaultMutableTreeNode("IT区");
            DefaultMutableTreeNode post_Math = new DefaultMutableTreeNode("数学区");
            DefaultMutableTreeNode post_English = new DefaultMutableTreeNode("英语区");
            DefaultMutableTreeNode post_Poli = new DefaultMutableTreeNode("政治区");
            DefaultMutableTreeNode post_Econ = new DefaultMutableTreeNode("经济类区");
            post.add(post_IT);
            post.add(post_Math);
            post.add(post_English);
            post.add(post_Poli);
            post.add(post_Econ);

        DefaultMutableTreeNode exam = new DefaultMutableTreeNode("题库");
            DefaultMutableTreeNode exam_IT = new DefaultMutableTreeNode("计算机试题");
            DefaultMutableTreeNode exam_Math = new DefaultMutableTreeNode("数学试题");
            DefaultMutableTreeNode exam_English = new DefaultMutableTreeNode("英语试题");
            DefaultMutableTreeNode exam_Poli = new DefaultMutableTreeNode("政治试题");
            DefaultMutableTreeNode exam_Econ = new DefaultMutableTreeNode("经济类试题");
            exam.add(exam_IT);
            exam.add(exam_Math);
            exam.add(exam_English);
            exam.add(exam_Poli);
            exam.add(exam_Econ);

        DefaultMutableTreeNode OnlineExam = new DefaultMutableTreeNode("在线考试");
            DefaultMutableTreeNode Online_IT = new DefaultMutableTreeNode("计算机");
            DefaultMutableTreeNode Online_Math = new DefaultMutableTreeNode("数学");
            DefaultMutableTreeNode Online_English = new DefaultMutableTreeNode("英语");
            DefaultMutableTreeNode Online_Poli = new DefaultMutableTreeNode("政治");
            DefaultMutableTreeNode Online_Econ = new DefaultMutableTreeNode("经济");
            OnlineExam.add(Online_IT);
            OnlineExam.add(Online_Math);
            OnlineExam.add(Online_English);
            OnlineExam.add(Online_Poli);
            OnlineExam.add(Online_Econ);

        DefaultMutableTreeNode friend = new DefaultMutableTreeNode("研友");
            DefaultMutableTreeNode friend_Dir = new DefaultMutableTreeNode("联系人");
            friend.add(friend_Dir);

            root.add(data);
            root.add(post);
            root.add(exam);
            root.add(OnlineExam);
            root.add(friend);


        jTree = new JTree(root);


        //
        // 在这里进行 联系人的添加
        //
        new Display_Friend(friend,jTree,jsp);

        //设置节点的图标以及背景色
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setBackgroundNonSelectionColor(new Color(0xABF0E3));
        renderer.setBackgroundSelectionColor(new Color(0x629DA3));
        jTree.setCellRenderer(renderer);
        jTree.setBackground(new Color(0xABF0E3));
        jsp.setLeftComponent(jTree);
        DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) jTree.getCellRenderer();
        //cellRenderer.setLeafIcon(new ImageIcon("/Volumes/T7/PE_Client/PE/src/com/one/images/shezhi.png"));



        //------------------------------------------------
        //每次登陆都会检查Confrim表 通过Receive_id对比当前登陆id进行匹配 查看是否弹出有好友申请消息
        Vector<Vector> vectors = new QueryConfirm().query();
        if(vectors != null){
            //申请列表不为空才会有弹窗
            new ConfirmJDialog(jFrame,"申请信息",true,vectors).setVisible(true);

        }













        //-------------------------------------------------
        //对左侧Tree的监听
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //得到当先选中的节点对象
                Object component = e.getNewLeadSelectionPath().getLastPathComponent();

                //-------------data----------------
                if (data_IT.equals(component)) {
                    Boxs boxs = new Boxs("计算机");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(boxs);
                } else if (data_Math.equals(component)) {
                    Boxs boxs = new Boxs("数学");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(boxs);
                } else if (data_Econ.equals(component)) {
                    Boxs boxs = new Boxs("经济学");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(boxs);
                } else if (data_English.equals(component)) {
                    Boxs boxs = new Boxs("英语");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(boxs);
                } else if (data_Poli.equals(component)) {
                    Boxs boxs = new Boxs("政治");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(boxs);

                    //----------post----------------
                } else if (post_Econ.equals(component)) {
                    JSplitPane_Post2 post_box = new JSplitPane_Post2("经济学",jFrame,friend,jTree);
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(post_box);
                } else if (post_English.equals(component)) {
                    JSplitPane_Post post_box = new JSplitPane_Post("英语");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(post_box);
                } else if (post_IT.equals(component)) {
                    JSplitPane_Post post_box = new JSplitPane_Post("计算机");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(post_box);
                } else if (post_Math.equals(component)) {
                    JSplitPane_Post post_box = new JSplitPane_Post("数学");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(post_box);
                } else if (post_Poli.equals(component)) {
                    JSplitPane_Post post_box = new JSplitPane_Post("政治");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(post_box);
                    //---------------exam-----------
                } else if (exam_Econ.equals(component)) {
                    JScr_Exam jScr_exam = new JScr_Exam("经济学");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(jScr_exam);
                } else if (exam_English.equals(component)) {
                    JScr_Exam jScr_exam = new JScr_Exam("英语");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(jScr_exam);
                } else if (exam_IT.equals(component)) {
                    JScr_Exam jScr_exam = new JScr_Exam("计算机");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(jScr_exam);
                } else if (exam_Math.equals(component)) {
                    JScr_Exam jScr_exam = new JScr_Exam("数学");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(jScr_exam);
                } else if (exam_Poli.equals(component)) {
                    JScr_Exam jScr_exam = new JScr_Exam("政治");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(jScr_exam);
                    //--------------------------Online Exam-----------------------

                } else if(Online_IT.equals(component)){
                    //设立在线考试;
                    com.one.online.OnlineExam exam1 = new OnlineExam("计算机");
                    jsp.setDividerLocation(150);
                    jsp.setRightComponent(exam1);
                } else if(Online_Math.equals(component)){


                } else if(Online_English.equals(component)){

                } else if(Online_Poli.equals(component)){

                } else if(Online_Econ.equals(component)){

                } else if(friend_Dir.equals(component)){

                } else {
                    jsp.setRightComponent(new BackGround());
                }

            }
        });

        jFrame.add(jsp);
        jFrame.setVisible(true);
    }


    private class MyRender extends DefaultTreeCellRenderer{
//        private Image root = ImageIO.read(new File(""));
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        }
    }

    public static void main(String[] args) {
        new ManagerInterface().init();
    }
}
