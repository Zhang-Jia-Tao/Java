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

//封禁账号
public class ProhibitAccount {

    String id = null;
    JFrame jf = null;
    ActionDoneListener listener = null;

    public ProhibitAccount(String id,JFrame jf,ActionDoneListener listener){
        this.id = id;
        this.jf = jf;
        this.listener = listener;

        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        GetUtils.getWithParams("http://localhost:8080/post/prohibit", param, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jf,info.getMessage()+",封号成功");

                    //封号成功之后进行删除帖子的操作
                    new DeletePost(id,jf,listener,null);
                } else {
                    JOptionPane.showMessageDialog(jf,info.getMessage());
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
        //进行回调
        listener.done(null);
    }

}
