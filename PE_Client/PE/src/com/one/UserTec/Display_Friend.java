package com.one.UserTec;

import com.one.Imporve.DefaultMutableTreeNode_name;
import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_friend;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import java.util.Vector;


//UserID_F 是自己，不是朋友的id

//显示好友列表
public class Display_Friend {

    DefaultMutableTreeNode node = null;
    Vector<Vector> vectors = null;
    JTree jTree = null;
    JSplitPane jSplitPane = null;

    public Display_Friend(DefaultMutableTreeNode node,JTree jTree,JSplitPane jSplitPane){

        this.node = node;
        this.jTree = jTree;
        this.jSplitPane = jSplitPane;

        //获取好友信息
        GetUtils.get("http://localhost:8080/friend/query", new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    vectors = ResultInfoData2Vector_friend.convertResultInfoData2Vector(info);
                } else {
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });

        for(int i=0;i<vectors.size();i++){
            DefaultMutableTreeNode_name treeNode = new DefaultMutableTreeNode_name("" + vectors.elementAt(i).elementAt(0),i);
            node.add(treeNode);

            //对每个treeNode添加监听
            jTree.addTreeSelectionListener(e -> {
                Object component = e.getNewLeadSelectionPath().getLastPathComponent();
                DefaultMutableTreeNode_name treenode = (DefaultMutableTreeNode_name) component;
                int id = treenode.id;
                //朋友的用户名和电话号码
                String username = vectors.elementAt(id).elementAt(0).toString();
                String phone = vectors.elementAt(id).elementAt(1).toString();
//                System.out.println(username+""+phone);

                Box_Talk box_talk = new Box_Talk(username,phone);

                jSplitPane.setRightComponent(box_talk.init());

            });


        }

    }
}
