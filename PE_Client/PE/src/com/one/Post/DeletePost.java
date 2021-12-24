package com.one.Post;


import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

//删除帖子
public class DeletePost {

    String id = null;
    JFrame jf = null;
    ActionDoneListener listener = null;
    JDialog jDialog = null;

    public DeletePost(String id, JFrame jf, ActionDoneListener listener,JDialog jDialog){
        this.id = id;
        this.jf = jf;
        this.listener = listener;
        this.jDialog = jDialog;
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        GetUtils.getWithParams("http://localhost:8080/post/del", param, new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jf,info.getMessage());
                } else {
                    JOptionPane.showMessageDialog(jf,info.getMessage());
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
        if(jDialog != null) {
            jDialog.dispose();
        }
        listener.done(null);
    }



}
