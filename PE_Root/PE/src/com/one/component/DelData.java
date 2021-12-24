package com.one.component;


import com.one.domain.ResultInfo;
import com.one.listener.ActionDoneListener;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

//用于删除数据
public class DelData {
    String id = null;
    ActionDoneListener actionDoneListener = null;
    JFrame jf = null;

    public DelData(JFrame jf,String id,ActionDoneListener actionDoneListener){
        this.id = id;
        this.actionDoneListener = actionDoneListener;
        this.jf = jf;

        Del();

    }

    //根据id，删除数据
    public void Del(){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        GetUtils.getWithParams("http://localhost:8080/data/del", param, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                    JOptionPane.showMessageDialog(jf,info.getMessage());
                } else {
                    JOptionPane.showMessageDialog(jf,info.getMessage());
                }
                //回调
                actionDoneListener.done(null);
            }
        }, new FailListener() {
            @Override
            public void fail() {
                JOptionPane.showMessageDialog(jf,"服务器异常");
            }
        });
    }

}
