package com.one.util;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.PostUtils;
import com.one.net_client.SuccessListener;

import java.util.Vector;

//用来保存返回的对象
public class BackFriend {


    Vector<Vector> vectors;

    public Vector<Vector> backFriend(){
        PostUtils.post("http://localhost:8080/friend/query", new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if(resultInfo.isFlag()){
                    vectors = ResultInfoData2Vector.convertResultInfoData2Vector(resultInfo);
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {
                System.out.println("Server发生异常");
            }
        });

        return vectors;
    }
}
